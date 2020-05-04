package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragment;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.Models.OrderPickPickListModel;

public class OrderPickActivity extends DaggerAppCompatActivity implements OrderPickActivityContract.View, OrderPickActivityContract.Navigator {

    @Inject
    OrderPickActivityPresenter presenter;

    BottomNavigationView bottomNavigationView;
    final OrderPickFragment orderPickFragment = new OrderPickFragment(this);
    final OrderPickOverviewFragment orderPickOverviewFragment = new OrderPickOverviewFragment(this);
    private InputMethodManager imm;
    private View view;
    Fragment active = orderPickFragment;
    static String orderName;

    //Todo: put in presenter.
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    try{
                        switch (item.getItemId()){
                            case R.id.navigation_main:
                                transferOverviewDataToMainPage();
                                break;
                            case R.id.navigation_end:
                                transferMainPageDataToOverview();
                                view = getCurrentFocus();
                                if (view == null) {
                                    view = new View(getApplicationContext());
                                }
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                break;
                        }
                    }catch (NullPointerException e){
                        return false;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pick_bottom_nav);
        bottomNavigationView = findViewById(R.id.order_pick_main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        orderName = getIntent().getStringExtra("order_name");

        imm = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        getSupportFragmentManager().beginTransaction().add(R.id.order_pick_fragment_container, orderPickOverviewFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.order_pick_fragment_container, orderPickFragment).hide(orderPickFragment).commit();
        presenter.getLocalSaveData();
        //presenter.getDataForFragments(orderName);
        bottomNavigationView.getMenu().findItem(R.id.navigation_end).setChecked(true);
    }

    public static Intent createIntent(Context context, String orderName){
        Intent orderPickIntent = new Intent(context, OrderPickActivity.class);
        orderPickIntent.putExtra("order_name", orderName);
        return orderPickIntent;
    }

    @Override
    public void onBarcodeScanned(String scan) {
        orderPickFragment.handleScan(scan);
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Todo: Change state for presenters
                        orderPickOverviewFragment.changeLoadingState(isLoading);
                        orderPickFragment.changeLoadingState(isLoading);
                    }
                });
            }
        }.start();
    }

    @Override
    public void setErrorMessage(String message) {
        orderPickOverviewFragment.setErrorMessage(message);
        orderPickFragment.setErrorMessage(message);
    }

    @Override
    public void onPickListInfoReceived(List<OrderPickPickListModel> data) {
        orderPickFragment.onPickListDataReceived(data);
        orderPickOverviewFragment.onPickListDataReceived(data);
        orderPickOverviewFragment.setAdapterNavigator(this);
        presenter.getImageDataForFragments(data);
    }

    @Override
    public void onPickListImageInfoReceived(BitmapDrawable image, int productId) {
        List<OrderPickPickListModel> models = orderPickFragment.data;
        for(int i = 0; i < models.size(); i++){
            if(models.get(i).getProductId() == productId){
                orderPickFragment.data.get(i).setImage(image);
                orderPickOverviewFragment.data.get(i).setImage(image);
                orderPickFragment.data.get(i).setImageLoaded(true);
                orderPickOverviewFragment.data.get(i).setImageLoaded(true);
            }
        }
        orderPickFragment.updateFragmentData();
        orderPickOverviewFragment.updateAdapterData();
    }

    @Override
    public void onSerialnumbersFetched(Serialnumbers serialnumbers, int productId) {
        List<OrderPickPickListModel> models = orderPickFragment.data;
        for(int i = 0; i < models.size(); i++){
            if(models.get(i).getProductId() == productId){
                orderPickFragment.data.get(i).setOpenSerialnumbers(serialnumbers.getGetAvailableBatchNumbersResult());
                orderPickOverviewFragment.data.get(i).setOpenSerialnumbers(serialnumbers.getGetAvailableBatchNumbersResult());
            }
        }
        //Todo: Could be removed later after some testing.
        orderPickFragment.updateFragmentData();
        orderPickOverviewFragment.updateAdapterData();
    }

    @Override
    public void navigateToOrder(int index) {
        transferOverviewDataToMainPage();
        orderPickFragment.setActivePage(index);
        bottomNavigationView.setSelectedItemId(R.id.navigation_main);
    }

    private void transferMainPageDataToOverview(){
        //Hands most recent order pick data over.
        getSupportFragmentManager().beginTransaction().hide(active).show(orderPickOverviewFragment).commit();
        active = orderPickOverviewFragment;
        orderPickOverviewFragment.onPickListDataReceived(orderPickFragment.data);
    }

    private void transferOverviewDataToMainPage(){
        //Hands most recent order pick data over.
        getSupportFragmentManager().beginTransaction().hide(active).show(orderPickFragment).commit();
        active = orderPickFragment;
        orderPickFragment.onPickListDataReceived(orderPickOverviewFragment.data);
    }

    public void runImageUpdateOnUiThread(BitmapDrawable drawable, int productId){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onPickListImageInfoReceived(drawable, productId);
                    }
                });
            }
        }.start();
    }

    public void runOrderUpdateOnUiThread(List<OrderPickPickListModel> orders){
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onPickListInfoReceived(orders);
                    }
                });
            }
        }.start();
    }

    public OrderPickActivityPresenter getPresenter() {
        return presenter;
    }

    public static String getOrderName() {
        return orderName;
    }

    @Override
    public void onDestroy(){
        //Todo: Save order list
        if(bottomNavigationView.getSelectedItemId() == R.id.navigation_main){
            presenter.saveLocalSaveData(orderPickFragment.data);
        }else{
            presenter.saveLocalSaveData(orderPickOverviewFragment.data);
        }

        super.onDestroy();
    }

    @Override
    public void onPause(){
        //Todo: Save order list
        if(bottomNavigationView.getSelectedItemId() == R.id.navigation_main){
            presenter.saveLocalSaveData(orderPickFragment.data);
        }else{
            presenter.saveLocalSaveData(orderPickOverviewFragment.data);
        }

        super.onPause();
    }
}

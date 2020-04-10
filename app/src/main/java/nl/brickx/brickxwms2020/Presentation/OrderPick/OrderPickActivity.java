package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragment;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickPickListModel;

public class OrderPickActivity extends DaggerAppCompatActivity implements OrderPickActivityContract.View {

    @Inject
    OrderPickActivityPresenter presenter;

    BottomNavigationView bottomNavigationView;
    final OrderPickFragment orderPickFragment = new OrderPickFragment();
    final OrderPickOverviewFragment orderPickOverviewFragment = new OrderPickOverviewFragment();
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
                                getSupportFragmentManager().beginTransaction().hide(active).show(orderPickFragment).commit();
                                active = orderPickFragment;
                                orderPickFragment.data = orderPickOverviewFragment.data;
                                break;
                            case R.id.navigation_end:
                                getSupportFragmentManager().beginTransaction().hide(active).show(orderPickOverviewFragment).commit();
                                active = orderPickOverviewFragment;
                                orderPickOverviewFragment.data = orderPickFragment.data;
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

        getSupportFragmentManager().beginTransaction().add(R.id.order_pick_fragment_container, orderPickOverviewFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.order_pick_fragment_container, orderPickFragment).hide(orderPickFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.navigation_end);

        presenter.getDataForFragments(orderName);
    }

    public static Intent createIntent(Context context, String orderName){
        Intent orderPickIntent = new Intent(context, OrderPickActivity.class);
        orderPickIntent.putExtra("order_name", orderName);
        return orderPickIntent;
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        //Todo: Change state for presenters
        orderPickOverviewFragment.changeLoadingState(isLoading);
        orderPickFragment.changeLoadingState(isLoading);
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
    }
}

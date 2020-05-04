package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickProductsFragments.ViewPagerFragmentAdapter;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewAdapter;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;

import static android.content.ContentValues.TAG;

public class OrderPickFragment extends DaggerFragment implements OrderPickFragmentContract.View {

    private OrderPickActivity parent;
    private Boolean expansionToggle = false;
    public List<OrderPickPickListModel> data;
    public List<OrderPickSerialStatusModel> serialNumbers = new ArrayList<>();
    RecyclerView statusSerialNumberRecycler;
    ImageView statusExpander;
    ConstraintLayout statusSerialNumbersConstraint;
    ViewPager imageViewPager;
    ViewPagerFragmentAdapter adapter;
    OrderPickStatusSerialNumbersAdapter serialNumberAdapter;
    TabLayout tabLayout;
    FloatingActionButton plusButton;
    FloatingActionButton minusButton;
    FloatingActionButton equalsButton;
    FloatingActionButton completedButton;
    ProgressBar progressBar;
    TextView outOfPickedTotalNeededTextView;
    TextInputEditText amountPickedText;
    ConstraintLayout statusBar;
    ConstraintLayout statusBarSerialNumbers;
    ViewFlipper viewFlipper;
    ViewGroup.LayoutParams tempParams;

    @Inject
    OrderPickFragmentContract.Presenter presenter;

    //Todo: Not pretty, fix better callback.
    public OrderPickFragment(OrderPickActivity parent) {
        this.parent = parent;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.order_pick_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        if(statusSerialNumberRecycler == null){
            statusSerialNumberRecycler = view.findViewById(R.id.order_pick_status_item_serial_number_recycler);
        }

        if(serialNumberAdapter == null){
            serialNumberAdapter = new OrderPickStatusSerialNumbersAdapter(serialNumbers);
            serialNumberAdapter.setPresenter(presenter);
            if(statusSerialNumberRecycler != null){
                statusSerialNumberRecycler.setAdapter(serialNumberAdapter);
                statusSerialNumberRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }

        if(statusSerialNumbersConstraint == null){
            statusSerialNumbersConstraint = view.findViewById(R.id.order_pick_status_item_serial_number_constraint);
        }

        if(statusExpander == null){
            statusExpander = view.findViewById(R.id.order_pick_serial_numbers_arrow_image);
            statusExpander.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Todo: Expand Menu toggle
                    if(!expansionToggle){
                        tempParams = statusSerialNumberRecycler.getLayoutParams();
                        tempParams.height = tempParams.height*2;
                        statusSerialNumberRecycler.setLayoutParams(tempParams);
                        expansionToggle = true;
                        statusExpander.setImageDrawable(getContext().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
                    }else{
                        tempParams = statusSerialNumberRecycler.getLayoutParams();
                        tempParams.height = tempParams.height/2;
                        statusSerialNumberRecycler.setLayoutParams(tempParams);
                        expansionToggle = false;
                        statusExpander.setImageDrawable(getContext().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
                    }
                }
            });
        }

        if(statusBar == null){
            statusBar = view.findViewById(R.id.order_pick_status);
        }

        if(statusBarSerialNumbers == null){
            statusBarSerialNumbers = view.findViewById(R.id.order_pick_status_serial_numbers);
        }

        if(viewFlipper == null){
            viewFlipper = view.findViewById(R.id.order_pick_status_view_flipper);
            viewFlipper.setDisplayedChild(0);
        }

        if(imageViewPager == null){
            imageViewPager = view.findViewById(R.id.order_pick_viewpager);

            imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if(state == 0){
                        updateButtonToolBar();
                    }
                }
            });
        }

        if(progressBar == null){
            progressBar = view.findViewById(R.id.loadingIcon);
            progressBar.setVisibility(View.VISIBLE);
        }

        if(tabLayout == null) {
            tabLayout = view.findViewById(R.id.order_pick_tablayout_dots);
            tabLayout.setupWithViewPager(imageViewPager, true);
        }

        if(adapter == null){
            adapter = new ViewPagerFragmentAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            imageViewPager.setAdapter(adapter);
        }

        if(amountPickedText == null){
            amountPickedText = view.findViewById(R.id.order_pick_status).findViewById(R.id.order_pick_main_product_pick_status_item_textInputLayout_edit);
        }

        if(outOfPickedTotalNeededTextView == null){
            outOfPickedTotalNeededTextView = view.findViewById(R.id.order_pick_main_amount_out_of_needed);
        }

        if(plusButton == null){
            plusButton = view.findViewById(R.id.order_pick_main_product_pick_plus_item_fab);

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plusPickedAmount();
                }
            });

            plusButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    equalPickedAmount();
                    return true;
                }
            });
        }

        if(minusButton == null){
            minusButton = view.findViewById(R.id.order_pick_main_product_pick_minus_item_fab);

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    minusPickedAmount();
                }
            });

            minusButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Todo: Debug code
                    onEqualsClicked();

                    //Reset the counted amount.
                    zeroPickedAmount();

                    return true;
                }
            });
        }

        if(completedButton == null){
            completedButton = view.findViewById(R.id.order_pick_main_product_pick_status_item_fab);

            completedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(data.get(imageViewPager.getCurrentItem()).getQuantityMet() && data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
                        imageViewPager.setCurrentItem(imageViewPager.getCurrentItem() + 1);
                    }
                }
            });
        }

        amountPickedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amountPickedText.getText().equals("0")){
                    amountPickedText.setText(String.valueOf(""));
                }
                amountPickedText.setSelection(amountPickedText.getText().length());
            }
        });

        amountPickedText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(data.get(imageViewPager.getCurrentItem()).getQuantityRequired() == Integer.parseInt(s.toString()) && data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
                        data.get(imageViewPager.getCurrentItem()).setQuantityPicked(Integer.parseInt(s.toString()));
                        data.get(imageViewPager.getCurrentItem()).setQuantityMet(true);
                        completedButton.setBackgroundTintList(ColorStateList.valueOf(getContext().getResources().getColor(R.color.status_free)));
                        data.get(imageViewPager.getCurrentItem()).setQuantityPicked(Integer.parseInt(amountPickedText.getText().toString()));
                    }else{
                        data.get(imageViewPager.getCurrentItem()).setQuantityMet(false);
                        completedButton.setBackgroundTintList(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorPrimary)));
                        data.get(imageViewPager.getCurrentItem()).setQuantityPicked(Integer.parseInt(amountPickedText.getText().toString()));
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    data.get(imageViewPager.getCurrentItem()).setQuantityMet(false);
                    completedButton.setBackgroundTintList(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorPrimary)));
                }
                adapter.updateOrderState(data.get(imageViewPager.getCurrentItem()), imageViewPager.getCurrentItem());
                amountPickedText.setSelection(amountPickedText.getText().length());
            }
        });
    }

    private void updateButtonToolBar(){

        try{
            if(data.get(imageViewPager.getCurrentItem()).getSerialNumberRequired())
            {
                serialNumbers.clear();
                for(int i = 0; i < data.get(imageViewPager.getCurrentItem()).getScannedSerialNumbers().size(); i++){
                    serialNumbers.add(new OrderPickSerialStatusModel(data.get(imageViewPager.getCurrentItem()).getScannedSerialNumbers().get(i), true, data.get(imageViewPager.getCurrentItem()).getProductId()));
                }
                serialNumberAdapter.setData(serialNumbers);
                serialNumberAdapter.notifyDataSetChanged();
                viewFlipper.setDisplayedChild(1);
            }else{
                viewFlipper.setDisplayedChild(0);
            }
        }catch (Exception e){
            Log.i(TAG, "Couldn't read serial number required field.");
            viewFlipper.setDisplayedChild(0);
        }

        try{
            if(!data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
                amountPickedText.setFocusable(false);
            }else{
                amountPickedText.setFocusable(true);
                amountPickedText.setFocusableInTouchMode(true);
            }
        }catch (Exception e){
            Log.i(TAG, "Edit text not visible on screen.");
        }

        try{
            outOfPickedTotalNeededTextView.setText(String.valueOf(getString(R.string.spaceless_splitter) + data.get(imageViewPager.getCurrentItem()).getQuantityRequired()));
        }catch (Exception e){
            Log.i(TAG, "TextView not visible on screen.");
        }

        try{
            amountPickedText.setText(String.valueOf(data.get(imageViewPager.getCurrentItem()).getQuantityPicked()));
        }catch (Exception e){
            e.printStackTrace();
            Log.i(TAG, "Edit text not visible on screen.");
        }
    }

    private void onLocationScanned(){
        //Todo: Change color of textfields on ViewPager.
    }

    public void onPickListDataReceived(List<OrderPickPickListModel> data){
        this.data = data;
        adapter.data = data;
        adapter.notifyDataSetChanged();
        updateButtonToolBar();
    }

    @Override
    public void handleScan(String scan) {
        if(data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
            if(scan.equals(data.get(imageViewPager.getCurrentItem()).getProductSku())){
                plusPickedAmount();
            }else if(!data.get(imageViewPager.getCurrentItem()).getScannedSerialNumbers().contains(scan) && data.get(imageViewPager.getCurrentItem()).getOpenSerialnumbers().contains(scan)){
                data.get(imageViewPager.getCurrentItem()).getScannedSerialNumbers().add(scan);
                updateButtonToolBar();
                checkIfEnoughSerialNumbersScanned();
            }
        }else if(scan.equals(data.get(imageViewPager.getCurrentItem()).getLocationTag())){
                onEqualsClicked();
        }
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        if(progressBar != null){
            if(isLoading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void setErrorMessage(String message) {
        Snackbar.make(this.getView(), message, Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parent.getPresenter().getDataForFragments(OrderPickActivity.getOrderName());
                    }
                }).setDuration(20000).show();
    }

    @Override
    public void updateSerialnumbers(List<OrderPickPickListModel> data) {
        this.data = data;
        adapter.data = this.data;
        adapter.notifyDataSetChanged();
        updateButtonToolBar();
    }

    @Override
    public List<OrderPickPickListModel> getData() {
        return data;
    }

    @Override
    public int getCurrentViewPagerIndex() {
        return imageViewPager.getCurrentItem();
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            updateButtonToolBar();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        updateButtonToolBar();
    }

    private void plusPickedAmount(){
        if(data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
            int currentAmount = -1;
            try{
                currentAmount = Integer.parseInt(amountPickedText.getText().toString());
            }catch (Exception e){
                currentAmount = 0;
            }

            if(currentAmount > -1 && currentAmount < data.get(imageViewPager.getCurrentItem()).getQuantityRequired()){
                currentAmount++;
                amountPickedText.setText(String.valueOf(currentAmount));
            }
        }
    }

    private void checkIfEnoughSerialNumbersScanned(){
        if(data.get(imageViewPager.getCurrentItem()).getQuantityRequired() == data.get(imageViewPager.getCurrentItem()).getScannedSerialNumbers().size()){
            data.get(imageViewPager.getCurrentItem()).setQuantityMet(true);
            updateFragmentData();
        }
    }

    private void minusPickedAmount(){
        if(data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
            int currentAmount = -1;
            try{
                currentAmount = Integer.parseInt(amountPickedText.getText().toString());
            }catch (Exception e){}

            if(currentAmount > 0){
                currentAmount--;
                amountPickedText.setText(String.valueOf(currentAmount));
            }
        }
    }

    private void zeroPickedAmount(){
        if(data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
            amountPickedText.setText(String.valueOf(0));
        }
    }

    private void equalPickedAmount(){
        if(data.get(imageViewPager.getCurrentItem()).getLocationScanned()){
            amountPickedText.setText(String.valueOf(data.get(imageViewPager.getCurrentItem()).getQuantityRequired()));
        }
    }

    private void onEqualsClicked(){
        data.get(imageViewPager.getCurrentItem()).setLocationScanned(true);
        onLocationScanned();
        updateButtonToolBar();
    }

    public void updateFragmentData(){
        adapter.data = data;
        adapter.notifyDataSetChanged();
    }

    public void setActivePage(int index){
        imageViewPager.setCurrentItem(index);
    }
}

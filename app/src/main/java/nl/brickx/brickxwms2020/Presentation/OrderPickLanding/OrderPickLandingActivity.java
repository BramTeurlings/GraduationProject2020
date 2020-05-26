package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickLandingRecyclerModel;

public class OrderPickLandingActivity extends DaggerAppCompatActivity implements OrderPickLandingContract.View {

    private RecyclerView orderRecycler;
    private OrderPickLandingAdapter adapter;
    ProgressBar loadingProgressBar;
    TextInputLayout combinedInputLayout;
    TextInputEditText barcodeInput;


    @Inject
    OrderPickLandingContract.Presenter presenter;

    @Inject
    OrderPickLandingContract.Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pick_landing_page);
        orderRecycler = findViewById(R.id.order_pick_landing_recycler_view);
        loadingProgressBar = findViewById(R.id.loadingIcon);
        combinedInputLayout = findViewById(R.id.order_pick_landing_textInputLayout);
        barcodeInput = findViewById(R.id.order_pick_landing_textInput);

        presenter.getOrdersToPick();

        List<OrderPickLandingRecyclerModel> data = new ArrayList<>();
        adapter = new OrderPickLandingAdapter(data, navigator);
        orderRecycler.setAdapter(adapter);
        orderRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Intent createIntent(Context context){
        return new Intent(context, OrderPickLandingActivity.class);
    }

    @Override
    public void clearBarcodeInput(){
        barcodeInput.setText("");
    }

    @Override
    public void getPickslipByScan(String scannedCode) {
        setErrorMessage(null);
        presenter.getPickslipByNumber(scannedCode);
    }

    @Override
    public void onDestroy(){
        presenter.dispose();
        super.onDestroy();
    }

    @Override
    public void onOrderInfoReceived(List<OrderPickLandingRecyclerModel> holder) {
        adapter.setData(holder);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        if(isLoading){
            loadingProgressBar.setVisibility(View.VISIBLE);
        }else{
            loadingProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setErrorMessage(String message) {
        combinedInputLayout.setError(message);
    }
}

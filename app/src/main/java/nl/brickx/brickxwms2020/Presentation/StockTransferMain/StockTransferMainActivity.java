package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoAdapter;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.ProductInfoHolder;

public class StockTransferMainActivity extends DaggerAppCompatActivity implements StockTransferMainContract.View {

    @Inject
    StockTransferMainPresenter infoPresenter;

    private LocationInfoRecyclerModel fromItemLocationData;
    private LocationInfoRecyclerModel toItemLocationData;
    TextInputEditText barcodeInput;
    ProgressBar loadingProgressBar;
    TextInputLayout combinedInputLayout;

    private final String TAG = "StockTransferMain: ";


    public static Intent createIntent(Context context){
        return new Intent(context, StockTransferMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combined_info_and_location_page);

        //Get data from previous activity.
        fromItemLocationData = (LocationInfoRecyclerModel)getIntent().getSerializableExtra("locationInfo");

        barcodeInput = findViewById(R.id.combined_info_textinputEditText);

        //Todo: Remove mock code.
        ProductInfoHolder holder = new ProductInfoHolder();
        initRecyclerViews();

        barcodeInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(barcodeInput.getWindowToken(), 0);
                    onBarcodeScanned();
                    return true;
                }
                return false;
            }
        });
    }


    public void initRecyclerViews(){

    }

    public void onBarcodeScanned(){
//        try{
//            getProductInfoByScan(barcodeInput.getText().toString());
//        }catch (NullPointerException e){
//            setErrorMessage(getString(R.string.product_error_message));
//        }
    }

    @Override
    public void onLocationInfoReceived(LocationInfoRecyclerModel holder) {

    }

    @Override
    public void getProductInfoByScan(String scannedCode){
        setErrorMessage(null);
        infoPresenter.getProductInfoByScan(scannedCode);
    }

    @Override
    public void clearBarcodeInput(){
        barcodeInput.setText("");
    }

    @Override
    public void onDestroy(){
        infoPresenter.dispose();
        super.onDestroy();
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

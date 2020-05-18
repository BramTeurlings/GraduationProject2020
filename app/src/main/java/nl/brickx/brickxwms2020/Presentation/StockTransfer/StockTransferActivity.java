package nl.brickx.brickxwms2020.Presentation.StockTransfer;

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
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.Models.ProductInfoHolder;

public class StockTransferActivity extends DaggerAppCompatActivity implements StockTransferContract.View {

    @Inject
    StockTransferPresenter infoPresenter;

    @Inject
    StockTransferContract.Navigator navigator;

    private final String TAG = "StockTransfer: ";

    SpannableStringBuilder stringBuilder;
    ConstraintLayout barcodesGroup;
    ConstraintLayout detailsGroup;
    FrameLayout propertiesGroup;
    View propertiesClickCatcherView;
    NestedScrollView scrollView;
    TextInputEditText barcodeInput;
    TextView productNameTextView;
    TextView skuTextView;
    TextView stockTextView;
    TextView eanTextView;
    TextView upcTextView;
    TextView customCodeTextView;
    TextView amountPerPackageTextView;
    TextView weightTextView;
    TextInputLayout combinedInputLayout;
    ImageView barcodeArrowImage;
    ImageView detailsArrowImage;
    ImageView propertiesArrowImage;
    MaterialCardView barcodeCardView;
    MaterialCardView detailsCardView;
    MaterialCardView propertiesCardView;
    ProgressBar loadingProgressBar;
    Boolean barcodeExpansionToggle = true;
    Boolean detailsExpansionToggle = true;
    Boolean propertiesExpansionToggle = true;
    StockTransferAdapter productInfoAdapter;
    RecyclerView productInfoRecyclerView;
    RecyclerView locationInfoRecyclerView;
    StockTransferLocationAdapter locationInfoAdapter;

    public static Intent createIntent(Context context){
        return new Intent(context, StockTransferActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_transfer_page);

        eanTextView = findViewById(R.id.combined_codes_ean_content);
        upcTextView = findViewById(R.id.combined_codes_upc_content);
        customCodeTextView = findViewById(R.id.combined_codes_custom_content);
        productNameTextView = findViewById(R.id.combined_name_content);
        skuTextView = findViewById(R.id.combined_sku_content);
        stockTextView = findViewById(R.id.combined_stock_content2);
        amountPerPackageTextView = findViewById(R.id.combined_details_packaging_units_content);
        weightTextView = findViewById(R.id.combined_details_weight_content);
        scrollView = findViewById(R.id.combined_nested_scrollview);
        barcodeInput = findViewById(R.id.combined_info_textinputEditText);
        propertiesClickCatcherView = findViewById(R.id.combined_properties_click_catcher_view);
        barcodesGroup = findViewById(R.id.combined_barcodes_group);
        detailsGroup = findViewById(R.id.combined_details_group);
        propertiesGroup = findViewById(R.id.combined_properties_group);
        barcodeCardView = findViewById(R.id.combined_barcodes_card);
        detailsCardView = findViewById(R.id.combined_details_card);
        propertiesCardView = findViewById(R.id.combined_properties_card);
        barcodeArrowImage = findViewById(R.id.combined_barcodes_arrow_image);
        detailsArrowImage = findViewById(R.id.combined_details_arrow_image);
        propertiesArrowImage = findViewById(R.id.combined_properties_arrow_image);
        productInfoRecyclerView = findViewById(R.id.combined_info_properties_recycler);
        locationInfoRecyclerView = findViewById(R.id.combined_location_recycler);
        combinedInputLayout = findViewById(R.id.combined_info_textInputLayout);
        loadingProgressBar = findViewById(R.id.loadingIcon);

        //Todo: Add listener for recyclerview items to toggle the click.
        barcodeCardView.setOnClickListener(this::onClickBarcodes);
        barcodeCardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        detailsCardView.setOnClickListener(this::onClickDetails);
        detailsCardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        propertiesCardView.setOnClickListener(this::onClickProperties);
        propertiesCardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        //Todo: Make less jank if possible.
        propertiesClickCatcherView.setOnClickListener(this::onClickProperties);

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

        //Todo: Remove mock code.
        ProductInfoHolder holder = new ProductInfoHolder();
        setTextViews(holder);
        initRecyclerViews();
    }

    public void setTextViews(ProductInfoHolder productInfo){
        try{
            productNameTextView.setText(productInfo.getProductName());
            skuTextView.setText(productInfo.getSku());
            stockTextView.setText(String.valueOf(productInfo.getStock().intValue()));

            stringBuilder = new SpannableStringBuilder(getText(R.string.combined_ean) + " " + productInfo.getEan());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, getText(R.string.combined_ean).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            eanTextView.setText(stringBuilder);

            stringBuilder = new SpannableStringBuilder(getText(R.string.combined_upc) + " " + productInfo.getUpc());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, getText(R.string.combined_upc).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            upcTextView.setText(stringBuilder);

            stringBuilder = new SpannableStringBuilder(getText(R.string.combined_custom_barcode) + " " + productInfo.getCustomBarcode());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, getText(R.string.combined_custom_barcode).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            customCodeTextView.setText(stringBuilder);

            stringBuilder = new SpannableStringBuilder(getText(R.string.combined_packaging_units_text) + " " + productInfo.getAmountPerPackage());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, getText(R.string.combined_packaging_units_text).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            amountPerPackageTextView.setText(stringBuilder);

            stringBuilder = new SpannableStringBuilder(getText(R.string.combined_weight_text) + " " + productInfo.getWeight());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, getText(R.string.combined_weight_text).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            weightTextView.setText(stringBuilder);
        }catch (NullPointerException e){
            Log.e(TAG, "Unable to load resources as api returned uncaught null value on ProductInfoHolder object.");
        }
    }

    public void initRecyclerViews(){
        //Productinfo
        productInfoAdapter = new StockTransferAdapter(new ArrayList<>());
        productInfoRecyclerView.setAdapter(productInfoAdapter);
        productInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Location:
        locationInfoAdapter = new StockTransferLocationAdapter(new ArrayList<>(), navigator, this);
        locationInfoRecyclerView.setAdapter(locationInfoAdapter);
        locationInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBarcodeScanned(){
        try{
            getProductInfoByScan(barcodeInput.getText().toString());
        }catch (NullPointerException e){
            setErrorMessage(getString(R.string.product_error_message));
        }
    }

    @Override
    public void getProductInfoByScan(String scannedCode){
        setErrorMessage(null);
        infoPresenter.getProductInfoByScan(scannedCode);
    }

    public void onClickBarcodes(View view){
        if(barcodeExpansionToggle){
            barcodesGroup.setVisibility(View.VISIBLE);
            barcodeArrowImage.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
            barcodeExpansionToggle = false;
        }else{
            barcodesGroup.setVisibility(View.GONE);
            barcodeArrowImage.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
            barcodeExpansionToggle = true;
        }
    }

    public void onClickDetails(View view){
        if(detailsExpansionToggle){
            detailsGroup.setVisibility(View.VISIBLE);
            detailsArrowImage.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
            detailsExpansionToggle = false;
        }else{
            detailsGroup.setVisibility(View.GONE);
            detailsArrowImage.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
            detailsExpansionToggle = true;
        }
    }

    public void onClickProperties(View view){
        if(propertiesExpansionToggle){
            if(productInfoAdapter.getItemCount() > 0){
                propertiesGroup.setVisibility(View.VISIBLE);
            }
            propertiesArrowImage.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
            propertiesExpansionToggle = false;
        }else{
            propertiesGroup.setVisibility(View.GONE);
            propertiesArrowImage.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
            propertiesExpansionToggle = true;
        }
    }

    @Override
    public void onNewProductInfoReceived(ProductInfoHolder holder, String scan) {
        locationInfoAdapter.setData(holder.getLocations(), scan);
        productInfoAdapter.setData(holder.getProperties());
        locationInfoAdapter.notifyDataSetChanged();
        productInfoAdapter.notifyDataSetChanged();
        setTextViews(holder);
        if(productInfoAdapter.getItemCount() < 1){
            propertiesExpansionToggle = false;
            onClickProperties(propertiesCardView);
        }
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Todo: This is a bit jank.
                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.smoothScrollTo(0,0);
                    }
                }, 600);
            }
        });
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
    public void onPause(){
        infoPresenter.dispose();
        super.onPause();
    }

    @Override
    public void onResume(){
        infoPresenter.registerDatawedgeReceiver();
        super.onResume();
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

    @Override
    public void onSerialnumbersFetched(Serialnumbers serialnumbers, int stockLocationId, int productId) {
        locationInfoAdapter.setSerialnumbers(productId, stockLocationId, serialnumbers.getGetAvailableBatchNumbersResult());
    }
}

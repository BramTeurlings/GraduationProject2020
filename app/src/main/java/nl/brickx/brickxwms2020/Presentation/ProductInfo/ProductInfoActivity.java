package nl.brickx.brickxwms2020.Presentation.ProductInfo;

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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoAdapter;
import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoPresenter;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.ProductInfoRecyclerModel;

public class ProductInfoActivity extends DaggerAppCompatActivity implements ProductInfoContract.View {

    @Inject
    ProductInfoPresenter infoPresenter;

    @Inject
    LocationInfoPresenter locationPresenter;

    private final String TAG = "ProductInfoActivity: ";

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
    ImageView barcodeArrowImage;
    ImageView detailsArrowImage;
    ImageView propertiesArrowImage;
    MaterialCardView barcodeCardView;
    MaterialCardView detailsCardView;
    MaterialCardView propertiesCardView;
    Boolean barcodeExpansionToggle = true;
    Boolean detailsExpansionToggle = true;
    Boolean propertiesExpansionToggle = true;
    ProductInfoAdapter productInfoAdapter;
    RecyclerView productInfoRecyclerView;
    RecyclerView locationInfoRecyclerView;
    LocationInfoAdapter recyclerViewAdapter;

    public static Intent createIntent(Context context){
        return new Intent(context, ProductInfoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combined_info_and_location_page);

        //Todo: Currently unsused.
        eanTextView = findViewById(R.id.combined_codes_ean_content);
        upcTextView = findViewById(R.id.combined_codes_upc_content);
        customCodeTextView = findViewById(R.id.combined_codes_custom_content);
        productNameTextView = findViewById(R.id.combined_name_content);
        skuTextView = findViewById(R.id.combined_sku_content);
        stockTextView = findViewById(R.id.combined_stock_content);
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

        //Todo: Add listener for recyclerview items to toggle the click.
        barcodeCardView.setOnClickListener(this::onClickBarcodes);
        barcodeCardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        detailsCardView.setOnClickListener(this::onClickDetails);
        detailsCardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        propertiesCardView.setOnClickListener(this::onClickProperties);
        propertiesCardView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        //Todo: Make less jank if possible.
        propertiesClickCatcherView.setOnClickListener(this::onClickProperties);

        barcodeInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onBarcodeScanned();
                    return true;
                }
                return false;
            }
        });

        //Todo: Remove mock code.
        ProductInfoHolder holder = new ProductInfoHolder("Smartphone", "HASV412612VSDHAW", 10d, "AUSVDWQYD12314", "219846718238712", "80003123", 1d, "300g");
        setTextViews(holder);
        initRecyclerViews();
    }

    public void setTextViews(ProductInfoHolder productInfo){
        try{
            productNameTextView.setText(productInfo.getProductName());
            skuTextView.setText(productInfo.getSku());
            //stockTextView.setText(productInfo.getStock());

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
        //Todo: get proper data from domain.
        List<ProductInfoRecyclerModel> data  = new ArrayList<>();
        data.add(new ProductInfoRecyclerModel("Garantie", "2 jaar"));
        data.add(new ProductInfoRecyclerModel("Lengte kabels", "1.5 M"));
        data.add(new ProductInfoRecyclerModel("Aantal per doos", "20"));
        data.add(new ProductInfoRecyclerModel("Waterdichtheid", "IP68"));
        data.add(new ProductInfoRecyclerModel("Ingangsstroom template tekst omdat ik wil zien hoe het veld om gaat met grote hoeveelheden aan woorden en langere zinnen ect ect ect.", "12V"));

        productInfoAdapter = new ProductInfoAdapter(data);
        productInfoRecyclerView.setAdapter(productInfoAdapter);
        productInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Location:
        //Todo: Get data from domain layer and data layer and bind to the correct adapter.
        recyclerViewAdapter = new LocationInfoAdapter(locationPresenter.getProductsByLocation("mockLocationCode"), "mockLocationCode");

        getProductInfoByScan("AE00872");

        locationInfoRecyclerView.setAdapter(recyclerViewAdapter);
        locationInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBarcodeScanned(){
        try{
            getProductInfoByScan(barcodeInput.getText().toString());
        }catch (NullPointerException e){
            //Todo: Tell user to fill in a code.
        }
    }

    private void getProductInfoByScan(String scannedCode){
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
            if(recyclerViewAdapter.getItemCount() > 0){
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
    public void onNewProductInfoReceived(ProductInfoHolder holder) {
        productInfoAdapter.setData(holder.getProperties());
        productInfoAdapter.notifyDataSetChanged();
        setTextViews(holder);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Todo: This is a bit jank.
                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                scrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                }, 500);
            }
        });

    }
}

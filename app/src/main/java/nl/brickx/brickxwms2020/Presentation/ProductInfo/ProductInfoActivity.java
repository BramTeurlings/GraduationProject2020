package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.card.MaterialCardView;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;

public class ProductInfoActivity extends DaggerAppCompatActivity {

    ConstraintLayout barcodesGroup;
    ConstraintLayout detailsGroup;
    TextView eanTextView;
    TextView upcTextView;
    TextView customCodeTextView;
    ImageView barcodeArrowImage;
    ImageView detailsArrowImage;
    MaterialCardView barcodeCardView;
    MaterialCardView detailsCardView;
    Boolean barcodeExpansionToggle = true;
    Boolean detailsExpansionToggle = true;

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

        barcodesGroup = findViewById(R.id.combined_barcodes_group);
        detailsGroup = findViewById(R.id.combined_details_group);
        barcodeCardView = findViewById(R.id.combined_barcodes_card);
        detailsCardView = findViewById(R.id.combined_details_card);
        barcodeArrowImage = findViewById(R.id.combined_barcodes_arrow_image);
        detailsArrowImage = findViewById(R.id.combined_details_arrow_image);

        barcodeCardView.setOnClickListener(this::onClickBarcodes);
        detailsCardView.setOnClickListener(this::onClickDetails);
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
}

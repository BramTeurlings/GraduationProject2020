package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;

public class ProductInfoActivity extends DaggerAppCompatActivity {

    public static Intent createIntent(Context context){
        return new Intent(context, ProductInfoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_info_page);
    }
}

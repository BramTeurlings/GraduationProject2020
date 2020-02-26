package nl.brickx.brickxwms2020.Presentation.MainMenu;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoActivity;
import nl.brickx.brickxwms2020.Presentation.ProductInfo.ProductInfoActivity;
import nl.brickx.data.dagger.DataContext;

public class MainMenuNavigator implements MainMenuContract.Navigator {

    private Activity activity;
    private Context context;

    @Inject
    MainMenuNavigator(Activity activity, @DataContext Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void navigateToProductInfo() {
        activity.startActivity(ProductInfoActivity.createIntent(context));
    }

    @Override
    public void navigateToLocationInfo() {
        activity.startActivity(LocationInfoActivity.createIntent(context));
    }
}

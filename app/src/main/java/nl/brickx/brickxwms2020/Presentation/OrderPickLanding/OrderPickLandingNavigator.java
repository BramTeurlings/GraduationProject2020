package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoActivity;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuContract;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivity;
import nl.brickx.brickxwms2020.Presentation.ProductInfo.ProductInfoActivity;
import nl.brickx.data.Dagger.DataContext;

public class OrderPickLandingNavigator implements OrderPickLandingContract.Navigator {

    private Activity activity;
    private Context context;

    @Inject
    OrderPickLandingNavigator(Activity activity, @DataContext Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void navigateToOrder(Context context, String orderId) {
        activity.startActivity(OrderPickActivity.createIntent(context, orderId));
    }
}

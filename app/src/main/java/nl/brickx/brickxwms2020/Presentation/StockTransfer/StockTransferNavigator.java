package nl.brickx.brickxwms2020.Presentation.StockTransfer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import javax.inject.Inject;
import nl.brickx.brickxwms2020.Presentation.StockTransferMain.StockTransferMainActivity;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public class StockTransferNavigator implements StockTransferContract.Navigator {

    private Activity activity;
    private Context context;
    private Intent stockTransferMainIntent;

    @Inject
    StockTransferNavigator(Activity activity, @DataContext Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void navigateToTransferScreen(LocationInfoRecyclerModel locationInfoRecyclerModel) {
        stockTransferMainIntent = StockTransferMainActivity.createIntent(context);
        stockTransferMainIntent.putExtra("locationInfo", locationInfoRecyclerModel);
        activity.startActivity(stockTransferMainIntent);
    }
}

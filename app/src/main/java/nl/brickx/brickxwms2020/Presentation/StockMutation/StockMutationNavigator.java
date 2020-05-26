package nl.brickx.brickxwms2020.Presentation.StockMutation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import javax.inject.Inject;
import nl.brickx.brickxwms2020.Presentation.StockMutationMain.StockMutationMainActivity;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public class StockMutationNavigator implements StockMutationContract.Navigator {

    private Activity activity;
    private Context context;
    private Intent stockTransferMainIntent;

    @Inject
    StockMutationNavigator(Activity activity, @DataContext Context context){
        this.activity = activity;
        this.context = context;
    }


    @Override
    public void navigateToMutationScreen(LocationInfoRecyclerModel locationInfoRecyclerModel) {
        stockTransferMainIntent = StockMutationMainActivity.createIntent(context);
        stockTransferMainIntent.putExtra("locationInfo", locationInfoRecyclerModel);
        activity.startActivity(stockTransferMainIntent);
    }
}

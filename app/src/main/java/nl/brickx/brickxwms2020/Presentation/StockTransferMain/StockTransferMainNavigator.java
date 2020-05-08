package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.StockTransferDto;

public class StockTransferMainNavigator implements StockTransferMainContract.Navigator {

    private Activity activity;
    private Context context;
    Intent stockTransferMainIntent;

    @Inject
    StockTransferMainNavigator(Activity activity, @DataContext Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void navigateToTransferScreen(StockTransferDto transferDto) {

    }
}

package nl.brickx.brickxwms2020.Presentation.StockMutationMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuActivity;
import nl.brickx.data.Dagger.DataContext;

public class StockMutationMainNavigator implements StockMutationMainContract.Navigator {

    private Activity activity;
    private Context context;
    Intent stockTransferMainIntent;

    @Inject
    StockMutationMainNavigator(Activity activity, @DataContext Context context){
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void navigateToMainMenu() {
        Intent intent = MainMenuActivity.createIntent(context);
        intent.putExtra("message", "Voorraad gemuteerd.");
        activity.startActivity(intent);
    }
}

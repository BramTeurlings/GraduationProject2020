package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragmentContract;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.OrderPick.Main.GetPickSlipByOrderNumber;
import nl.brickx.domain.OrderPick.Main.GetProductImageByNumber;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class OrderPickOverviewFragmentPresenter implements OrderPickOverviewFragmentContract.Presenter {

    Context context;
    Boolean isLoading = false;
    private List<Disposable> disposables = new ArrayList<>();
    private UserDataManager userDataManager;

    @Inject
    public OrderPickOverviewFragmentPresenter(@DataContext Context context, GetPickSlipByOrderNumber getPickSlipByOrderNumber, GetProductImageByNumber getProductImageByNumber){
        this.context = context;
    }

    @Override
    public void loadPickSlipData() {

    }

    private void onApiRequestStarted(){
        isLoading = true;
    }

    private void onApiRequestCompleted(){
        isLoading = false;
    }

    private void changeLoadingState(){
        //view.changeLoadingState(isLoading);
    }

    private void onLoginFailed(Throwable throwable){
//        throwable.printStackTrace();
//        onApiRequestCompleted();
//        changeLoadingState();
//        view.setErrorMessage(context.getString(R.string.product_error_message));
    }

    @Override
    public void dispose() {
//        for(int i = 0; i < disposables.size(); i++){
//            this.disposables.get(i).dispose();
//        }
//
//        try{
//            context.unregisterReceiver(textInputBroadcastReceiver);
//        }catch (Exception e){
//            Log.e(TAG, "Unable to unsubscribe broadcast receiver.");
//        }
    }

    private void printErrorMessage(String missingObject){
        Log.e(TAG, "Product " + missingObject + " not present.");
    }
}

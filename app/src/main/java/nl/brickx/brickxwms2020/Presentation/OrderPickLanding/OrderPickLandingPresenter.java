package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;
import nl.brickx.domain.Models.OrderPickLandingRecyclerModel;
import nl.brickx.domain.Models.OrderPickLandingStatus;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.OrderPick.Landing.GetOrdersToPickByKey;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class OrderPickLandingPresenter implements OrderPickLandingContract.Presenter {

    Context context;
    Boolean isLoading = false;
    OrderPickLandingContract.View view;
    private List<Disposable> disposables = new ArrayList<>();
    private GetOrdersToPickByKey getOrdersToPickByKey;
    private UserDataManager userDataManager;

    @Inject
    public OrderPickLandingPresenter(@DataContext Context context, OrderPickLandingContract.View view, GetOrdersToPickByKey getOrdersToPickByKey, UserDataManager userDataManager){
        this.context = context;
        this.view = view;
        this.getOrdersToPickByKey = getOrdersToPickByKey;
        this.userDataManager = userDataManager;
    }

    @Override
    public void getOrdersToPick() {
        if(!isLoading){
            onApiRequestStarted();
            changeLoadingState();
            final List<OrderPickLanding> result = new ArrayList<>();
            System.out.println(new Date());
            this.disposables.add(getOrdersToPickByKey.invoke(getUserData().getApiKey())
                    .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( s -> result.add(s),
                            t -> onLoginFailed(t),
                            () -> onProductInfoFetched(result)));
        }
    }

    @Override
    public void getPickslipByNumber(String number) {
        //Todo:
    }

    public User getUserData() {
        return userDataManager.GetUserData();
    }

    private void onProductInfoFetched(List<OrderPickLanding> productInformations){
        List<OrderPickLandingRecyclerModel> result = new ArrayList<>();

        for(int i = 0; i < productInformations.get(0).getGetPickslipListResult().size(); i++){
            OrderPickLandingRecyclerModel tempRecyclerModel = new OrderPickLandingRecyclerModel();

            try{
                if(productInformations.get(0).getGetPickslipListResult().get(i).getPickSlipDate() != null){
                    tempRecyclerModel.setOrderDate(productInformations.get(0).getGetPickslipListResult().get(i).getPickSlipDate());
                }else{
                    printErrorMessage("Order Date");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Order Date");
            }

            try{
                if(productInformations.get(0).getGetPickslipListResult().get(i).getId() != null){
                    tempRecyclerModel.setOrderId(productInformations.get(0).getGetPickslipListResult().get(i).getId());
                }else{
                    printErrorMessage("Id");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Id");
            }

            try{
                if(productInformations.get(0).getGetPickslipListResult().get(i).getPickSlipNumber() != null){
                    tempRecyclerModel.setOrderName(productInformations.get(0).getGetPickslipListResult().get(i).getPickSlipNumber());
                }else{
                    printErrorMessage("Pickslip Number");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Pickslip Number");
            }

            tempRecyclerModel.setOrderStatus(OrderPickLandingStatus.FREE);
            result.add(tempRecyclerModel);
        }

        onApiRequestCompleted();
        changeLoadingState();
        view.onOrderInfoReceived(result);
    }

    private BroadcastReceiver textInputBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(context.getResources().getString(R.string.datawedge_intent_filter_action))) {
                //  Received a barcode scan
                try {
                    //Execute View code.
                    view.clearBarcodeInput();
                    view.getPickslipByScan(intent.getStringExtra(context.getResources().getString(R.string.datawedge_intent_key_data)).replace("\n", ""));
                } catch (Exception e) {
                    Log.i(TAG, "Unable to read data from scanner.");
                }
            }
        }
    };

    private void onApiRequestStarted(){
        isLoading = true;
    }

    private void onApiRequestCompleted(){
        isLoading = false;
    }

    private void changeLoadingState(){
        view.changeLoadingState(isLoading);
    }

    private void onLoginFailed(Throwable throwable){
        throwable.printStackTrace();
        onApiRequestCompleted();
        changeLoadingState();
        view.setErrorMessage(context.getString(R.string.product_error_message));
    }

    @Override
    public void dispose() {
        for(int i = 0; i < disposables.size(); i++){
            this.disposables.get(i).dispose();
        }

        try{
            context.unregisterReceiver(textInputBroadcastReceiver);
        }catch (Exception e){
            Log.e(TAG, "Unable to unsubscribe broadcast receiver.");
        }
    }

    private void printErrorMessage(String missingObject){
        Log.e(TAG, "Product " + missingObject + " not present.");
    }

}

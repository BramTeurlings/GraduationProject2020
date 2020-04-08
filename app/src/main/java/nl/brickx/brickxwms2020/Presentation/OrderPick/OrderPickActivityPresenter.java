package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragmentContract;
import nl.brickx.brickxwms2020.Presentation.OrderPickLanding.OrderPickLandingContract;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.OrderPick.Main.GetPickSlipByOrderNumber;
import nl.brickx.domain.OrderPick.Main.GetProductImageByNumber;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class OrderPickActivityPresenter implements OrderPickActivityContract.Presenter {

    Context context;
    Boolean isLoading = false;
    private List<Disposable> disposables = new ArrayList<>();
    OrderPickActivityContract.View view;
    private UserDataManager userDataManager;
    private GetPickSlipByOrderNumber getPickSlipByOrderNumber;
    private GetProductImageByNumber getProductImageByNumber;

    @Inject
    public OrderPickActivityPresenter(@DataContext Context context, OrderPickActivityContract.View view, GetPickSlipByOrderNumber getPickSlipByOrderNumber, GetProductImageByNumber getProductImageByNumber, UserDataManager userDataManager){
        this.context = context;
        this.getPickSlipByOrderNumber = getPickSlipByOrderNumber;
        this.getProductImageByNumber = getProductImageByNumber;
        this.userDataManager = userDataManager;
        this.view = view;
    }

    @Override
    public void getDataForFragments(String orderNumber) {
        onApiRequestStarted();
        changeLoadingState();
        final List<OrderPickSlip> result = new ArrayList<>();
        System.out.println(new Date());
        this.disposables.add(getPickSlipByOrderNumber.invoke(orderNumber, getUserData().getApiKey())
                .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( s -> result.add(s),
                        t -> onLoginFailed(t),
                        () -> onProductInfoFetched(result)));
    }
    private void onProductInfoFetched(List<OrderPickSlip> pickSlips) {

    }

    public User getUserData() {
        return userDataManager.GetUserData();
    }

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
    }

    private void printErrorMessage(String missingObject){
        Log.e(TAG, "Product " + missingObject + " not present.");
    }
}

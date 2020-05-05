package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.BatchNumberSelectionModelDto;
import nl.brickx.domain.Models.SaveReservationLocationDto;
import nl.brickx.domain.Models.SavePickSlipDto;
import nl.brickx.domain.Models.SavePickSlipLineDto;
import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.OrderPick.Main.CloseOrderPick;
import nl.brickx.domain.OrderPick.Main.GetPickSlipByOrderNumber;
import nl.brickx.domain.OrderPick.Main.GetProductImageByNumber;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class OrderPickOverviewFragmentPresenter implements OrderPickOverviewFragmentContract.Presenter {

    Context context;
    Boolean isLoading = false;
    private List<Disposable> disposables = new ArrayList<>();
    private CloseOrderPick closeOrderPick;
    private UserDataManager userDataManager;
    private SavePickSlipDto closeOrderPickModel;
    private SavePickSlipLineDto tempClosePickSlipLineModels = new SavePickSlipLineDto();
    private List<SaveReservationLocationDto> tempClosePickLocationModels = new ArrayList<>();
    private SaveReservationLocationDto tempClosePickLocationModel = new SaveReservationLocationDto();
    private List<BatchNumberSelectionModelDto> tempBatchNumberSelectionModels = new ArrayList<>();
    private BatchNumberSelectionModelDto tempBatchNumberSelectionModel = new BatchNumberSelectionModelDto();

    @Inject
    public OrderPickOverviewFragmentPresenter(@DataContext Context context, GetPickSlipByOrderNumber getPickSlipByOrderNumber, GetProductImageByNumber getProductImageByNumber, CloseOrderPick closeOrderPick, UserDataManager userDataManager){
        this.context = context;
        this.closeOrderPick = closeOrderPick;
        this.userDataManager = userDataManager;
    }

    @Override
    public void loadPickSlipData() {

    }

    @Override
    public void closeOrderPick(List<OrderPickPickListModel> data) {
        closeOrderPickModel = new SavePickSlipDto();
        if(data.size() > 0){

            for(int i = 0; i < data.size(); i++){
                tempClosePickLocationModels = new ArrayList<>();
                tempBatchNumberSelectionModels = new ArrayList<>();


                for(int i2 = 0; i2 < data.get(i).getScannedSerialNumbers().size(); i2++){
                    tempBatchNumberSelectionModel = new BatchNumberSelectionModelDto(data.get(i).getScannedSerialNumbers().get(i2));
                    tempBatchNumberSelectionModels.add(tempBatchNumberSelectionModel);
                }

                tempClosePickLocationModel = new SaveReservationLocationDto(data.get(i).getPicklistId(), (double)data.get(i).getQuantityPicked(), tempBatchNumberSelectionModels);
                tempClosePickLocationModels.add(tempClosePickLocationModel);
                tempClosePickSlipLineModels = new SavePickSlipLineDto(data.get(i).getPickSlipLineId(), tempClosePickLocationModels);
                closeOrderPickModel.addClosePickSlipLineModel(tempClosePickSlipLineModels);
            }

            closeOrderPickModel.setId(data.get(0).getPickSlipId());
            //Todo: Make actual check for doPrint.
            closeOrderPickModel.setDoPrint(false);
            AtomicReference<Boolean> result = new AtomicReference<Boolean>();

            disposables.add(closeOrderPick.invoke(closeOrderPickModel, getUserData().getApiKey())
                    .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                    .subscribeOn(Schedulers.io())
                    .subscribe(result::set,
                            this::onGetApiDataFailed,
                            () -> onOrderPickClosed(result.get())));
        }else{
            //Todo: Alert that order pick is empty.
        }

    }

    private void onOrderPickClosed(Boolean result){

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

    private void onGetApiDataFailed(Throwable throwable){
        throwable.printStackTrace();
        onApiRequestCompleted();
        changeLoadingState();
        //Todo: Display error message.
        //view.setErrorMessage(context.getString(R.string.product_error_message));
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

    public User getUserData() {
        return userDataManager.GetUserData();
    }
}

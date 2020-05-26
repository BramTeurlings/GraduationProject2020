package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Location.Info.GetLocationInfoByScan;
import nl.brickx.domain.Models.Gson.LocationInfo.LocationInfo;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class LocationInfoPresenter implements LocationInfoContract.Presenter {

    private GetLocationInfoByScan getLocationInfoByScan;
    private UserDataManager userDataManager;
    private LocationInfoRecyclerModel tempLocationInfoRecyclerModel = new LocationInfoRecyclerModel();
    private LocationInfoContract.View view;
    private List<Disposable> disposables = new ArrayList<>();
    private Context context;
    private Boolean isLoading = false;

    @Inject
    LocationInfoPresenter(GetLocationInfoByScan getLocationInfoByScan, UserDataManager userDataManager, LocationInfoContract.View view, @DataContext Context context){
        this.getLocationInfoByScan = getLocationInfoByScan;
        this.userDataManager = userDataManager;
        this.view = view;
        this.context = context;

        //Datawedge
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(context.getString(R.string.datawedge_intent_filter_action));
        context.registerReceiver(textInputBroadcastReceiver, filter);
    }

    @Override
    public void getLocationInfoByScan(String locationCode) {
        if(!isLoading){
            onApiRequestStarted();
            changeLoadingState();
            List<LocationInfo> result = new ArrayList<>();
            disposables.add(getLocationInfoByScan.invoke(locationCode, userDataManager.GetUserData().getApiKey())
                    .doOnNext(c -> Log.i(TAG, "processing item on thread " + Thread.currentThread().getName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result::add,
                            this::onLoginFailed,
                            () -> onLocationInfoFetched(result)));
        }
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

    @Override
    public void buildLocationTag(String locationName){
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(context.getText(R.string.location_info_name_title) + " " + locationName);
        stringBuilder.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), 0, context.getText(R.string.location_info_name_title).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setLocationTag(stringBuilder);
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
                    view.getLocationInfoByScan(intent.getStringExtra(context.getResources().getString(R.string.datawedge_intent_key_data)).replace("\n", ""));
                } catch (Exception e) {
                    Log.i(TAG, "Unable to read data from scanner.");
                }
            }
        }
    };

    private void onLocationInfoFetched(List<LocationInfo> locationInfos){
        //Todo: Could add more data here if we want to. Also possibly not safe because values might be null.
        List<LocationInfoRecyclerModel> locationData = new ArrayList<>();
        if(locationInfos.get(0).getGetWmsLocationViewForLocationResult() != null){
            for(int i = 0; i < locationInfos.get(0).getGetWmsLocationViewForLocationResult().size(); i++){
                tempLocationInfoRecyclerModel = new LocationInfoRecyclerModel();
                if(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getWareHouseName() != null){
                    tempLocationInfoRecyclerModel.setWarehouseName(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getWareHouseName());
                }
                if(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getLocationName() != null){
                    tempLocationInfoRecyclerModel.setLocation(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getLocationName());
                }
                if(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getCurrentStock() != null){
                    tempLocationInfoRecyclerModel.setProductStock(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getCurrentStock().intValue());
                }
                if(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getName() != null){
                    tempLocationInfoRecyclerModel.setProductName(locationInfos.get(0).getGetWmsLocationViewForLocationResult().get(i).getName());
                }
                locationData.add(tempLocationInfoRecyclerModel);
            }
        }

        onApiRequestCompleted();
        changeLoadingState();
        view.onLocationInfoReceived(locationData);
        if(locationData.size() > 0){
            buildLocationTag(locationData.get(0).getLocation());
        }else{
            buildLocationTag("");
        }
    }

    private void onLoginFailed(Throwable throwable){
        Log.e(TAG, Objects.requireNonNull(throwable.getLocalizedMessage()));
        onApiRequestCompleted();
        changeLoadingState();
        view.setErrorMessage(context.getString(R.string.location_error_message));
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
}

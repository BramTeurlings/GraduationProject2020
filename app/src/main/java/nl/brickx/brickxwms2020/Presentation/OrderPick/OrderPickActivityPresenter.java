package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Base64;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.Models.OrderPickPickListModel;
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
    private HandlerThread backgroundHandlerThread;
    private Handler handler;


    @Inject
    public OrderPickActivityPresenter(@DataContext Context context, OrderPickActivityContract.View view, GetPickSlipByOrderNumber getPickSlipByOrderNumber, GetProductImageByNumber getProductImageByNumber, UserDataManager userDataManager){
        this.context = context;
        this.getPickSlipByOrderNumber = getPickSlipByOrderNumber;
        this.getProductImageByNumber = getProductImageByNumber;
        this.userDataManager = userDataManager;
        this.view = view;
        this.handler = new Handler();
        this.backgroundHandlerThread = new HandlerThread("backgroundThread");
        backgroundHandlerThread.start();


        //Datawedge
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(context.getString(R.string.datawedge_intent_filter_action));
        context.registerReceiver(textInputBroadcastReceiver, filter);
    }

    private BroadcastReceiver textInputBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(context.getResources().getString(R.string.datawedge_intent_filter_action))) {
                //  Received a barcode scan
                try {
                    //Execute View code.
                    view.onBarcodeScanned(intent.getStringExtra(context.getResources().getString(R.string.datawedge_intent_key_data)).replace("\n", ""));
                } catch (Exception e) {
                    Log.i(TAG, "Unable to read data from scanner.");
                }
            }
        }
    };

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
                        t -> onGetApiDataFailed(t),
                        () -> onProductInfoFetched(result)));
    }

    @Override
    public void getImageDataForFragments(List<OrderPickPickListModel> data) {
        onApiRequestStarted();
        AtomicReference<ProductImage> result = new AtomicReference<ProductImage>(new ProductImage());

        for(int i = 0; i < data.size(); i++){
            System.out.println(new Date());
            int index = i;
            this.disposables.add(getProductImageByNumber.invoke(String.valueOf(data.get(i).getProductId()), getUserData().getApiKey())
                    .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result::set,
                            this::onGetApiDataFailed,
                            () -> onProductImageFetched(result.get(), data.get(index).getProductId())));
        }
    }

    private void onProductInfoFetched(List<OrderPickSlip> pickSlips) {
        List<OrderPickPickListModel> fragmentData = new ArrayList<>();

        for(int i = 0; i < pickSlips.get(0).getGetPickslipByNumberResult().getPickList().size(); i++){
            OrderPickPickListModel tempModel = new OrderPickPickListModel();

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getId() != null){
                    tempModel.setId(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getId());
                }else{
                    printErrorMessage("Picklist Id");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Picklist Id");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getQuantity() != null){
                    tempModel.setQuantityRequired(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getQuantity().intValue());
                }else{
                    printErrorMessage("Quantity");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Quantity");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getProductInfo().getCode() != null){
                    tempModel.setProductSku(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getProductInfo().getCode());
                }else{
                    printErrorMessage("Sku");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Sku");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getProductInfo().getId() != null){
                    tempModel.setProductId(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getProductInfo().getId());
                }else{
                    printErrorMessage("Product Id");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Product Id");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getProductInfo().getName() != null){
                    tempModel.setProductName(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getProductInfo().getName());
                }else{
                    printErrorMessage("Product Name");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Product Name");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getCurrentStock() != null){
                    tempModel.setCurrentStock(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getCurrentStock().intValue());
                }else{
                    printErrorMessage("Current Stock");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Current Stock");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getWareHouseLocation().getLocationName() != null){
                    tempModel.setProductLocation(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getWareHouseLocation().getLocationName());
                }else{
                    printErrorMessage("Location");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Location");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getWareHouseLocation().getScanLocationTag() != null){
                    tempModel.setLocationTag(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getWareHouseLocation().getScanLocationTag());
                }else{
                    printErrorMessage("Location Tag");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Location Tag");
            }

            try{
                if(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getWareHouseName() != null){
                    tempModel.setWarehouseName(pickSlips.get(0).getGetPickslipByNumberResult().getPickList().get(i).getStockLocation().getWareHouseName());
                }else{
                    printErrorMessage("Warehouse Name");
                }
            }catch(NullPointerException | IndexOutOfBoundsException e){
                printErrorMessage("Warehouse Name");
            }

            fragmentData.add(tempModel);
        }

        onApiRequestCompleted();
        changeLoadingState();
        view.onPickListInfoReceived(fragmentData);
    }

    private void onProductImageFetched(ProductImage productImage, int productId){
        handler = new Handler(backgroundHandlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try{
                     view.runImageUpdateOnUiThread(new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(Base64.decode(productImage.getGetImageByProductIdResult().getBase64Array(), Base64.DEFAULT), 0, Base64.decode(productImage.getGetImageByProductIdResult().getBase64Array(), Base64.DEFAULT).length)), productId);
                }catch (NullPointerException e){
                    Log.e(TAG, "Unable to parse Base64 image. Image not present!");
                }

            }
        });

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

    private void onGetApiDataFailed(Throwable throwable){
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
package nl.brickx.brickxwms2020.Presentation.StockTransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.ProductInfoRecyclerModel;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.OrderPick.Main.GetSerialnumberByStockLocationIdAndProductId;
import nl.brickx.domain.Product.Info.GetProductInfoByScan;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class StockTransferPresenter implements StockTransferContract.Presenter {

    private UserDataManager userDataManager;
    private GetProductInfoByScan getProductInfoByScan;
    private GetSerialnumberByStockLocationIdAndProductId getSerialnumberByStockLocationIdAndProductId;
    private ProductInfoRecyclerModel tempProductInfoRecyclerModel = new ProductInfoRecyclerModel();
    private LocationInfoRecyclerModel tempLocationInfoRecyclerModel = new LocationInfoRecyclerModel();
    private StockTransferContract.View view;
    private BroadcastReceiver textInputBroadcastReceiver;
    private List<Disposable> disposables = new ArrayList<>();
    private Context context;
    private Boolean isLoading = false;


    @Inject
    StockTransferPresenter(UserDataManager userDataManager, GetProductInfoByScan getProductInfoByScan, StockTransferContract.View view, @DataContext Context context, GetSerialnumberByStockLocationIdAndProductId getSerialnumberByStockLocationIdAndProductId){
        this.userDataManager = userDataManager;
        this.getProductInfoByScan = getProductInfoByScan;
        this.view = view;
        this.context = context;
        this.getSerialnumberByStockLocationIdAndProductId = getSerialnumberByStockLocationIdAndProductId;
    }

    @Override
    public void registerDatawedgeReceiver() {
        textInputBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(context.getResources().getString(R.string.datawedge_intent_filter_action))) {
                    //  Received a barcode scan
                    try {
                        //Execute View code.
                        view.clearBarcodeInput();
                        view.getProductInfoByScan(intent.getStringExtra(context.getResources().getString(R.string.datawedge_intent_key_data)).replace("\n", ""));
                    } catch (Exception e) {
                        Log.i(TAG, "Unable to read data from scanner.");
                    }
                }
            }
        };
        //Datawedge
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(context.getString(R.string.datawedge_intent_filter_action));
        context.registerReceiver(textInputBroadcastReceiver, filter);
    }

    @Override
    public void getProductInfoByScan(String scan) {
        if(!isLoading){
            onApiRequestStarted();
            changeLoadingState();
            final List<ProductInformation> result = new ArrayList<>();
            Log.i(TAG, new Date().toString());
            this.disposables.add(getProductInfoByScan.invoke(scan, getUserData().getApiKey())
                    .doOnNext(c -> Log.i(TAG,"processing item on thread " + Thread.currentThread().getName()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result::add,
                            this::onGetApiDataFailed,
                            () -> onProductInfoFetched(result, scan)));
        }
    }

    @Override
    public User getUserData() {
        return userDataManager.GetUserData();
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

    private void onProductInfoFetched(List<ProductInformation> productInformations, String scan){
        Log.i(TAG, new Date().toString());
        ProductInfoHolder infoHolder = new ProductInfoHolder();
        ProductInformation result = productInformations.get(0);

        //Todo: Return error message to user if this code throws an error (on getCurrentStock().get(0).
        try{
            if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getName() != null){
                infoHolder.setProductName(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getName());
            }else{
                printErrorMessage("Name");
            }
        }catch(NullPointerException | IndexOutOfBoundsException e){
            printErrorMessage("Name");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getCurrentStock() != null){
                infoHolder.setStock(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getCurrentStock());
            }else{
                printErrorMessage("Stock");
            }
        }catch (NullPointerException | IndexOutOfBoundsException e){
            printErrorMessage("Stock");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getCode() != null){
                infoHolder.setSku(result.getGetProductsCompleteByScanCodeResult().getCode());
            }else{
                printErrorMessage("SKU");
            }
        }catch (NullPointerException e){
            printErrorMessage("SKU");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getEan13() != null){
                infoHolder.setEan(result.getGetProductsCompleteByScanCodeResult().getEan13().toString());
            }else{
                printErrorMessage("EAN");
            }
        }catch (NullPointerException e){
            printErrorMessage("EAN");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getUpcBarCode() != null){
                infoHolder.setUpc(result.getGetProductsCompleteByScanCodeResult().getUpcBarCode().toString());
            }else{
                printErrorMessage("UPC");
            }
        }catch (NullPointerException e){
            printErrorMessage("UPC");

        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getCustomBarCode() != null){
                infoHolder.setCustomBarcode(result.getGetProductsCompleteByScanCodeResult().getCustomBarCode());
            }else{
                printErrorMessage("CustomBarcode");
            }
        }catch (NullPointerException e){
            printErrorMessage("CustomBarcode");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getPackagingUnit() != null){
                infoHolder.setAmountPerPackage(result.getGetProductsCompleteByScanCodeResult().getPackagingUnit());
            }else{
                printErrorMessage("PackagingUnits");
            }
        }catch (NullPointerException e){
            printErrorMessage("PackagingUnits");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getWeightPerUnit() != null){
                infoHolder.setWeight(result.getGetProductsCompleteByScanCodeResult().getWeightPerUnit().toString());
            }else{
                printErrorMessage("Weight");
            }
        }catch (NullPointerException e){
            printErrorMessage("Weight");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getWeightUnit().getDescription() != null){
                infoHolder.setWeight(infoHolder.getWeight() + result.getGetProductsCompleteByScanCodeResult().getWeightUnit().getDescription());
            }else{
                printErrorMessage("WeightUnit");
            }
        }catch (NullPointerException e){
            printErrorMessage("WeightUnit");
        }

        //Check for product properties.
        try{
            if(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues() != null){
                for(int i = 0; i < result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().size(); i++){
                    tempProductInfoRecyclerModel = new ProductInfoRecyclerModel();
                    if(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().get(i).getName() != null){
                        tempProductInfoRecyclerModel.setProperty(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().get(i).getName());
                    }else{
                        tempProductInfoRecyclerModel.setProperty("-");
                    }
                    if(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().get(i).getPropertyValue() != null){
                        tempProductInfoRecyclerModel.setValue(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().get(i).getPropertyValue().toString());
                    }else{
                        tempProductInfoRecyclerModel.setValue("-");
                    }
                    if(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().get(i).getUnit() != null){
                        tempProductInfoRecyclerModel.setUnit(result.getGetProductsCompleteByScanCodeResult().getProductPropertyValues().get(i).getUnit().toString());
                    }else{
                        tempProductInfoRecyclerModel.setUnit("");
                    }
                    infoHolder.getProperties().add(tempProductInfoRecyclerModel);
                }
            }else{
                printErrorMessage("Properties");
            }
        }catch (NullPointerException e){
            printErrorMessage("Properties");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock() != null){
                if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation() != null){
                    for(int i = 0; i < result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().size(); i++){
                        tempLocationInfoRecyclerModel = new LocationInfoRecyclerModel();
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getCurrentStock() != null){
                            tempLocationInfoRecyclerModel.setProductStock(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getCurrentStock().intValue());
                        }
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseLocation().getScanLocationTag() != null){
                            tempLocationInfoRecyclerModel.setLocationTag(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseLocation().getScanLocationTag());
                        }
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseLocation().getLocationName() != null){
                            tempLocationInfoRecyclerModel.setLocation(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseLocation().getLocationName());
                        }
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseName() != null){
                            tempLocationInfoRecyclerModel.setWarehouseName(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseName());
                        }
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getId() != null){
                            tempLocationInfoRecyclerModel.setStockLocationId(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getId());
                        }
                        if(result.getGetProductsCompleteByScanCodeResult().getId() != null){
                            tempLocationInfoRecyclerModel.setProductId(result.getGetProductsCompleteByScanCodeResult().getId());
                        }
                        infoHolder.getLocations().add(tempLocationInfoRecyclerModel);
                    }
                }
            }else{
                printErrorMessage("Location");
            }
        }catch (NullPointerException e){
            printErrorMessage("Location");
        }

        try{
            if(result.getGetProductsCompleteByScanCodeResult().getUniqueBatchNumbers() != null){
                for(int i = 0; i < infoHolder.getLocations().size(); i++){
                    infoHolder.getLocations().get(i).setSerialnumbersRequired(result.getGetProductsCompleteByScanCodeResult().getUniqueBatchNumbers());
                    if(infoHolder.getLocations().get(0).getSerialnumbersRequired()){
                        //Todo: Get serialnumbers
                        try{
                            AtomicReference<Serialnumbers> serialnumbersResult = new AtomicReference<>();
                            int id = result.getGetProductsCompleteByScanCodeResult().getId();
                            int stockLocationId = infoHolder.getLocations().get(i).getStockLocationId();
                            this.disposables.add(getSerialnumberByStockLocationIdAndProductId.invoke(infoHolder.getLocations().get(i).getStockLocationId(), id, getUserData().getApiKey())
                                    .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(serialnumbersResult::set,
                                            this::onGetApiDataFailed,
                                            () -> onSerialnumbersFetched(serialnumbersResult.get(), stockLocationId, id)));
                        }catch (Exception e){
                            //Todo: Raise a visible error here so that orderpickers don't get stuck.
                            Log.e(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
                            printErrorMessage("Failed to get serialnumbers");
                        }
                    }
                }
            }else{
                printErrorMessage("Is Serialnumber Required");
            }
        }catch(NullPointerException | IndexOutOfBoundsException e){
            printErrorMessage("Is Serialnumber Required");
        }

        onApiRequestCompleted();
        changeLoadingState();
        if(infoHolder.getLocations().size() < 1){
            Toast.makeText(context, "Geen locaties gevonden.", Toast.LENGTH_SHORT).show();
        }
        view.onNewProductInfoReceived(infoHolder, scan);
    }

    private void onSerialnumbersFetched(Serialnumbers serialnumbers, int stocklocationId, int productId){
        view.onSerialnumbersFetched(serialnumbers, stocklocationId, productId);
    }

    private void onGetApiDataFailed(Throwable throwable){
        Log.e(TAG, Objects.requireNonNull(throwable.getLocalizedMessage()));
        onApiRequestCompleted();
        changeLoadingState();
        view.setErrorMessage(context.getString(R.string.product_error_message));
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

    private void printErrorMessage(String missingObject){
        Log.e(TAG, "Product " + missingObject + " not present.");
    }
}

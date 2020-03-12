package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.ProductInfoRecyclerModel;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Product.Info.GetProductInfoByScan;
import nl.brickx.domain.Users.UserDataManager;

import static android.content.ContentValues.TAG;

public class ProductInfoPresenter implements ProductInfoContract.Presenter {

    private MediatorLiveData<ProductInfoHolder> productInfo = new MediatorLiveData<>();

    private UserDataManager userDataManager;
    private GetProductInfoByScan getProductInfoByScan;
    private ProductInfoRecyclerModel tempProductInfoRecyclerModel = new ProductInfoRecyclerModel();
    private LocationInfoRecyclerModel tempLocationInfoRecyclerModel = new LocationInfoRecyclerModel();
    private ProductInfoContract.View view;
    private Context context;


    @Inject
    ProductInfoPresenter(UserDataManager userDataManager, GetProductInfoByScan getProductInfoByScan, ProductInfoContract.View view, @DataContext Context context){
        this.userDataManager = userDataManager;
        this.getProductInfoByScan = getProductInfoByScan;
        this.view = view;

        //Datawedge
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(context.getString(R.string.datawedge_intent_filter_action));
        context.registerReceiver(textInputBroadcastReceiver, filter);
    }

    @Override
    public void getProductInfoByScan(String scan) {
        final List<ProductInformation> result = new ArrayList<>();
        System.out.println(new Date());
        getProductInfoByScan.invoke(scan, getUserData().getApiKey())
                .doOnNext(c -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( s -> result.add(s),
                            Throwable::printStackTrace,
                            () -> onProductInfoFetched(result));
    }

    @Override
    public User getUserData() {
        return userDataManager.GetUserData();
    }

    @Override
    public LiveData<ProductInfoHolder> observeProductInfo() {
        return productInfo;
    }

    private BroadcastReceiver textInputBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle b = intent.getExtras();
            if (action.equals(context.getResources().getString(R.string.datawedge_intent_filter_action))) {
                //  Received a barcode scan
                try {
                    //Execute View code.
                    view.getProductInfoByScan(intent.getStringExtra(context.getResources().getString(R.string.datawedge_intent_key_data)));
                } catch (Exception e) {
                    Log.i(TAG, "Unable to read data from scanner.");
                }
            }
        }
    };

    private void onProductInfoFetched(List<ProductInformation> productInformations){
        System.out.println(new Date());
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
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseLocation().getLocationName() != null){
                            tempLocationInfoRecyclerModel.setLocation(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseLocation().getLocationName());
                        }
                        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseName() != null){
                            tempLocationInfoRecyclerModel.setWarehouseName(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getStockLocation().get(i).getWareHouseName());
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

        view.onNewProductInfoReceived(infoHolder);
    }

    private void printErrorMessage(String missingObject){
        Log.i(TAG, "Product " + missingObject + " not present.");
    }
}

package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
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
    private ProductInfoContract.View view;

    @Inject
    ProductInfoPresenter(UserDataManager userDataManager, GetProductInfoByScan getProductInfoByScan, ProductInfoContract.View view){
        this.userDataManager = userDataManager;
        this.getProductInfoByScan = getProductInfoByScan;
        this.view = view;
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
            if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getAvailableStock() != null){
                infoHolder.setStock(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getAvailableStock());
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

        view.onNewProductInfoReceived(infoHolder);
    }

    private void printErrorMessage(String missingObject){
        Log.i(TAG, "Product " + missingObject + " not present.");
    }

    @Override
    public User getUserData() {
        return userDataManager.GetUserData();
    }

    @Override
    public LiveData<ProductInfoHolder> observeProductInfo() {
        return productInfo;
    }
}

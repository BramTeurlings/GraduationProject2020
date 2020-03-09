package nl.brickx.domain.Product.Info;

import android.content.Context;
import android.util.Log;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.ProductInfoRecyclerModel;
import nl.brickx.domain.Product.Info.Data.ProductRepository;

public class GetProductInfoByScan {

    private Context context;
    private ProductRepository.ProductInfo productInfo;
    private final String TAG = "ProductInfo Parser: ";
    private ProductInfoRecyclerModel tempProductInfoRecyclerModel = new ProductInfoRecyclerModel();

    @Inject
    GetProductInfoByScan(ProductRepository.ProductInfo productInfo){
        this.productInfo = productInfo;
    }

    public Flowable<ProductInfoHolder> invoke(String code, String apiKey) {
        ProductInformation result = productInfo.getProductInfoByScan(code, apiKey).subscribeOn(Schedulers.io()).blockingFirst();
        ProductInfoHolder infoHolder = new ProductInfoHolder();

        //Todo: Return error message to user if this code throws an error (on getCurrentStock().get(0).
        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getName() != null){
            infoHolder.setProductName(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getName());
        }else{
            printErrorMessage("Name");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getAvailableStock() != null){
            infoHolder.setStock(result.getGetProductsCompleteByScanCodeResult().getCurrentStock().get(0).getAvailableStock());
        }else{
            printErrorMessage("Stock");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getCode() != null){
            infoHolder.setSku(result.getGetProductsCompleteByScanCodeResult().getCode());
        }else{
            printErrorMessage("SKU");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getEan13() != null){
            infoHolder.setEan(result.getGetProductsCompleteByScanCodeResult().getEan13().toString());
        }else{
            printErrorMessage("EAN");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getUpcBarCode() != null){
            infoHolder.setUpc(result.getGetProductsCompleteByScanCodeResult().getUpcBarCode().toString());
        }else{
            printErrorMessage("UPC");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getCustomBarCode() != null){
            infoHolder.setCustomBarcode(result.getGetProductsCompleteByScanCodeResult().getCustomBarCode());
        }else{
            printErrorMessage("CustomBarcode");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getPackagingUnit() != null){
            infoHolder.setAmountPerPackage(result.getGetProductsCompleteByScanCodeResult().getPackagingUnit());
        }else{
            printErrorMessage("PackagingUnits");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getWeightPerUnit() != null){
            infoHolder.setWeight(result.getGetProductsCompleteByScanCodeResult().getWeightPerUnit().toString());
        }else{
            printErrorMessage("Weight");
        }
        if(result.getGetProductsCompleteByScanCodeResult().getWeightUnit().getDescription() != null){
            infoHolder.setWeight(infoHolder.getWeight() + result.getGetProductsCompleteByScanCodeResult().getWeightUnit().getDescription());
        }else{
            printErrorMessage("WeightUnit");
        }
        //Check for product properties.
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
        return Flowable.just(infoHolder);
    }

    private boolean checkForNull(String content){
        if(content.equals("null")){
            return false;
        }
        return true;
    }

    private void printErrorMessage(String missingObject){
        Log.i(TAG, "Product " + missingObject + " not present.");
    }
}


package nl.brickx.domain.Models.Gson.ProductInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInformation {

    @SerializedName("GetProductsCompleteByScanCodeResult")
    @Expose
    private GetProductsCompleteByScanCodeResult getProductsCompleteByScanCodeResult;

    public GetProductsCompleteByScanCodeResult getGetProductsCompleteByScanCodeResult() {
        return getProductsCompleteByScanCodeResult;
    }

    public void setGetProductsCompleteByScanCodeResult(GetProductsCompleteByScanCodeResult getProductsCompleteByScanCodeResult) {
        this.getProductsCompleteByScanCodeResult = getProductsCompleteByScanCodeResult;
    }

}

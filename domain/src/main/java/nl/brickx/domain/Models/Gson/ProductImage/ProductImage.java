
package nl.brickx.domain.Models.Gson.ProductImage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImage {

    @SerializedName("GetImageByProductIdResult")
    @Expose
    private GetImageByProductIdResult getImageByProductIdResult;

    public GetImageByProductIdResult getGetImageByProductIdResult() {
        return getImageByProductIdResult;
    }

    public void setGetImageByProductIdResult(GetImageByProductIdResult getImageByProductIdResult) {
        this.getImageByProductIdResult = getImageByProductIdResult;
    }

}

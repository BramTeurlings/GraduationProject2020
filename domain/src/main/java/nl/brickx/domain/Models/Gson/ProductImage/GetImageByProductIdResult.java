
package nl.brickx.domain.Models.Gson.ProductImage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetImageByProductIdResult {

    @SerializedName("AttributeCombination")
    @Expose
    private Object attributeCombination;
    @SerializedName("Base64Array")
    @Expose
    private String base64Array;
    @SerializedName("ImageGroup")
    @Expose
    private String imageGroup;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SortOrder")
    @Expose
    private Integer sortOrder;

    public Object getAttributeCombination() {
        return attributeCombination;
    }

    public void setAttributeCombination(Object attributeCombination) {
        this.attributeCombination = attributeCombination;
    }

    public String getBase64Array() {
        return base64Array;
    }

    public void setBase64Array(String base64Array) {
        this.base64Array = base64Array;
    }

    public String getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(String imageGroup) {
        this.imageGroup = imageGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}

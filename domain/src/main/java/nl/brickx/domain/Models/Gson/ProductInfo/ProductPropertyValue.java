
package nl.brickx.domain.Models.Gson.ProductInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductPropertyValue {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PropertyValue")
    @Expose
    private Object propertyValue;
    @SerializedName("Unit")
    @Expose
    private Object unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Object getUnit() {
        return unit;
    }

    public void setUnit(Object unit) {
        this.unit = unit;
    }

}

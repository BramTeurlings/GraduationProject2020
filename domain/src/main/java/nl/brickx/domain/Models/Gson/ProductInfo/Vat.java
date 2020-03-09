
package nl.brickx.domain.Models.Gson.ProductInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vat {

    @SerializedName("Percentage")
    @Expose
    private Double percentage;
    @SerializedName("ValidFrom")
    @Expose
    private String validFrom;
    @SerializedName("ValidTill")
    @Expose
    private String validTill;

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

}

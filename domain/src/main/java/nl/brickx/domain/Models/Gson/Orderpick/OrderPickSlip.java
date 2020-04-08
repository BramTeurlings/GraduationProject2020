
package nl.brickx.domain.Models.Gson.Orderpick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPickSlip {

    @SerializedName("GetPickslipByNumberResult")
    @Expose
    private GetPickslipByNumberResult getPickslipByNumberResult;

    public GetPickslipByNumberResult getGetPickslipByNumberResult() {
        return getPickslipByNumberResult;
    }

    public void setGetPickslipByNumberResult(GetPickslipByNumberResult getPickslipByNumberResult) {
        this.getPickslipByNumberResult = getPickslipByNumberResult;
    }

}

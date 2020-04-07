
package nl.brickx.domain.Models.Gson.OrderPickLanding;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPickLanding {

    @SerializedName("GetPickslipListResult")
    @Expose
    private List<GetPickslipListResult> getPickslipListResult = null;

    public List<GetPickslipListResult> getGetPickslipListResult() {
        return getPickslipListResult;
    }

    public void setGetPickslipListResult(List<GetPickslipListResult> getPickslipListResult) {
        this.getPickslipListResult = getPickslipListResult;
    }

}

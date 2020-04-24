
package nl.brickx.domain.Models.Gson.Serialnumbers;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Serialnumbers {

    @SerializedName("GetAvailableBatchNumbersResult")
    @Expose
    private List<String> getAvailableBatchNumbersResult = null;

    public List<String> getGetAvailableBatchNumbersResult() {
        return getAvailableBatchNumbersResult;
    }

    public void setGetAvailableBatchNumbersResult(List<String> getAvailableBatchNumbersResult) {
        this.getAvailableBatchNumbersResult = getAvailableBatchNumbersResult;
    }

}

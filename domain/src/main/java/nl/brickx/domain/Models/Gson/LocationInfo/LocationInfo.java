
package nl.brickx.domain.Models.Gson.LocationInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationInfo {

    @SerializedName("GetWmsLocationViewForLocationResult")
    @Expose
    private List<GetWmsLocationViewForLocationResult> getWmsLocationViewForLocationResult = null;

    public List<GetWmsLocationViewForLocationResult> getGetWmsLocationViewForLocationResult() {
        return getWmsLocationViewForLocationResult;
    }

    public void setGetWmsLocationViewForLocationResult(List<GetWmsLocationViewForLocationResult> getWmsLocationViewForLocationResult) {
        this.getWmsLocationViewForLocationResult = getWmsLocationViewForLocationResult;
    }

}

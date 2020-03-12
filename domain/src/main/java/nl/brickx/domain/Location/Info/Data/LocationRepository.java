package nl.brickx.domain.Location.Info.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.LocationInfo.LocationInfo;

public interface LocationRepository {

    interface LocationInfo{

        Observable<nl.brickx.domain.Models.Gson.LocationInfo.LocationInfo> getProductInfoByScan(String scan, String apiKey);
    }
}

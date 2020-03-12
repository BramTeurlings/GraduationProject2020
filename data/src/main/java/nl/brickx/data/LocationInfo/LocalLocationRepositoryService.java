package nl.brickx.data.LocationInfo;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.LocationInfo.LocationInfo;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalLocationRepositoryService {

    @GET("WmsLocationView/searchbyscantag/")
    Observable<LocationInfo> getLocationInfo(@Query("tag") String scan,
                                         @Query("apiKey") String apiKey);
}

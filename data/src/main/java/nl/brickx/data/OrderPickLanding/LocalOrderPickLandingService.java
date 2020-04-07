package nl.brickx.data.OrderPickLanding;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalOrderPickLandingService {

    @GET("picksliplist")
    Observable<OrderPickLanding> getOrders(@Query("apikey") String apiKey,
                                                @Query("key") String key);
}

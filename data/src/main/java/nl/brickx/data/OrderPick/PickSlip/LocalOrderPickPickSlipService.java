package nl.brickx.data.OrderPick.PickSlip;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalOrderPickPickSlipService {

    @GET("pickslip/searchbynumber/")
    Observable<OrderPickSlip> getPickSlip(@Query("number") String orderNumber,
                                          @Query("apikey") String apikey,
                                          @Query("key") String key);
}

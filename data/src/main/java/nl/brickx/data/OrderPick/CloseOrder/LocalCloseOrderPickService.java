package nl.brickx.data.OrderPick.CloseOrder;

import io.reactivex.Observable;
import nl.brickx.domain.Models.SavePickSlipDto;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalCloseOrderPickService {

    @POST("Pickslip/savepickslip")
    Observable<Boolean> closeOrderPick(@Body SavePickSlipDto closeOrderPickModel,
                                       @Query("apikey") String apikey);
}

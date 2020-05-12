package nl.brickx.data.StockTransfer;

import io.reactivex.Observable;
import nl.brickx.domain.Models.BoolResultDto;
import nl.brickx.domain.Models.StockTransferDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalStockTransferService {

    @POST("stock/makestockmutation")
    Observable<BoolResultDto> doStockTransfer(@Body StockTransferDto closeOrderPickModel,
                                        @Query("apikey") String apikey);
}

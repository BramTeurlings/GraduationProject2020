package nl.brickx.data.StockTransfer;

import io.reactivex.Observable;
import nl.brickx.domain.Models.BoolResultDto;
import nl.brickx.domain.Models.StockTransferDto;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalStockTransferService {

    @POST("stock/makestocktransfer")
    Observable<BoolResultDto> doStockTransfer(@Body StockTransferDto stockTransferDto,
                                              @Query("apikey") String apikey);
}

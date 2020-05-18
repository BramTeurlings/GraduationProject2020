package nl.brickx.data.StockMutation;

import io.reactivex.Observable;
import nl.brickx.domain.Models.BoolResultDto;
import nl.brickx.domain.Models.StockMutationDto;
import nl.brickx.domain.Models.StockTransferDto;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LocalStockMutationService {

    @POST("stock/makestockmutation")
    Observable<BoolResultDto> doStockMutation(@Body StockMutationDto stockMutationDto,
                                              @Query("apikey") String apikey);
}

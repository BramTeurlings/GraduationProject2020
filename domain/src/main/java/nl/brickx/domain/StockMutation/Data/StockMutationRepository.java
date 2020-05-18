package nl.brickx.domain.StockMutation.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.StockMutationDto;
import nl.brickx.domain.Models.StockTransferDto;

public interface StockMutationRepository {

    interface StockMutation{

        Observable<Boolean> doStockMutation(StockMutationDto stockMutationDto, String key);
    }
}

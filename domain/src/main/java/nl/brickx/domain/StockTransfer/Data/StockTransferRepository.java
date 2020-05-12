package nl.brickx.domain.StockTransfer.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.StockTransferDto;

public interface StockTransferRepository {

    interface StockTransfer{

        Observable<Boolean> doStockTransfer(StockTransferDto stockTransferDto, String key);
    }
}

package nl.brickx.domain.StockTransfer;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.domain.Models.StockTransferDto;
import nl.brickx.domain.StockTransfer.Data.StockTransferRepository;

public class PostStockTransfer {

    private StockTransferRepository.StockTransfer stockTransfer;

    @Inject
    PostStockTransfer(StockTransferRepository.StockTransfer stockTransfer){
        this.stockTransfer = stockTransfer;
    }

    public Observable<Boolean> invoke(StockTransferDto stockTransferDto, String apikey) {
        return stockTransfer.doStockTransfer(stockTransferDto, apikey);
    }
}

package nl.brickx.domain.StockMutation;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.domain.Models.StockMutationDto;
import nl.brickx.domain.Models.StockTransferDto;
import nl.brickx.domain.StockMutation.Data.StockMutationRepository;

public class PostStockMutation {

    private StockMutationRepository.StockMutation stockMutation;

    @Inject
    PostStockMutation(StockMutationRepository.StockMutation stockMutation){
        this.stockMutation = stockMutation;
    }

    public Observable<Boolean> invoke(StockMutationDto stockMutationDto, String apikey) {
        return stockMutation.doStockMutation(stockMutationDto, apikey);
    }
}

package nl.brickx.data.StockMutation;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.BoolResultDto;
import nl.brickx.domain.Models.StockMutationDto;
import nl.brickx.domain.Models.StockTransferDto;
import nl.brickx.domain.StockMutation.Data.StockMutationRepository;
import retrofit2.Retrofit;

public class LocalStockMutationRepository implements StockMutationRepository.StockMutation {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalStockMutationService localStockMutationService;

    Context context;

    @Inject
    LocalStockMutationRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<Boolean> doStockMutation(StockMutationDto stockMutationDto, String key) {
        Observable<BoolResultDto> holder = localStockMutationService.doStockMutation(stockMutationDto, key);

        //Todo: Fix to return the BoolResultDto
        return Observable.just(holder.blockingFirst().getResult());
    }
}

package nl.brickx.data.StockTransfer;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.BoolResultDto;
import nl.brickx.domain.Models.StockTransferDto;
import nl.brickx.domain.StockTransfer.Data.StockTransferRepository;
import retrofit2.Retrofit;

public class LocalStockTransferRepository implements StockTransferRepository.StockTransfer {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalStockTransferService localStockTransferService;

    Context context;

    @Inject
    LocalStockTransferRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<Boolean> doStockTransfer(StockTransferDto stockTransferDto, String key) {
        Observable<BoolResultDto> holder = localStockTransferService.doStockTransfer(stockTransferDto, key).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        //Todo: Fix to return the BoolResultDto
        return Observable.just(holder.blockingFirst().getResult());
    }
}

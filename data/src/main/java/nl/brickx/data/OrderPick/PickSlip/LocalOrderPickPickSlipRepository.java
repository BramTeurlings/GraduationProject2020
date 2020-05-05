package nl.brickx.data.OrderPick.PickSlip;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;
import retrofit2.Retrofit;

public class LocalOrderPickPickSlipRepository implements OrderPickRepository.PickSlips {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalOrderPickPickSlipService localOrderPickPickSlipService;

    Context context;

    @Inject
    LocalOrderPickPickSlipRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<OrderPickSlip> getPickSlips(String orderNumber, String key) {
        Observable<OrderPickSlip> holder = localOrderPickPickSlipService.getPickSlip(orderNumber, key, key).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        return holder;
    }
}

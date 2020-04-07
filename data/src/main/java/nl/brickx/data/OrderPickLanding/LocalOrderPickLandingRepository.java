package nl.brickx.data.OrderPickLanding;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;
import nl.brickx.domain.OrderPick.Landing.Data.OrderPickLandingRepository;
import retrofit2.Retrofit;

public class LocalOrderPickLandingRepository implements OrderPickLandingRepository.OpenOrders {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalOrderPickLandingService localOrderPickLandingRepository;

    Context context;

    @Inject
    LocalOrderPickLandingRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<OrderPickLanding> getOpenOrders(String apiKey, String key) {
        Observable<OrderPickLanding> holder = localOrderPickLandingRepository.getOrders(apiKey, apiKey).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        return holder;
    }
}

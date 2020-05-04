package nl.brickx.data.OrderPick.CloseOrder;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.CloseOrderPickModel;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;
import retrofit2.Retrofit;

public class LocalCloseOrderPickRepository implements OrderPickRepository.CloseOrderPick {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalCloseOrderPickService localCloseOrderPickService;

    Context context;

    @Inject
    LocalCloseOrderPickRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<Boolean> closeOrderPick(CloseOrderPickModel model) {
        Observable<Boolean> holder = localCloseOrderPickService.closeOrderPick(model).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        return holder;
    }
}

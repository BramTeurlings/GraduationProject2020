package nl.brickx.data.OrderPick.ProductImage;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.data.OrderPickLanding.LocalOrderPickLandingService;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.OrderPick.Landing.Data.OrderPickLandingRepository;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;
import retrofit2.Retrofit;

public class LocalOrderPickProductImageRepository implements OrderPickRepository.ProductImage {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalOrderPickProductImageService localOrderPickProductImageService;

    Context context;

    @Inject
    LocalOrderPickProductImageRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<ProductImage> getProductImage(String productId, String key) {
        Observable<ProductImage> holder = localOrderPickProductImageService.getProductImage(productId, key).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        return holder;
    }
}

package nl.brickx.data.OrderPick.ProductImage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;

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

import static android.content.ContentValues.TAG;

public class LocalOrderPickProductImageRepository implements OrderPickRepository.ProductImage {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalOrderPickProductImageService localOrderPickProductImageService;

    Context context;

    private ProductImage tempImage;

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

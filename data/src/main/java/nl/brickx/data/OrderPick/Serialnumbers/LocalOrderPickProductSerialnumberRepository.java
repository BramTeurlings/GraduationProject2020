package nl.brickx.data.OrderPick.Serialnumbers;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;
import retrofit2.Retrofit;

public class LocalOrderPickProductSerialnumberRepository implements OrderPickRepository.SerialNumbers {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalOrderPickProductSerialnumberService localOrderPickProductSerialnumberService;

    Context context;

    private ProductImage tempImage;

    @Inject
    LocalOrderPickProductSerialnumberRepository(@DataContext Context context){
        this.context = context;
    }


    @Override
    public Observable<Serialnumbers> getSerialnumbers(int stockLocationId, int productId, String apiKey) {
        return localOrderPickProductSerialnumberService.getProductImage(stockLocationId, productId, apiKey).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());
    }
}

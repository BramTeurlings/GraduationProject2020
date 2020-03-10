package nl.brickx.data.ProductInfo;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Product.Info.Data.ProductRepository;
import retrofit2.Retrofit;

public class LocalProductInfoRepository implements ProductRepository.ProductInfo {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalProductRepositoryService localProductRepositoryService;

    Context context;

    @Inject
    LocalProductInfoRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<ProductInformation> getProductInfoByScan(String scan, String apiKey) {
        Observable<ProductInformation> holder = localProductRepositoryService.getProductInfo(scan, apiKey).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        return holder;
    }
}

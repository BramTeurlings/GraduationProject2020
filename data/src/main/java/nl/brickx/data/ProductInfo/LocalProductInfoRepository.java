package nl.brickx.data.ProductInfo;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Flowable;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.ProductInfoHolder;
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
    public Flowable<ProductInformation> getProductInfoByScan(String scan, String apiKey) {
        Flowable<ProductInformation> holder = localProductRepositoryService.getProductInfo(scan, apiKey);

        return holder;
    }
}

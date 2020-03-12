package nl.brickx.data.LocationInfo;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.data.ProductInfo.LocalProductRepositoryService;
import nl.brickx.domain.Location.Info.Data.LocationRepository;
import nl.brickx.domain.Models.Gson.LocationInfo.LocationInfo;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import retrofit2.Retrofit;

public class LocalLocationInfoRepository implements LocationRepository.LocationInfo {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalLocationRepositoryService localLocationRepositoryService;

    Context context;

    @Inject
    LocalLocationInfoRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Observable<LocationInfo> getProductInfoByScan(String scan, String apiKey) {
        Observable<LocationInfo> holder = localLocationRepositoryService.getLocationInfo(scan, apiKey).subscribeOn(Schedulers.computation()).observeOn(Schedulers.io());

        return holder;
    }
}

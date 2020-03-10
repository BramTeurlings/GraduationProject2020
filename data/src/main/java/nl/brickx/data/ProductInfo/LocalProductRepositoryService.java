package nl.brickx.data.ProductInfo;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.ProductInfoHolder;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalProductRepositoryService {

    @GET("product/searchbyscan/")
    Observable<ProductInformation> getProductInfo(@Query("scan") String scan,
                                                  @Query("apikey") String apikey);
}

package nl.brickx.data.ProductInfo;

import io.reactivex.Flowable;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.ProductInfoHolder;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalProductRepositoryService {

    @GET("product/searchbyscan/")
    Flowable<ProductInformation> getProductInfo(@Query("scan") String scan,
                                                @Query("apikey") String apikey);
}

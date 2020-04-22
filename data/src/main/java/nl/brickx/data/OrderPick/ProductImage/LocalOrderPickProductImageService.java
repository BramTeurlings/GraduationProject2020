package nl.brickx.data.OrderPick.ProductImage;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalOrderPickProductImageService {

    @GET("productimage/getbyproductid/")
    Observable<ProductImage> getProductImage(@Query("id") String productId,
                                             @Query("apikey") String apikey);
}

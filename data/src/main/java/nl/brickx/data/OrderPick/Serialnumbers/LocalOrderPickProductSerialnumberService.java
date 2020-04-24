package nl.brickx.data.OrderPick.Serialnumbers;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalOrderPickProductSerialnumberService {

    @GET("pickslip/getavailablebatchnumbers/{stockLocation}/{productId}/0")
    Observable<Serialnumbers> getProductImage(@Path("stockLocation") int stockLocation,
                                              @Path("productId") int productId,
                                              @Query("apikey") String apikey);
}

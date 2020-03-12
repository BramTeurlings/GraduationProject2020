package nl.brickx.domain.Product.Info.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;

public interface ProductRepository {

    interface ProductInfo{

        Observable<ProductInformation> getProductInfoByScan(String scan, String apiKey);
    }
}

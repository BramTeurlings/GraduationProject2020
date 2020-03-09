package nl.brickx.domain.Product.Info.Data;

import io.reactivex.Flowable;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.ProductInfoHolder;

public interface ProductRepository {

    interface ProductInfo{
        Flowable<ProductInformation> getProductInfoByScan(String scan, String apiKey);
    }
}

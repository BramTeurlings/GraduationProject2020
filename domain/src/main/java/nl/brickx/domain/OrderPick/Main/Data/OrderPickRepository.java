package nl.brickx.domain.OrderPick.Main.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;


public interface OrderPickRepository {

    interface PickSlips{

        Observable<OrderPickSlip> getPickSlips(String orderNumber, String key);
    }

    interface ProductImage{

        Observable<nl.brickx.domain.Models.Gson.ProductImage.ProductImage> getProductImage(String productId, String key);
    }

}

package nl.brickx.domain.OrderPick.Main.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;


public interface OrderPickRepository {

    interface PickSlips{

        Observable<OrderPickSlip> getPickSlips(String orderNumber, String key);
    }

    interface ProductImage{

        Observable<nl.brickx.domain.Models.Gson.ProductImage.ProductImage> getProductImage(String productId, String key);
    }

    interface SerialNumbers{

        Observable<Serialnumbers> getSerialnumbers(int stockLocationId, int productId, String apiKey);
    }

}

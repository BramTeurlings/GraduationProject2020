package nl.brickx.domain.OrderPick.Main.Data;

import java.util.List;

import io.reactivex.Observable;
import nl.brickx.domain.Models.SavePickSlipDto;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.Models.GsonOrderPickPickList;
import nl.brickx.domain.Models.OrderPickPickListModel;


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

    interface CloseOrderPick {
        //Todo: Add API call here
        Observable<Boolean> closeOrderPick(SavePickSlipDto closeOrderPickModel, String apikey);
    }

    interface SaveOrderPickProgress{
        Boolean saveUserDataSharedPref(List<OrderPickPickListModel> data);
    }

    interface GetOrderPickProgress{
        Observable<List<GsonOrderPickPickList>> getOrderPickData();
    }
}

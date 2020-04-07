package nl.brickx.domain.OrderPick.Landing.Data;

import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;

public interface OrderPickLandingRepository {

    interface OpenOrders{

        Observable<OrderPickLanding> getOpenOrders(String apiKey, String key);
    }
}

package nl.brickx.domain.OrderPick.Landing;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.OrderPickLanding.OrderPickLanding;
import nl.brickx.domain.OrderPick.Landing.Data.OrderPickLandingRepository;

public class GetOrdersToPickByKey {

    private Context context;
    private OrderPickLandingRepository.OpenOrders orderRepo;
    private final String TAG = "ProductInfo Parser: ";
    private OrderPickLanding tempProductInfoRecyclerModel = new OrderPickLanding();

    @Inject
    GetOrdersToPickByKey(OrderPickLandingRepository.OpenOrders orderRepo){
        this.orderRepo = orderRepo;
    }

    public Observable<OrderPickLanding> invoke(String apiKey) {
        return orderRepo.getOpenOrders(apiKey, apiKey).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

}

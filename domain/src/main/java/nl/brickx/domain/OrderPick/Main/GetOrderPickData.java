package nl.brickx.domain.OrderPick.Main;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import nl.brickx.domain.Models.GsonOrderPickPickList;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class GetOrderPickData {

    private OrderPickRepository.GetOrderPickProgress getOrderPickProgress;
    private Observable<List<GsonOrderPickPickList>> tempOrders;

    @Inject
    GetOrderPickData(OrderPickRepository.GetOrderPickProgress getOrderPickProgress){
        this.getOrderPickProgress = getOrderPickProgress;
    }

    public Observable<List<GsonOrderPickPickList>> invoke() {
        tempOrders = getOrderPickProgress.getOrderPickData();
        if(tempOrders == null){
            tempOrders = Observable.just(new ArrayList<>());
        }

        return tempOrders;
    }
}

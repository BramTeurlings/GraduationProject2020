package nl.brickx.domain.OrderPick.Main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.domain.Models.CloseOrderPickModel;
import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class CloseOrderPick {

    private OrderPickRepository.CloseOrderPick closeOrderPick;

    @Inject
    CloseOrderPick(OrderPickRepository.CloseOrderPick closeOrderPick){
        this.closeOrderPick = closeOrderPick;
    }

    public Observable<Boolean> invoke(CloseOrderPickModel orders) {
        return closeOrderPick.closeOrderPick(orders);
    }
}

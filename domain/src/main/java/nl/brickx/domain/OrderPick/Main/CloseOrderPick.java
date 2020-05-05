package nl.brickx.domain.OrderPick.Main;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.domain.Models.SavePickSlipDto;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class CloseOrderPick {

    private OrderPickRepository.CloseOrderPick closeOrderPick;

    @Inject
    CloseOrderPick(OrderPickRepository.CloseOrderPick closeOrderPick){
        this.closeOrderPick = closeOrderPick;
    }

    public Observable<Boolean> invoke(SavePickSlipDto orders, String apikey) {
        return closeOrderPick.closeOrderPick(orders, apikey);
    }
}

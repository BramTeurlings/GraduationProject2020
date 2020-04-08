package nl.brickx.domain.OrderPick.Main;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.Orderpick.OrderPickSlip;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class GetPickSlipByOrderNumber {

    private OrderPickRepository.PickSlips pickSlipsRepo;
    private OrderPickSlip orderPickSlip = new OrderPickSlip();

    @Inject
    GetPickSlipByOrderNumber(OrderPickRepository.PickSlips pickSlipsRepo){
        this.pickSlipsRepo = pickSlipsRepo;
    }

    public Observable<OrderPickSlip> invoke(String orderNumber, String apiKey) {
        return pickSlipsRepo.getPickSlips(orderNumber, apiKey).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}

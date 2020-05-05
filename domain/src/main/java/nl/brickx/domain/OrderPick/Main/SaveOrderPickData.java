package nl.brickx.domain.OrderPick.Main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class SaveOrderPickData {

    private OrderPickRepository.SaveOrderPickProgress saveOrderPickProgress;

    @Inject
    SaveOrderPickData(OrderPickRepository.SaveOrderPickProgress saveOrderPickDataRepo){
        this.saveOrderPickProgress = saveOrderPickDataRepo;
    }

    public Observable<Boolean> invoke(List<OrderPickPickListModel> orders) {
        return Observable.just(saveOrderPickProgress.saveUserDataSharedPref(orders));
    }
}

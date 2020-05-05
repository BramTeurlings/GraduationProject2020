package nl.brickx.domain.OrderPick.Main;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class GetSerialnumberByStockLocationIdAndProductId {

    private OrderPickRepository.SerialNumbers serialNumberRepo;
    private Serialnumbers serialnumbers = new Serialnumbers();

    @Inject
    GetSerialnumberByStockLocationIdAndProductId(OrderPickRepository.SerialNumbers serialNumberRepo){
        this.serialNumberRepo = serialNumberRepo;
    }

    public Observable<Serialnumbers> invoke(int stockLocationId, int productId, String apiKey) {
        return serialNumberRepo.getSerialnumbers(stockLocationId, productId, apiKey).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}

package nl.brickx.domain.OrderPick.Main;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class GetProductImageByNumber {

    private OrderPickRepository.ProductImage productImageRepo;

    @Inject
    GetProductImageByNumber(OrderPickRepository.ProductImage productImageRepo){
        this.productImageRepo = productImageRepo;
    }

    public Observable<ProductImage> invoke(String productId, String apiKey) {
        return productImageRepo.getProductImage(productId, apiKey).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}

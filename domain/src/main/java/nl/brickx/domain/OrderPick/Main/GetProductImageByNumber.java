package nl.brickx.domain.OrderPick.Main;

import android.graphics.drawable.BitmapDrawable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;

public class GetProductImageByNumber {

    private OrderPickRepository.ProductImage productImageRepo;
    private int delay = 0;

    @Inject
    GetProductImageByNumber(OrderPickRepository.ProductImage productImageRepo){
        this.productImageRepo = productImageRepo;
    }

    public Observable<ProductImage> invoke(String productId, String apiKey) {
        //Avoid API breaking.

        return productImageRepo.getProductImage(productId, apiKey).subscribeOn(Schedulers.io());
    }
}

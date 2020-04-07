package nl.brickx.domain.Product.Info;

import android.content.Context;
import android.util.Log;
import javax.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.Gson.ProductInfo.ProductInformation;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.ProductInfoRecyclerModel;
import nl.brickx.domain.Product.Info.Data.ProductRepository;

public class GetProductInfoByScan {

    private Context context;
    private ProductRepository.ProductInfo productInfo;
    private final String TAG = "ProductInfo Parser: ";
    private ProductInfoRecyclerModel tempProductInfoRecyclerModel = new ProductInfoRecyclerModel();

    @Inject
    GetProductInfoByScan(ProductRepository.ProductInfo productInfo){
        this.productInfo = productInfo;
    }

    public Observable<ProductInformation> invoke(String code, String apiKey) {
        return productInfo.getProductInfoByScan(code, apiKey).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}

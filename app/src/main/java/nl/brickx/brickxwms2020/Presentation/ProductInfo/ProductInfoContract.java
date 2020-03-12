package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import androidx.lifecycle.LiveData;

import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.User;

public interface ProductInfoContract {

    interface Presenter{

        void getProductInfoByScan(String scan);
        User getUserData();
        LiveData<ProductInfoHolder> observeProductInfo();
    }

    interface View{

        void onNewProductInfoReceived(ProductInfoHolder holder);
        void getProductInfoByScan(String scannedCode);
    }
}

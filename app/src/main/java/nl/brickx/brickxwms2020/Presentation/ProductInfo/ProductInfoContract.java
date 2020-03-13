package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.User;

public interface ProductInfoContract {

    interface Presenter{

        void getProductInfoByScan(String scan);
        User getUserData();
        void dispose();
    }

    interface View{

        void onNewProductInfoReceived(ProductInfoHolder holder);
        void getProductInfoByScan(String scannedCode);
        void clearBarcodeInput();
    }
}

package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.StockTransferDto;
import nl.brickx.domain.Models.User;

public interface StockTransferMainContract {

    interface Presenter{

        void getProductInfoByScan(String scan);
        User getUserData();
        void dispose();
    }

    interface View {

        void onLocationInfoReceived(LocationInfoRecyclerModel holder);
        void getProductInfoByScan(String scannedCode);
        void clearBarcodeInput();
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }

    interface Navigator {


    }
}

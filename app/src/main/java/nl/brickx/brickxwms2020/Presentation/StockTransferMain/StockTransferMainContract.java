package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import java.util.List;

import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.StockTransferDto;
import nl.brickx.domain.Models.User;

public interface StockTransferMainContract {

    interface Presenter{

        void getProductInfoByScan(String scan);
        User getUserData();
        void dispose();
        void removeSerialnumber(OrderPickSerialStatusModel orderPickSerialStatusModel);
        void completeStockTransfer(StockTransferDto stockTransferDto);
    }

    interface View {

        void onBarcodeScanned(String scan);
        LocationInfoRecyclerModel getLocationRecyclerModel();
        void updateSerialNumbers(LocationInfoRecyclerModel model);
        void onLocationInfoReceived(List<LocationInfoRecyclerModel> holder, String scan);
        void getProductInfoByScan(String scannedCode);
        void clearBarcodeInput();
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }

    interface Navigator {


    }
}

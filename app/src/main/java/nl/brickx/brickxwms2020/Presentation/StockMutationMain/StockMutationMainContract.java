package nl.brickx.brickxwms2020.Presentation.StockMutationMain;

import java.util.List;

import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;
import nl.brickx.domain.Models.StockMutationDto;
import nl.brickx.domain.Models.User;

public interface StockMutationMainContract {

    interface Presenter{

        void getProductInfoByScan(String scan);
        User getUserData();
        void dispose();
        void removeSerialnumber(OrderPickSerialStatusModel orderPickSerialStatusModel);
        void completeStockMutation(StockMutationDto stockTransferDto);
    }

    interface View {

        void clearFocus();
        void onBarcodeScanned(String scan);
        LocationInfoRecyclerModel getLocationRecyclerModel();
        void updateSerialNumbers(LocationInfoRecyclerModel model);
        void onLocationInfoReceived(List<LocationInfoRecyclerModel> holder, String scan);
        void getProductInfoByScan(String scannedCode);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }

    interface Navigator {


    }
}

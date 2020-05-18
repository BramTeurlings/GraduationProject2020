package nl.brickx.brickxwms2020.Presentation.StockMutation;

import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.User;

public interface StockMutationContract {

    interface Presenter{

        void getProductInfoByScan(String scan);
        User getUserData();
        void dispose();
    }

    interface View {

        void onNewProductInfoReceived(ProductInfoHolder holder, String scan);
        void getProductInfoByScan(String scannedCode);
        void clearBarcodeInput();
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
        void onSerialnumbersFetched(Serialnumbers serialnumbers, int stockLocationId, int productId);
    }

    interface Navigator {

        void navigateToMutationScreen(LocationInfoRecyclerModel locationInfoRecyclerModel);
    }
}

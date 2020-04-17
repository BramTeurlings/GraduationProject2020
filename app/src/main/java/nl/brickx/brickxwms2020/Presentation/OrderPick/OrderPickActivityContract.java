package nl.brickx.brickxwms2020.Presentation.OrderPick;

import java.util.List;

import nl.brickx.domain.Models.OrderPickPickListModel;

public interface OrderPickActivityContract {

    interface Presenter {

        void getDataForFragments(String orderNumber);
        void dispose();
    }

    interface Navigator {

        void navigateToOrder(int index);
        //Todo: Navigate to screens.
    }
    interface View {

        void onBarcodeScanned(String scan);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
        void onPickListInfoReceived(List<OrderPickPickListModel> data);
    }
}

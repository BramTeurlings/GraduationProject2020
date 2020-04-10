package nl.brickx.brickxwms2020.Presentation.OrderPick;

import java.util.List;

import nl.brickx.domain.Models.OrderPickPickListModel;

public interface OrderPickActivityContract {

    interface Presenter {

        void getDataForFragments(String orderNumber);
        void dispose();
    }

    interface Navigator {

        //Todo: Navigate to screens.
    }
    interface View {

        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
        void onPickListInfoReceived(List<OrderPickPickListModel> data);
    }
}

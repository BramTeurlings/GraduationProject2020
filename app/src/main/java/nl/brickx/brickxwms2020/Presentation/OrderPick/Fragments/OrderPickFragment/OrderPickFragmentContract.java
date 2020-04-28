package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import nl.brickx.domain.Models.OrderPickSerialStatusModel;

public interface OrderPickFragmentContract {

    interface Presenter {

        void removeSerialnumber(OrderPickSerialStatusModel serialStatusModel);
        void startPresenting();
    }

    interface View {

        void handleScan(String scan);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}

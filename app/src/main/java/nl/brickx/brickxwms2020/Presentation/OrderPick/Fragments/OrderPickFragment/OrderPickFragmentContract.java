package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import java.util.List;

import nl.brickx.domain.Models.OrderPickPickListModel;
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
        void updateSerialnumbers(List<OrderPickPickListModel> data);
        List<OrderPickPickListModel> getData();
        int getCurrentViewPagerIndex();
    }
}

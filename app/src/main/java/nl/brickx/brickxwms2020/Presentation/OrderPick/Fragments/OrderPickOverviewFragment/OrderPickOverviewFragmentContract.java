package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.content.Context;

import java.util.List;

import nl.brickx.domain.Models.OrderPickPickListModel;

public interface OrderPickOverviewFragmentContract {

    interface Presenter {

        void loadPickSlipData();
        void closeOrderPick(List<OrderPickPickListModel> data);
        void dispose();
    }

    interface View {

        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}

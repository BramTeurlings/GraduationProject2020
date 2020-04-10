package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.content.Context;

public interface OrderPickOverviewFragmentContract {

    interface Presenter {

        void loadPickSlipData();
        void dispose();
    }

    interface View {

        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}

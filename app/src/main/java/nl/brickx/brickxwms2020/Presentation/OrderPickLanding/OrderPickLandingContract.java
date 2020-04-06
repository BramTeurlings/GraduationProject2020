package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.Context;

public interface OrderPickLandingContract {

    interface Presenter {

        void getOrdersToPick();
    }

    interface Navigator {
        void navigateToOrder(Context context, String orderId);
    }

    interface View {


    }
}

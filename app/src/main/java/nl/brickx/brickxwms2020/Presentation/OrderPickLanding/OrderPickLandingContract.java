package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.Context;

import java.util.List;

import nl.brickx.domain.Models.OrderPickLandingRecyclerModel;

public interface OrderPickLandingContract {

    interface Presenter {

        void getOrdersToPick();
        void dispose();
        void getPickslipByNumber(String number);
    }

    interface Navigator {
        void navigateToOrder(Context context, int orderId);
    }

    interface View {

        void clearBarcodeInput();
        void getPickslipByScan(String scannedCode);
        void onOrderInfoReceived(List<OrderPickLandingRecyclerModel> holder);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}

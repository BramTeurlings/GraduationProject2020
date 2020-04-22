package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.graphics.drawable.BitmapDrawable;

import java.util.List;

import nl.brickx.domain.Models.Gson.ProductImage.ProductImage;
import nl.brickx.domain.Models.OrderPickPickListModel;

public interface OrderPickActivityContract {

    interface Presenter {

        void getDataForFragments(String orderNumber);
        void getImageDataForFragments(List<OrderPickPickListModel> data);
        void dispose();
    }

    interface Navigator {

        void navigateToOrder(int index);
        //Todo: Navigate to screens.
    }
    interface View {

        void runImageUpdateOnUiThread(BitmapDrawable drawable, int productId);
        void onBarcodeScanned(String scan);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
        void onPickListInfoReceived(List<OrderPickPickListModel> data);
        void onPickListImageInfoReceived(BitmapDrawable image, int productId);
    }
}

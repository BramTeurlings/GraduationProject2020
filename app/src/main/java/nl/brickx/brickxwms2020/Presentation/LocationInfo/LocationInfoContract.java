package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.text.SpannableStringBuilder;

import java.util.List;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public interface LocationInfoContract {

    interface Presenter {

        void getLocationInfoByScan(String locationCode);
        void dispose();
        void buildLocationTag(String locationname);
    }

    interface View {

        void getLocationInfoByScan(String scan);
        void onLocationInfoReceived(List<LocationInfoRecyclerModel> locationInfoRecyclerModel);
        void clearBarcodeInput();
        void setLocationTag(SpannableStringBuilder stringBuilder);
        void changeLoadingState(Boolean isLoading);
        void setErrorMessage(String message);
    }
}

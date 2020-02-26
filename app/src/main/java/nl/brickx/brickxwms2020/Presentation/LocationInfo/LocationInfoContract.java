package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import java.util.List;
import nl.brickx.brickxwms2020.Presentation.Models.WarehouseLocation;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public interface LocationInfoContract {

    interface Presenter {

        List<LocationInfoRecyclerModel> getProductsByLocation(String locationCode);
    }

    interface Navigator {


    }
}

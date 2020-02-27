package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.Models.WarehouseLocation;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public class LocationInfoPresenter implements LocationInfoContract.Presenter {

    @Inject
    LocationInfoPresenter(){

    }

    @Override
    public List<LocationInfoRecyclerModel> getProductsByLocation(String locationCode) {
        //Todo: Add connection to domain layer and data layer for API call.
        List<LocationInfoRecyclerModel> locationData = new ArrayList<>();
        locationData.add(new LocationInfoRecyclerModel( "A21UAS23325B", "Thermish Blok", 256));
        locationData.add(new LocationInfoRecyclerModel( "A21HDRT36845", "Verwarming", 114));
        locationData.add(new LocationInfoRecyclerModel( "A212U3V1245B", "Zonnepaneel", 56));
        locationData.add(new LocationInfoRecyclerModel( "A214521EDS43", "Schroeven M3", 1274839761));

        return locationData;
    }
}

package nl.brickx.domain.Location.Info;

import javax.inject.Inject;

import io.reactivex.Observable;
import nl.brickx.domain.Location.Info.Data.LocationRepository;
import nl.brickx.domain.Models.Gson.LocationInfo.LocationInfo;

public class GetLocationInfoByScan {

    private LocationRepository.LocationInfo locationRepository;

    @Inject
    public GetLocationInfoByScan(LocationRepository.LocationInfo locationRepository){
        this.locationRepository = locationRepository;
    }

    public Observable<LocationInfo> invoke(String scan, String apiKey){
        return locationRepository.getLocationInfoByScan(scan, apiKey);
    }

}

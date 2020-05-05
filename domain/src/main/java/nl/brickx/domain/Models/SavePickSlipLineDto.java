package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.List;

public class SavePickSlipLineDto {

    int Id;

    List<SaveReservationLocationDto> SaveReservationLocationDto = new ArrayList<>();

    public SavePickSlipLineDto() {
    }

    public SavePickSlipLineDto(int id, List<SaveReservationLocationDto> closeOrderPickLocationModelList) {
        this.Id = id;
        this.SaveReservationLocationDto = closeOrderPickLocationModelList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public List<SaveReservationLocationDto> getSaveReservationLocationDto() {
        return SaveReservationLocationDto;
    }

    public void setSaveReservationLocationDto(List<SaveReservationLocationDto> saveReservationLocationDto) {
        this.SaveReservationLocationDto = saveReservationLocationDto;
    }
}

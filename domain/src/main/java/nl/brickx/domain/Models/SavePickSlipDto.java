package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.List;

public class SavePickSlipDto {

    int Id;

    List<SavePickSlipLineDto> SavePickSlipLineDto = new ArrayList<>();

    Boolean DoPrint;

    public SavePickSlipDto() {
    }

    public SavePickSlipDto(int id, List<SavePickSlipLineDto> closePickSlipLineModelList, Boolean doPrint) {
        this.Id = id;
        this.SavePickSlipLineDto = closePickSlipLineModelList;
        this.DoPrint = doPrint;
    }

    public void addClosePickSlipLineModel(SavePickSlipLineDto model){
        this.SavePickSlipLineDto.add(model);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public List<SavePickSlipLineDto> getSavePickSlipLineDto() {
        return SavePickSlipLineDto;
    }

    public void setSavePickSlipLineDto(List<SavePickSlipLineDto> savePickSlipLineDto) {
        this.SavePickSlipLineDto = savePickSlipLineDto;
    }

    public Boolean getDoPrint() {
        return DoPrint;
    }

    public void setDoPrint(Boolean doPrint) {
        this.DoPrint = doPrint;
    }
}

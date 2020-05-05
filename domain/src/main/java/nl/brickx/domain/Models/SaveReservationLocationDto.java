package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveReservationLocationDto {

    int Id;

    double Quantity;

    List<BatchNumberSelectionModelDto> SelectedSerialNumbers = new ArrayList<>();

    //Date PickDateTime;

    public SaveReservationLocationDto() {
    }

    public SaveReservationLocationDto(int id, double quantity, List<BatchNumberSelectionModelDto> selectionModelList) {
        this.Id = id;
        Quantity = quantity;
        this.SelectedSerialNumbers = selectionModelList;
        //this.PickDateTime = pickDateTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public List<BatchNumberSelectionModelDto> getSelectedSerialNumbers() {
        return SelectedSerialNumbers;
    }

    public void setSelectedSerialNumbers(List<BatchNumberSelectionModelDto> selectedSerialNumbers) {
        this.SelectedSerialNumbers = selectedSerialNumbers;
    }
}

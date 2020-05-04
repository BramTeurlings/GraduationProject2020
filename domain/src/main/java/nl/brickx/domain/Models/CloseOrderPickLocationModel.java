package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CloseOrderPickLocationModel {

    int id;

    double Quantity;

    List<BatchNumberSelectionModel> selectionModelList= new ArrayList<>();

    Date pickDateTime;
}

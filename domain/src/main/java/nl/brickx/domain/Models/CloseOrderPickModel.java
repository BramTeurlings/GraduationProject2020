package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.List;

public class CloseOrderPickModel {

    int id;

    List<ClosePickSlipLineModel> closePickSlipLineModelList = new ArrayList<>();

    Boolean doPrint;
}

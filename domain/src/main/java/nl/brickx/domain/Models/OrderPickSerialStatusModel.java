package nl.brickx.domain.Models;

public class OrderPickSerialStatusModel {

    String serialnumber;
    Boolean isAvailible;

    public OrderPickSerialStatusModel() {
    }

    public OrderPickSerialStatusModel(String serialnumber, Boolean isAvailible) {
        this.serialnumber = serialnumber;
        this.isAvailible = isAvailible;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Boolean getAvailible() {
        return isAvailible;
    }

    public void setAvailible(Boolean availible) {
        isAvailible = availible;
    }
}

package nl.brickx.domain.Models;

public class OrderPickSerialStatusModel {

    String serialnumber;
    Boolean isAvailible;
    int productId;

    public OrderPickSerialStatusModel() {
    }

    public OrderPickSerialStatusModel(String serialnumber, Boolean isAvailible, int productId) {
        this.serialnumber = serialnumber;
        this.isAvailible = isAvailible;
        this.productId = productId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

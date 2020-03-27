package nl.brickx.domain.Models;

import java.io.Serializable;

public class OrderPickViewPagerModel implements Serializable {

    private String productName;
    private String location;
    private String warehouse;
    private Double amountToPick;

    public OrderPickViewPagerModel(String productName, String location, String warehouse, Double amountToPick) {
        this.productName = productName;
        this.location = location;
        this.warehouse = warehouse;
        this.amountToPick = amountToPick;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Double getAmountToPick() {
        return amountToPick;
    }

    public void setAmountToPick(Double amountToPick) {
        this.amountToPick = amountToPick;
    }

}

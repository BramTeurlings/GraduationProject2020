package nl.brickx.domain.Models;

public class LocationInfoRecyclerModel {

    private String warehouseName;
    private String location;
    private Integer productStock;

    public LocationInfoRecyclerModel() {
    }

    public LocationInfoRecyclerModel(String productCode) {
        this.warehouseName = productCode;
    }

    public LocationInfoRecyclerModel(String productCode, String productName, Integer productStock) {
        this.warehouseName = productCode;
        this.location = productName;
        this.productStock = productStock;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }
}

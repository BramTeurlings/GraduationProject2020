package nl.brickx.domain.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationInfoRecyclerModel implements Serializable {

    private String warehouseName;
    private String location;
    private Integer productStock;
    private String productName;
    private String productSKU;
    private int stockLocationId;
    private int productId;
    private String locationTag;
    private String productScan;
    private Boolean isSerialnumbersRequired = false;
    private List<String> scannedNumbers = new ArrayList<>();
    private List<String> availibleNumbers = new ArrayList<>();

    public LocationInfoRecyclerModel() {
    }

    public LocationInfoRecyclerModel(String locationTag) {
        this.locationTag = locationTag;
    }

    public LocationInfoRecyclerModel(String productCode, String productName, Integer productStock) {
        this.warehouseName = productCode;
        this.location = productName;
        this.productStock = productStock;
    }

    public LocationInfoRecyclerModel(String warehouseName, String location, Integer productStock, String productName) {
        this.warehouseName = warehouseName;
        this.location = location;
        this.productStock = productStock;
        this.productName = productName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocationTag() {
        return locationTag;
    }

    public void setLocationTag(String locationTag) {
        this.locationTag = locationTag;
    }

    public String getProductScan() {
        return productScan;
    }

    public void setProductScan(String productScan) {
        this.productScan = productScan;
    }

    public Boolean getSerialnumbersRequired() {
        return isSerialnumbersRequired;
    }

    public void setSerialnumbersRequired(Boolean serialnumbersRequired) {
        isSerialnumbersRequired = serialnumbersRequired;
    }

    public List<String> getScannedNumbers() {
        return scannedNumbers;
    }

    public void setScannedNumbers(List<String> scannedNumbers) {
        this.scannedNumbers = scannedNumbers;
    }

    public List<String> getAvailibleNumbers() {
        return availibleNumbers;
    }

    public void setAvailibleNumbers(List<String> availibleNumbers) {
        this.availibleNumbers = availibleNumbers;
    }

    public int getStockLocationId() {
        return stockLocationId;
    }

    public void setStockLocationId(int stockLocationId) {
        this.stockLocationId = stockLocationId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }
}

package nl.brickx.domain.Models;

import java.io.Serializable;

public class OrderPickPickListModel implements Serializable {

    private int Id;
    private int quantityRequired;
    private int quantityPicked = 0;
    private String productSku;
    private int ProductId;
    private String productName;
    private Boolean isSerialNumberRequired = false;
    private Boolean isSerialNumberScanned = false;
    private Boolean isLocationScanned = false;
    private Boolean isQuantityMet = false;
    private int currentStock;
    private String productLocation;
    private String locationTag;
    private String warehouseName;
    private String scannedSerialNumber;

    public OrderPickPickListModel() {
    }

    public OrderPickPickListModel(int id, int quantityRequired, int quantityPicked, String productSku, int productId, String productName, Boolean isSerialNumberRequired, Boolean isLocationScanned, String productLocation, String locationTag, String warehouseName) {
        Id = id;
        this.quantityRequired = quantityRequired;
        this.quantityPicked = quantityPicked;
        this.productSku = productSku;
        ProductId = productId;
        this.productName = productName;
        this.isSerialNumberRequired = isSerialNumberRequired;
        this.isLocationScanned = isLocationScanned;
        this.productLocation = productLocation;
        this.locationTag = locationTag;
        this.warehouseName = warehouseName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getSerialNumberRequired() {
        return isSerialNumberRequired;
    }

    public void setSerialNumberRequired(Boolean serialNumberRequired) {
        isSerialNumberRequired = serialNumberRequired;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public String getLocationTag() {
        return locationTag;
    }

    public void setLocationTag(String locationTag) {
        this.locationTag = locationTag;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getQuantityPicked() {
        return quantityPicked;
    }

    public void setQuantityPicked(int quantityPicked) {
        this.quantityPicked = quantityPicked;
    }

    public Boolean getLocationScanned() {
        return isLocationScanned;
    }

    public void setLocationScanned(Boolean locationScanned) {
        isLocationScanned = locationScanned;
    }

    public String getScannedSerialNumber() {
        return scannedSerialNumber;
    }

    public void setScannedSerialNumber(String scannedSerialNumber) {
        this.scannedSerialNumber = scannedSerialNumber;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Boolean getQuantityMet() {
        return isQuantityMet;
    }

    public void setQuantityMet(Boolean quantityMet) {
        isQuantityMet = quantityMet;
    }

    public Boolean getSerialNumberScanned() {
        return isSerialNumberScanned;
    }

    public void setSerialNumberScanned(Boolean serialNumberScanned) {
        isSerialNumberScanned = serialNumberScanned;
    }
}

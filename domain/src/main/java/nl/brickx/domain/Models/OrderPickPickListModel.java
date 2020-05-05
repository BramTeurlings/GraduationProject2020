package nl.brickx.domain.Models;

import android.graphics.drawable.BitmapDrawable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderPickPickListModel implements Serializable {

    private String pickslipNumber;
    private int pickSlipId;
    private int picklistId;
    private int pickSlipLineId;
    private int quantityRequired;
    private int quantityPicked = 0;
    private String productSku;
    private int ProductId;
    private int StockLocationId;
    private String productName;
    private Boolean isSerialNumberRequired = false;
    private Boolean isSerialNumberScanned = false;
    private Boolean isLocationScanned = false;
    private Boolean isQuantityMet = false;
    private Boolean isImageLoaded = false;
    private int currentStock;
    private String productLocation;
    private String locationTag;
    private String warehouseName;
    private String scannedSerialNumber;

    @JsonIgnore
    private transient BitmapDrawable image;

    private List<String> openSerialnumbers = new ArrayList<>();
    private List<String> scannedSerialNumbers = new ArrayList<>();

    public OrderPickPickListModel() {
    }

    public OrderPickPickListModel(int id, int quantityRequired, int quantityPicked, String productSku, int productId, String productName, Boolean isSerialNumberRequired, Boolean isLocationScanned, String productLocation, String locationTag, String warehouseName) {
        picklistId = id;
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

    public int getPicklistId() {
        return picklistId;
    }

    public void setPicklistId(int picklistId) {
        this.picklistId = picklistId;
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

    public BitmapDrawable getImage() {
        return image;
    }

    public void setImage(BitmapDrawable image) {
        this.image = image;
    }

    public Boolean getImageLoaded() {
        return isImageLoaded;
    }

    public void setImageLoaded(Boolean imageLoaded) {
        isImageLoaded = imageLoaded;
    }

    public int getStockLocationId() {
        return StockLocationId;
    }

    public void setStockLocationId(int stockLocationId) {
        StockLocationId = stockLocationId;
    }

    public List<String> getOpenSerialnumbers() {
        return openSerialnumbers;
    }

    public void setOpenSerialnumbers(List<String> openSerialnumbers) {
        this.openSerialnumbers = openSerialnumbers;
    }

    public List<String> getScannedSerialNumbers() {
        return scannedSerialNumbers;
    }

    public void setScannedSerialNumbers(List<String> scannedSerialNumbers) {
        this.scannedSerialNumbers = scannedSerialNumbers;
    }

    public String getPickslipNumber() {
        return pickslipNumber;
    }

    public void setPickslipNumber(String pickslipNumber) {
        this.pickslipNumber = pickslipNumber;
    }

    public int getPickSlipId() {
        return pickSlipId;
    }

    public void setPickSlipId(int pickSlipId) {
        this.pickSlipId = pickSlipId;
    }

    public int getPickSlipLineId() {
        return pickSlipLineId;
    }

    public void setPickSlipLineId(int pickSlipLineId) {
        this.pickSlipLineId = pickSlipLineId;
    }
}

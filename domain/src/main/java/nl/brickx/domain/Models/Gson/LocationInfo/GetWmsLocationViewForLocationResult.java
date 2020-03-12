
package nl.brickx.domain.Models.Gson.LocationInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetWmsLocationViewForLocationResult {

    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("AvailableStock")
    @Expose
    private Double availableStock;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("CombinationName")
    @Expose
    private Object combinationName;
    @SerializedName("CurrentReservations")
    @Expose
    private Double currentReservations;
    @SerializedName("CurrentStock")
    @Expose
    private Double currentStock;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ImageBase64")
    @Expose
    private String imageBase64;
    @SerializedName("LevelName")
    @Expose
    private String levelName;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("MaximumStock")
    @Expose
    private Double maximumStock;
    @SerializedName("MinimumStock")
    @Expose
    private Double minimumStock;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("RowName")
    @Expose
    private String rowName;
    @SerializedName("ScanLocationTag")
    @Expose
    private String scanLocationTag;
    @SerializedName("ShelveName")
    @Expose
    private String shelveName;
    @SerializedName("WareHouseName")
    @Expose
    private String wareHouseName;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Double availableStock) {
        this.availableStock = availableStock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getCombinationName() {
        return combinationName;
    }

    public void setCombinationName(Object combinationName) {
        this.combinationName = combinationName;
    }

    public Double getCurrentReservations() {
        return currentReservations;
    }

    public void setCurrentReservations(Double currentReservations) {
        this.currentReservations = currentReservations;
    }

    public Double getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Double currentStock) {
        this.currentStock = currentStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(Double maximumStock) {
        this.maximumStock = maximumStock;
    }

    public Double getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Double minimumStock) {
        this.minimumStock = minimumStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getScanLocationTag() {
        return scanLocationTag;
    }

    public void setScanLocationTag(String scanLocationTag) {
        this.scanLocationTag = scanLocationTag;
    }

    public String getShelveName() {
        return shelveName;
    }

    public void setShelveName(String shelveName) {
        this.shelveName = shelveName;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

}

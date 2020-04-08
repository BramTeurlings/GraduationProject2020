
package nl.brickx.domain.Models.Gson.Orderpick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockLocation {

    @SerializedName("AvailableStock")
    @Expose
    private Double availableStock;
    @SerializedName("CurrentReservations")
    @Expose
    private Double currentReservations;
    @SerializedName("CurrentStock")
    @Expose
    private Double currentStock;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("MaximumStock")
    @Expose
    private Integer maximumStock;
    @SerializedName("MinimumStock")
    @Expose
    private Integer minimumStock;
    @SerializedName("WareHouseLocation")
    @Expose
    private WareHouseLocation wareHouseLocation;
    @SerializedName("WareHouseLocationName")
    @Expose
    private String wareHouseLocationName;
    @SerializedName("WareHouseName")
    @Expose
    private String wareHouseName;

    public Double getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Double availableStock) {
        this.availableStock = availableStock;
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

    public Integer getMaximumStock() {
        return maximumStock;
    }

    public void setMaximumStock(Integer maximumStock) {
        this.maximumStock = maximumStock;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public WareHouseLocation getWareHouseLocation() {
        return wareHouseLocation;
    }

    public void setWareHouseLocation(WareHouseLocation wareHouseLocation) {
        this.wareHouseLocation = wareHouseLocation;
    }

    public String getWareHouseLocationName() {
        return wareHouseLocationName;
    }

    public void setWareHouseLocationName(String wareHouseLocationName) {
        this.wareHouseLocationName = wareHouseLocationName;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

}

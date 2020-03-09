
package nl.brickx.domain.Models.Gson.ProductInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentStock {

    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("AttributeCombination")
    @Expose
    private Object attributeCombination;
    @SerializedName("AvailableStock")
    @Expose
    private Double availableStock;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("CurrentBackOrders")
    @Expose
    private Double currentBackOrders;
    @SerializedName("CurrentConsignation")
    @Expose
    private Integer currentConsignation;
    @SerializedName("CurrentPurchased")
    @Expose
    private Double currentPurchased;
    @SerializedName("CurrentReservations")
    @Expose
    private Double currentReservations;
    @SerializedName("CurrentStock")
    @Expose
    private Double currentStock;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("StockLocation")
    @Expose
    private List<StockLocation> stockLocation = null;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Object getAttributeCombination() {
        return attributeCombination;
    }

    public void setAttributeCombination(Object attributeCombination) {
        this.attributeCombination = attributeCombination;
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

    public Double getCurrentBackOrders() {
        return currentBackOrders;
    }

    public void setCurrentBackOrders(Double currentBackOrders) {
        this.currentBackOrders = currentBackOrders;
    }

    public Integer getCurrentConsignation() {
        return currentConsignation;
    }

    public void setCurrentConsignation(Integer currentConsignation) {
        this.currentConsignation = currentConsignation;
    }

    public Double getCurrentPurchased() {
        return currentPurchased;
    }

    public void setCurrentPurchased(Double currentPurchased) {
        this.currentPurchased = currentPurchased;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StockLocation> getStockLocation() {
        return stockLocation;
    }

    public void setStockLocation(List<StockLocation> stockLocation) {
        this.stockLocation = stockLocation;
    }

}

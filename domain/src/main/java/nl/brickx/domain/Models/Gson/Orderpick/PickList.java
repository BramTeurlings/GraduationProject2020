
package nl.brickx.domain.Models.Gson.Orderpick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickList {

    @SerializedName("BatchNumber")
    @Expose
    private Object batchNumber;
    @SerializedName("BoxNumber")
    @Expose
    private Object boxNumber;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ProductInfo")
    @Expose
    private ProductInfo productInfo;
    @SerializedName("Quantity")
    @Expose
    private Double quantity;
    @SerializedName("ReservationDate")
    @Expose
    private String reservationDate;
    @SerializedName("StockLocation")
    @Expose
    private StockLocation stockLocation;

    public Object getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Object batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Object getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(Object boxNumber) {
        this.boxNumber = boxNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public StockLocation getStockLocation() {
        return stockLocation;
    }

    public void setStockLocation(StockLocation stockLocation) {
        this.stockLocation = stockLocation;
    }

}


package nl.brickx.domain.Models.Gson.Orderpick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfo {

    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("CustomBarCode")
    @Expose
    private Object customBarCode;
    @SerializedName("Ean13")
    @Expose
    private Object ean13;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("SalesPriceBasic")
    @Expose
    private Double salesPriceBasic;
    @SerializedName("StockManagement")
    @Expose
    private Boolean stockManagement;
    @SerializedName("StockManagementCombinations")
    @Expose
    private Boolean stockManagementCombinations;
    @SerializedName("TrackTrace")
    @Expose
    private Boolean trackTrace;
    @SerializedName("UniqueBatchNumbers")
    @Expose
    private Boolean uniqueBatchNumbers;
    @SerializedName("UpcBarCode")
    @Expose
    private Object upcBarCode;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getCustomBarCode() {
        return customBarCode;
    }

    public void setCustomBarCode(Object customBarCode) {
        this.customBarCode = customBarCode;
    }

    public Object getEan13() {
        return ean13;
    }

    public void setEan13(Object ean13) {
        this.ean13 = ean13;
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

    public Double getSalesPriceBasic() {
        return salesPriceBasic;
    }

    public void setSalesPriceBasic(Double salesPriceBasic) {
        this.salesPriceBasic = salesPriceBasic;
    }

    public Boolean getStockManagement() {
        return stockManagement;
    }

    public void setStockManagement(Boolean stockManagement) {
        this.stockManagement = stockManagement;
    }

    public Boolean getStockManagementCombinations() {
        return stockManagementCombinations;
    }

    public void setStockManagementCombinations(Boolean stockManagementCombinations) {
        this.stockManagementCombinations = stockManagementCombinations;
    }

    public Boolean getTrackTrace() {
        return trackTrace;
    }

    public void setTrackTrace(Boolean trackTrace) {
        this.trackTrace = trackTrace;
    }

    public Boolean getUniqueBatchNumbers() {
        return uniqueBatchNumbers;
    }

    public void setUniqueBatchNumbers(Boolean uniqueBatchNumbers) {
        this.uniqueBatchNumbers = uniqueBatchNumbers;
    }

    public Object getUpcBarCode() {
        return upcBarCode;
    }

    public void setUpcBarCode(Object upcBarCode) {
        this.upcBarCode = upcBarCode;
    }

}

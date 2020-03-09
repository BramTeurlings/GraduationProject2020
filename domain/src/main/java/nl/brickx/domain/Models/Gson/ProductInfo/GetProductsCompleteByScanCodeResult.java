
package nl.brickx.domain.Models.Gson.ProductInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProductsCompleteByScanCodeResult {

    @SerializedName("AllowDiscounts")
    @Expose
    private Boolean allowDiscounts;
    @SerializedName("AttributeCombinations")
    @Expose
    private List<Object> attributeCombinations = null;
    @SerializedName("Categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("CbsCode")
    @Expose
    private Object cbsCode;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("CurrentStock")
    @Expose
    private List<CurrentStock> currentStock = null;
    @SerializedName("CustomBarCode")
    @Expose
    private String customBarCode;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("Documents")
    @Expose
    private List<Object> documents = null;
    @SerializedName("Ean13")
    @Expose
    private Object ean13;
    @SerializedName("HasAttributes")
    @Expose
    private Boolean hasAttributes;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Images")
    @Expose
    private List<Image> images = null;
    @SerializedName("InternalRemarks")
    @Expose
    private Object internalRemarks;
    @SerializedName("LongDescription")
    @Expose
    private Object longDescription;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PackagingUnit")
    @Expose
    private Double packagingUnit;
    @SerializedName("PriceUnits")
    @Expose
    private Integer priceUnits;
    @SerializedName("ProductPropertyValues")
    @Expose
    private List<ProductPropertyValue> productPropertyValues = null;
    @SerializedName("RetailerPrice")
    @Expose
    private Integer retailerPrice;
    @SerializedName("RetailerPriceExclVat")
    @Expose
    private Integer retailerPriceExclVat;
    @SerializedName("SalesPriceBasic")
    @Expose
    private Double salesPriceBasic;
    @SerializedName("SalesPriceBasicIncludingVat")
    @Expose
    private Integer salesPriceBasicIncludingVat;
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
    @SerializedName("Unit")
    @Expose
    private Unit unit;
    @SerializedName("UpAndCrossSells")
    @Expose
    private List<Object> upAndCrossSells = null;
    @SerializedName("UpcBarCode")
    @Expose
    private Object upcBarCode;
    @SerializedName("VatType")
    @Expose
    private VatType vatType;
    @SerializedName("WeightPerUnit")
    @Expose
    private Double weightPerUnit;
    @SerializedName("WeightUnit")
    @Expose
    private WeightUnit weightUnit;

    public Boolean getAllowDiscounts() {
        return allowDiscounts;
    }

    public void setAllowDiscounts(Boolean allowDiscounts) {
        this.allowDiscounts = allowDiscounts;
    }

    public List<Object> getAttributeCombinations() {
        return attributeCombinations;
    }

    public void setAttributeCombinations(List<Object> attributeCombinations) {
        this.attributeCombinations = attributeCombinations;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Object getCbsCode() {
        return cbsCode;
    }

    public void setCbsCode(Object cbsCode) {
        this.cbsCode = cbsCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CurrentStock> getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(List<CurrentStock> currentStock) {
        this.currentStock = currentStock;
    }

    public String getCustomBarCode() {
        return customBarCode;
    }

    public void setCustomBarCode(String customBarCode) {
        this.customBarCode = customBarCode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public List<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Object> documents) {
        this.documents = documents;
    }

    public Object getEan13() {
        return ean13;
    }

    public void setEan13(Object ean13) {
        this.ean13 = ean13;
    }

    public Boolean getHasAttributes() {
        return hasAttributes;
    }

    public void setHasAttributes(Boolean hasAttributes) {
        this.hasAttributes = hasAttributes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Object getInternalRemarks() {
        return internalRemarks;
    }

    public void setInternalRemarks(Object internalRemarks) {
        this.internalRemarks = internalRemarks;
    }

    public Object getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(Object longDescription) {
        this.longDescription = longDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPackagingUnit() {
        return packagingUnit;
    }

    public void setPackagingUnit(Double packagingUnit) {
        this.packagingUnit = packagingUnit;
    }

    public Integer getPriceUnits() {
        return priceUnits;
    }

    public void setPriceUnits(Integer priceUnits) {
        this.priceUnits = priceUnits;
    }

    public List<ProductPropertyValue> getProductPropertyValues() {
        return productPropertyValues;
    }

    public void setProductPropertyValues(List<ProductPropertyValue> productPropertyValues) {
        this.productPropertyValues = productPropertyValues;
    }

    public Integer getRetailerPrice() {
        return retailerPrice;
    }

    public void setRetailerPrice(Integer retailerPrice) {
        this.retailerPrice = retailerPrice;
    }

    public Integer getRetailerPriceExclVat() {
        return retailerPriceExclVat;
    }

    public void setRetailerPriceExclVat(Integer retailerPriceExclVat) {
        this.retailerPriceExclVat = retailerPriceExclVat;
    }

    public Double getSalesPriceBasic() {
        return salesPriceBasic;
    }

    public void setSalesPriceBasic(Double salesPriceBasic) {
        this.salesPriceBasic = salesPriceBasic;
    }

    public Integer getSalesPriceBasicIncludingVat() {
        return salesPriceBasicIncludingVat;
    }

    public void setSalesPriceBasicIncludingVat(Integer salesPriceBasicIncludingVat) {
        this.salesPriceBasicIncludingVat = salesPriceBasicIncludingVat;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Object> getUpAndCrossSells() {
        return upAndCrossSells;
    }

    public void setUpAndCrossSells(List<Object> upAndCrossSells) {
        this.upAndCrossSells = upAndCrossSells;
    }

    public Object getUpcBarCode() {
        return upcBarCode;
    }

    public void setUpcBarCode(Object upcBarCode) {
        this.upcBarCode = upcBarCode;
    }

    public VatType getVatType() {
        return vatType;
    }

    public void setVatType(VatType vatType) {
        this.vatType = vatType;
    }

    public Double getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(Double weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

}

package nl.brickx.domain.Models;

public class ProductInfoHolder {

    private String productName;
    private String sku;
    private Integer stock;
    private String ean;
    private String upc;
    private String customBarcode;
    private Integer amountPerPackage;
    private String weight;

    public ProductInfoHolder() {
    }

    public ProductInfoHolder(String productName, String sku, Integer stock, String ean, String upc, String customBarcode, Integer amountPerPackage, String weight) {
        this.productName = productName;
        this.sku = sku;
        this.stock = stock;
        this.ean = ean;
        this.upc = upc;
        this.customBarcode = customBarcode;
        this.amountPerPackage = amountPerPackage;
        this.weight = weight;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCustomBarcode() {
        return customBarcode;
    }

    public void setCustomBarcode(String customBarcode) {
        this.customBarcode = customBarcode;
    }

    public Integer getAmountPerPackage() {
        return amountPerPackage;
    }

    public void setAmountPerPackage(Integer amountPerPackage) {
        this.amountPerPackage = amountPerPackage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}

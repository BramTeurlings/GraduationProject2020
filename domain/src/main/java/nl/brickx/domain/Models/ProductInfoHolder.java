package nl.brickx.domain.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoHolder {

    private String productName = "-";
    private String sku = "-";
    private Double stock = 0d;
    private String ean = "-";
    private String upc = "-";
    private String customBarcode = "-";
    private Double amountPerPackage = 0d;
    private String weight = "-";
    private List<ProductInfoRecyclerModel> properties = new ArrayList<>();
    private List<LocationInfoRecyclerModel> locations = new ArrayList<>();

    public ProductInfoHolder() {

    }

    public ProductInfoHolder(String productName, String sku, Double stock, String ean, String upc, String customBarcode, Double amountPerPackage, String weight) {
        this.productName = productName;
        this.sku = sku;
        this.stock = stock;
        this.ean = ean;
        this.upc = upc;
        this.customBarcode = customBarcode;
        this.amountPerPackage = amountPerPackage;
        this.weight = weight;
    }

    public ProductInfoHolder(String productName, String sku, Double stock, String ean, String upc, String customBarcode, Double amountPerPackage, String weight, List<ProductInfoRecyclerModel> properties) {
        this.productName = productName;
        this.sku = sku;
        this.stock = stock;
        this.ean = ean;
        this.upc = upc;
        this.customBarcode = customBarcode;
        this.amountPerPackage = amountPerPackage;
        this.weight = weight;
        this.properties = properties;
    }

    public ProductInfoHolder(String productName, String sku, Double stock, String ean, String upc, String customBarcode, Double amountPerPackage, String weight, List<ProductInfoRecyclerModel> properties, List<LocationInfoRecyclerModel> locations) {
        this.productName = productName;
        this.sku = sku;
        this.stock = stock;
        this.ean = ean;
        this.upc = upc;
        this.customBarcode = customBarcode;
        this.amountPerPackage = amountPerPackage;
        this.weight = weight;
        this.properties = properties;
        this.locations = locations;
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

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
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

    public Double getAmountPerPackage() {
        return amountPerPackage;
    }

    public void setAmountPerPackage(Double amountPerPackage) {
        this.amountPerPackage = amountPerPackage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<ProductInfoRecyclerModel> getProperties() {
        return properties;
    }

    public void setProperties(List<ProductInfoRecyclerModel> properties) {
        this.properties = properties;
    }

    public List<LocationInfoRecyclerModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationInfoRecyclerModel> locations) {
        this.locations = locations;
    }
}

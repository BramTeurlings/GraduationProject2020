package nl.brickx.domain.Models;

public class LocationInfoRecyclerModel {

    private String productCode;
    private String productName;
    private Integer productStock;

    public LocationInfoRecyclerModel(String productCode) {
        this.productCode = productCode;
    }

    public LocationInfoRecyclerModel(String productCode, String productName, Integer productStock) {
        this.productCode = productCode;
        this.productName = productName;
        this.productStock = productStock;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }
}

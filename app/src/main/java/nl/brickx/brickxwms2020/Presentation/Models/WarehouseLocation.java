package nl.brickx.brickxwms2020.Presentation.Models;

public class WarehouseLocation {

    private Integer id;
    private String code;
    private String description;
    private Double stockAmount;

    public WarehouseLocation() {
    }

    public WarehouseLocation(Integer id, String code, String description, Double stockAmount) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.stockAmount = stockAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Double stockAmount) {
        this.stockAmount = stockAmount;
    }
}

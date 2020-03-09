package nl.brickx.domain.Models;

public class ProductInfoRecyclerModel {

    private String property;
    private String value;
    private String unit;

    public ProductInfoRecyclerModel() {
    }

    public ProductInfoRecyclerModel(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public ProductInfoRecyclerModel(String property, String value, String unit) {
        this.property = property;
        this.value = value;
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

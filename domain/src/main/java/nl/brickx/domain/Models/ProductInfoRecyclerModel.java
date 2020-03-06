package nl.brickx.domain.Models;

public class ProductInfoRecyclerModel {

    private String property;
    private String value;

    public ProductInfoRecyclerModel() {
    }

    public ProductInfoRecyclerModel(String property, String value) {
        this.property = property;
        this.value = value;
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
}

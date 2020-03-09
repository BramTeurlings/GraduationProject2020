
package nl.brickx.domain.Models.Gson.ProductInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VatType {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("PrintDescription")
    @Expose
    private String printDescription;
    @SerializedName("Vat")
    @Expose
    private List<Vat> vat = null;
    @SerializedName("VatCode")
    @Expose
    private String vatCode;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrintDescription() {
        return printDescription;
    }

    public void setPrintDescription(String printDescription) {
        this.printDescription = printDescription;
    }

    public List<Vat> getVat() {
        return vat;
    }

    public void setVat(List<Vat> vat) {
        this.vat = vat;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

}

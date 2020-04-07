
package nl.brickx.domain.Models.Gson.OrderPickLanding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GetPickslipListResult {

    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("OrderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("PickSlipDate")
    @Expose
    private Date pickSlipDate;
    @SerializedName("PickSlipNumber")
    @Expose
    private String pickSlipNumber;
    @SerializedName("RequestedDeliveryDate")
    @Expose
    private String requestedDeliveryDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getPickSlipDate() {
        return pickSlipDate;
    }

    public void setPickSlipDate(Date pickSlipDate) {
        this.pickSlipDate = pickSlipDate;
    }

    public String getPickSlipNumber() {
        return pickSlipNumber;
    }

    public void setPickSlipNumber(String pickSlipNumber) {
        this.pickSlipNumber = pickSlipNumber;
    }

    public String getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public void setRequestedDeliveryDate(String requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

}

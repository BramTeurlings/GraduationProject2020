
package nl.brickx.domain.Models.Gson.Orderpick;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPickslipByNumberResult {

    @SerializedName("BoxNumber")
    @Expose
    private Object boxNumber;
    @SerializedName("ColliQty")
    @Expose
    private Integer colliQty;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("Courier")
    @Expose
    private Object courier;
    @SerializedName("CourierServiceLevel")
    @Expose
    private Object courierServiceLevel;
    @SerializedName("CourierTrackTraceNumber")
    @Expose
    private Object courierTrackTraceNumber;
    @SerializedName("DateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("DeliveryAddress1")
    @Expose
    private String deliveryAddress1;
    @SerializedName("DeliveryAddress2")
    @Expose
    private Object deliveryAddress2;
    @SerializedName("DeliveryAddress3")
    @Expose
    private Object deliveryAddress3;
    @SerializedName("DeliveryCity")
    @Expose
    private String deliveryCity;
    @SerializedName("DeliveryCountry")
    @Expose
    private String deliveryCountry;
    @SerializedName("DeliveryZipCode")
    @Expose
    private String deliveryZipCode;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("LockGranted")
    @Expose
    private Boolean lockGranted;
    @SerializedName("Memo")
    @Expose
    private String memo;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("OrderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("PickList")
    @Expose
    private List<PickList> pickList = null;
    @SerializedName("PickSlipDate")
    @Expose
    private String pickSlipDate;
    @SerializedName("PickSlipMemoCustomizable")
    @Expose
    private Object pickSlipMemoCustomizable;
    @SerializedName("PickSlipNumber")
    @Expose
    private String pickSlipNumber;
    @SerializedName("RequestedDeliveryDate")
    @Expose
    private String requestedDeliveryDate;
    @SerializedName("TransSmartDocumentId")
    @Expose
    private Integer transSmartDocumentId;
    @SerializedName("TransSmartStatus")
    @Expose
    private Object transSmartStatus;
    @SerializedName("UserName")
    @Expose
    private String userName;

    public Object getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(Object boxNumber) {
        this.boxNumber = boxNumber;
    }

    public Integer getColliQty() {
        return colliQty;
    }

    public void setColliQty(Integer colliQty) {
        this.colliQty = colliQty;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Object getCourier() {
        return courier;
    }

    public void setCourier(Object courier) {
        this.courier = courier;
    }

    public Object getCourierServiceLevel() {
        return courierServiceLevel;
    }

    public void setCourierServiceLevel(Object courierServiceLevel) {
        this.courierServiceLevel = courierServiceLevel;
    }

    public Object getCourierTrackTraceNumber() {
        return courierTrackTraceNumber;
    }

    public void setCourierTrackTraceNumber(Object courierTrackTraceNumber) {
        this.courierTrackTraceNumber = courierTrackTraceNumber;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDeliveryAddress1() {
        return deliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        this.deliveryAddress1 = deliveryAddress1;
    }

    public Object getDeliveryAddress2() {
        return deliveryAddress2;
    }

    public void setDeliveryAddress2(Object deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public Object getDeliveryAddress3() {
        return deliveryAddress3;
    }

    public void setDeliveryAddress3(Object deliveryAddress3) {
        this.deliveryAddress3 = deliveryAddress3;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryZipCode() {
        return deliveryZipCode;
    }

    public void setDeliveryZipCode(String deliveryZipCode) {
        this.deliveryZipCode = deliveryZipCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLockGranted() {
        return lockGranted;
    }

    public void setLockGranted(Boolean lockGranted) {
        this.lockGranted = lockGranted;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public List<PickList> getPickList() {
        return pickList;
    }

    public void setPickList(List<PickList> pickList) {
        this.pickList = pickList;
    }

    public String getPickSlipDate() {
        return pickSlipDate;
    }

    public void setPickSlipDate(String pickSlipDate) {
        this.pickSlipDate = pickSlipDate;
    }

    public Object getPickSlipMemoCustomizable() {
        return pickSlipMemoCustomizable;
    }

    public void setPickSlipMemoCustomizable(Object pickSlipMemoCustomizable) {
        this.pickSlipMemoCustomizable = pickSlipMemoCustomizable;
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

    public Integer getTransSmartDocumentId() {
        return transSmartDocumentId;
    }

    public void setTransSmartDocumentId(Integer transSmartDocumentId) {
        this.transSmartDocumentId = transSmartDocumentId;
    }

    public Object getTransSmartStatus() {
        return transSmartStatus;
    }

    public void setTransSmartStatus(Object transSmartStatus) {
        this.transSmartStatus = transSmartStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

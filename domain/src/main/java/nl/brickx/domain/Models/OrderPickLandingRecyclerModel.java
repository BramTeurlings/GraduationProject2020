package nl.brickx.domain.Models;

public class OrderPickLandingRecyclerModel {

    String orderId;
    String orderName;
    OrderPickLandingStatus orderStatus;

    public OrderPickLandingRecyclerModel() {
    }

    public OrderPickLandingRecyclerModel(String orderId, String orderName, OrderPickLandingStatus orderStatus) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderStatus = orderStatus;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public OrderPickLandingStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderPickLandingStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

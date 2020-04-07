package nl.brickx.domain.Models;

import java.util.Date;

public class OrderPickLandingRecyclerModel {

    int orderId;
    Date orderDate;
    String orderName;
    OrderPickLandingStatus orderStatus;

    public OrderPickLandingRecyclerModel() {
    }

    public OrderPickLandingRecyclerModel(int orderId, Date orderDate, String orderName, OrderPickLandingStatus orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}

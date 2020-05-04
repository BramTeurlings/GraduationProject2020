package nl.brickx.domain.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GsonOrderPickPickList implements Serializable {
    List<OrderPickPickListModel> orders = new ArrayList<>();

    public GsonOrderPickPickList() {
    }

    public GsonOrderPickPickList(List<OrderPickPickListModel> orders) {
        this.orders = orders;
    }

    public List<OrderPickPickListModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderPickPickListModel> orders) {
        this.orders = orders;
    }
}

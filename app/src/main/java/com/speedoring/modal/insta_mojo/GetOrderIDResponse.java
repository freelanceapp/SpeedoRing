package com.speedoring.modal.insta_mojo;

import com.google.gson.annotations.SerializedName;

public class GetOrderIDResponse {

    @SerializedName("order_id")
    private String orderID;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}

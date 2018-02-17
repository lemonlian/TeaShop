package com.bean;

/**
 * 对应订单表的模型
 */
public class Order {

    private int orderId;

    private int teaId;

    private int userId;

    private int orderState;

    private int teaBuyNum;

    private double orderPrice;

    public int getOrderBuyNum() {
        return teaBuyNum;
    }

    public void setOrderBuyNum(int teaBuyNum) {
        this.teaBuyNum = teaBuyNum;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }


}

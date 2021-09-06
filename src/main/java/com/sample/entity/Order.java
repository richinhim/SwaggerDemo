package com.sample.entity;

/**
 * Order Entity
 */
public class Order {
    private String id = null;
    private int quantity = 0;
    private String date = null;
    private String status = null;
    private boolean complete = false;

    public Order(){}
    public Order(String id, int quantity, String date, String status, boolean complete) {
        this.id = id;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

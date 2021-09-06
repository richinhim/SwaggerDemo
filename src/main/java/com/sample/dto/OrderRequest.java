package com.sample.dto;

/**
 * Order 정보 요청용 DTO
 */
public class OrderRequest {
    private String id = null;
    private int quantity = 0;
    private String status = null;

    public OrderRequest(){}

    public OrderRequest(String id, int quantity, String status) {
        this.id = id;
        this.quantity = quantity;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

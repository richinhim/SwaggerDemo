package com.sample.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Order 정보 응답용 DTO
 */
public class OrderResponse {

    @ApiModelProperty(example = "Order id")
    private String id = null;

    @ApiModelProperty(example = "주문 수량")
    private int quantity = 0;

    @ApiModelProperty(example = "주문 일자")
    private String date = null;

    @ApiModelProperty(example = "주문 상태")
    private String status = null;

    @ApiModelProperty(example = "주문 완료 여부")
    private boolean complete = false;

    public OrderResponse() {}

    public OrderResponse(String id, int quantity, String date, String status, boolean complete) {
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

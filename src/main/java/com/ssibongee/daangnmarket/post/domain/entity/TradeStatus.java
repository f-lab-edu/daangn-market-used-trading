package com.ssibongee.daangnmarket.post.domain.entity;

public enum TradeStatus {
    SALE("판매중"),
    RESERVED("예약중"),
    SOLD("판매완료");

    private String status;

    TradeStatus(String status) {
        this.status = status;
    }

    public String getTradeStatus() {
        return this.status;
    }
}

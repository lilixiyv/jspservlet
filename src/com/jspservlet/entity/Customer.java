package com.jspservlet.entity;

import java.util.List;

public class Customer extends User {
    private Integer vipLevel;

    private List<Order> orders;

    private Double totalCost;

    public Customer() {
    }

    public Customer(String name, String password, String id, String email, String telephone,
                    String location, Integer vipLevel, List<Order> orders, Double totalCost) {
        super(name, password, id, email, telephone, location);
        this.vipLevel = vipLevel;
        this.orders = orders;
        this.totalCost = totalCost;
    }

    public Double upExpense() {
        return ((this.getVipLevel() + 1) * 1000.0);
    }

    public void upgradeVip() {
        Integer vipLevel = this.getVipLevel();
        if (vipLevel >= 10) {
            vipLevel = 9;
        }
        Double rate = (10 - vipLevel) * 0.1;
        Double paid = rate * this.totalCost;
        while (paid >= this.upExpense()) {
            paid -= this.upExpense();
            this.vipLevel += 1;
        }
    }

    public Integer getVipLevel() {
        return (int) Math.floor(this.vipLevel);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setTotalCost(Double currentBuy) {
        this.totalCost += currentBuy;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }
}

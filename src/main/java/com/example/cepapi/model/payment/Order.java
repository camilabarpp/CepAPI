package com.example.cepapi.model.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Order {
    private String totalCost;
    private boolean isClosed = false;
    public String setTotalCost (String cost) {
        this.totalCost += cost;
        return cost;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}

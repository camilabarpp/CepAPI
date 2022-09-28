package com.example.cepapi.model.drink;

public class Tea implements Drink {
    @Override
    public String servir() {
        return "\nAdding 100ml of tea";
    }

    @Override
    public Double getPrice() {
        return 1.00d;
    }

    @Override
    public String toString() {
        return "Tea";
    }
}

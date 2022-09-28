package com.example.cepapi.patterns.decorator.drink;

public class Tea implements Drink {
    String name = "Tea";

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

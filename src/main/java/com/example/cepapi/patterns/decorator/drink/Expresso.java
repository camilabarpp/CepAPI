package com.example.cepapi.patterns.decorator.drink;

public class Expresso  implements Drink {
    String name = "Expresso";
    @Override
    public String servir() {
        return "\nAdding 50ml of expresso";
    }

    @Override
    public Double getPrice() {
        return 1.50;
    }

    @Override
    public String toString() {
        return "Expresso";
    }
}

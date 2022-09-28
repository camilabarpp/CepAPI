package com.example.cepapi.cafe.model.drink;

public class Expresso  implements Drink {
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

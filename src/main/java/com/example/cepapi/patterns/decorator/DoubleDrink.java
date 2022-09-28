package com.example.cepapi.patterns.decorator;

import com.example.cepapi.cafe.model.drink.Drink;

public class DoubleDrink extends DrinkDecorator {

    //Decorator de DoubleDrink
   public DoubleDrink(Drink drink) { //Referecia ao Drink original
       super(drink);
   }
    @Override
    public String servir() {
        drink.servir();
        drink.servir();
        return "\nAdding double of " + drink;
    }

    @Override
    public Double getPrice() {
        return drink.getPrice() * 2.00;
    }
}

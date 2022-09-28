package com.example.cepapi.patterns.decorator;

import com.example.cepapi.model.drink.Drink;

public abstract class DrinkDecorator implements Drink {

    //Apartir dessa composição, nós vamos conseguir adcionar novas funcionalidades
    //e ainda respeitar a ‘interface’ Drink
    protected Drink drink;

    protected DrinkDecorator(Drink drink) {
        this.drink = drink;
    }
}

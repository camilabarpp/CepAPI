package com.example.cepapi.service;

import com.example.cepapi.model.drink.Drink;
import com.example.cepapi.model.drink.Expresso;
import com.example.cepapi.model.drink.Tea;
import com.example.cepapi.patterns.decorator.DoubleDrink;
import com.example.cepapi.patterns.decorator.Milk;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CafeService {
    private ShoppingCartService shoppingCartService;

    public String order(String name, Drink drink) {
        String sum = shoppingCartService.getPrice(drink.getPrice());
        shoppingCartService.add(name);
        return "\n\nOrdering a " + name +
                drink.servir() + "\nPrice: R$ " +
                drink.getPrice() + "\n" + "\nTotal amount: R$ " + sum;
    }
    public String test1() {
        return order("Expresso", new Expresso());
    }

    public String test2() {
        return order("Tea ", new Tea());
    }

    public String test3() {
        return order("Lungo ", new DoubleDrink(new Expresso()));
    }

    public String test4() {
        return order("Cafe Au Lait ", new Milk(new Expresso()));
    }

    public String test5() {
        return order("English Tea  ", new Milk(new Tea()));
    }

    public String test6() {
        return order("British Tea  ",
                new DoubleDrink(
                        new Tea()));
    }

    public String menu() {
        return """
                1 - Expresso      R$ 1,50
                2 - Tea           R$ 1,00
                3 - Lungo         R$ 3,00
                4 - Cafe Au Lait  R$ 2,00
                5 - English Tea   R$ 1,50
                6 - British Tea   R$ 2,00""";

    }

    public String deleteShoppingCart() {
        if (!shoppingCartService.items.isEmpty()) {
            shoppingCartService.items.clear();
        }
        return "";
    }
}


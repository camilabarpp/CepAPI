package com.example.cepapi.patterns.decorator;

import com.example.cepapi.patterns.decorator.drink.Drink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
 //   private List<Drink> drinks;
    private String name;
    private String price;
}

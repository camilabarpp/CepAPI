package com.example.cepapi.cafe.controller;

import com.example.cepapi.cafe.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("shoppingCart/")
@AllArgsConstructor
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @GetMapping
    public String showShoppingCart() {
        return "Total amount: R$ " + shoppingCartService.showShoppingCart() +
                "\n" + shoppingCartService.getNomes();
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void deleteShoppingCart() {
        shoppingCartService.deleteShoppingCart();
    }

}

package com.example.cepapi.cafe.service;

import com.example.cepapi.cafe.model.payment.CreditCard;
import com.example.cepapi.cafe.model.payment.DebitCard;
import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.patterns.strategy.PayByCreditCard;
import com.example.cepapi.patterns.strategy.PayByDebitCard;
import com.example.cepapi.patterns.strategy.PayByPayPal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class PaymentService {
    private ShoppingCartService shoppingCartService;
    private PayByCreditCard payByCreditCard;
    private PayByDebitCard payByDebitCard;
    private PayByPayPal payByPayPal;

    public String payAndVerifyCreditCard(String id, CreditCard creditCard) {
        payByCreditCard.verify(id, creditCard);
        return payByCreditCard.pay(shoppingCartService.showShoppingCart());
    }
    public String payAndVerifyDebitCard(String id, DebitCard debitCard) {
        payByDebitCard.verify(id, debitCard);
        return payByDebitCard.pay(shoppingCartService.showShoppingCart());
    }
    public String payAndVerifyPayPal(String id, PayPal payPal) {
        payByPayPal.verify(id, payPal);
        return payByPayPal.pay(shoppingCartService.showShoppingCart());
    }
}

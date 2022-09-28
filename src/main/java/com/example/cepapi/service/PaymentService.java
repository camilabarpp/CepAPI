package com.example.cepapi.service;

import com.example.cepapi.model.payment.CreditCard;
import com.example.cepapi.model.payment.DebitCard;
import com.example.cepapi.model.payment.PayPal;
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

    public String payAndVerifyCreditCard(CreditCard creditCard) {
        payByCreditCard.verify(creditCard);
        return payByCreditCard.pay(shoppingCartService.showShoppingCart());
    }
    public String payAndVerifyDebitCard(DebitCard debitCard) {
        payByDebitCard.verify(debitCard);
        return payByDebitCard.pay(shoppingCartService.showShoppingCart());
    }

    public String payAndVerifyPayPal(PayPal payPal) {
        payByPayPal.verify(payPal);
        return payByPayPal.pay(shoppingCartService.showShoppingCart());
    }
}
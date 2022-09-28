package com.example.cepapi.cafe.controller;

import com.example.cepapi.cafe.model.payment.CreditCard;
import com.example.cepapi.cafe.model.payment.DebitCard;
import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.cafe.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
@AllArgsConstructor
public class PaymentController {

    private PaymentService service;

    @GetMapping("/credito/{id}")
    public String verifyAndPayCreditCard(@RequestBody CreditCard creditCard, @PathVariable String id) {
        return service.payAndVerifyCreditCard(id, creditCard);
    }

    @GetMapping("/debito/{id}")
    public String verifyAndPayDebitCard(@RequestBody DebitCard debitCard, @PathVariable String id) {
        return service.payAndVerifyDebitCard(id, debitCard);
    }

    @GetMapping("/paypal/{id}")
    public String verifyAndPayPayPal(@PathVariable String id, @RequestBody PayPal payPal) {
        return service.payAndVerifyPayPal(id, payPal);
    }
}

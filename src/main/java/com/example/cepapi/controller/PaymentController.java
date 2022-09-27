package com.example.cepapi.controller;

import com.example.cepapi.model.payment.CreditCard;
import com.example.cepapi.model.payment.DebitCard;
import com.example.cepapi.model.payment.PayPal;
import com.example.cepapi.model.pessoa.Pessoa;
import com.example.cepapi.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@AllArgsConstructor
public class PaymentController {

    private PaymentService service;

    @GetMapping("/credito")
    public String verifyAndPayCreditCard(@RequestBody CreditCard creditCard) {
        return service.payAndVerifyCreditCard(creditCard);
    }

    @GetMapping("/debito")
    public String berifyAndPayDebitCard(@RequestBody DebitCard debitCard) {
        return service.payAndVerifyDebitCard(debitCard);
    }

    @GetMapping("/paypal")
    public String berifyAndPayPayPal(@RequestBody PayPal payPal) {
        return service.payAndVerifyPayPal(payPal);
    }
}

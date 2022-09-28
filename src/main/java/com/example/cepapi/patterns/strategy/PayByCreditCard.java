package com.example.cepapi.patterns.strategy;

import com.example.cepapi.model.payment.CreditCard;
import com.example.cepapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class PayByCreditCard implements PayStrategy {
    private static final CreditCard card = new CreditCard.Builder()
            .nomeTitular("Camila")
            .number("123456")
            .dateExpiration("02/28")
            .cvv("123")
            .build();
    private boolean signedIn;
    private boolean signedIn2;
    private boolean signedIn3;
    private final ShoppingCartService shoppingCartService;

    public PayByCreditCard(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public String pay(String paymentAmount) {
        if (signedIn && signedIn2 && signedIn3) {
            if (paymentAmount.startsWith("0,0")) {
                return  "Total amount R$ 0,00" +
                        "\nShopping cart is empty!";
            } else {
                shoppingCartService.deleteShoppingCart();
                return "Data verification has been sucessfull. \n" +"Paying " + paymentAmount + " using CreditCard.";
            }
        } else {
            return "Wrong number card, date expiration or cvv!";
        }
    }
    public void verify(CreditCard creditCard) {
        var numberAccount = creditCard.getNumber().equals(card.getNumber());
        var dateExpirationAccount = creditCard.getDateExpiration().equals(card.getDateExpiration());
        var cvvAccount = creditCard.getCvv().equals(card.getCvv());
        setSignedIn(numberAccount, dateExpirationAccount, cvvAccount);
    }

    public void setSignedIn(boolean signedIn, boolean signedIn2, boolean signedIn3) {
        this.signedIn = signedIn;
        this.signedIn2 = signedIn2;
        this.signedIn3 = signedIn3;
    }
}

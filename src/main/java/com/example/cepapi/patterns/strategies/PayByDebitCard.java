package com.example.cepapi.patterns.strategies;

import com.example.cepapi.model.payment.DebitCard;
import com.example.cepapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;


@Service
public class PayByDebitCard implements PayStrategy {
    private static final DebitCard card = new DebitCard.Builder()
            .nomeTitular("Camila")
            .number("123456")
            .dateExpiration("02/28")
            .cvv("123")
            .build();

    private boolean signedIn;
    private boolean signedIn2;
    private boolean signedIn3;
    private final ShoppingCartService shoppingCartService;

    public PayByDebitCard(ShoppingCartService shoppingCartService) {
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
                return "Data verification has been sucessfull. \n" +"Paying " + paymentAmount + " using DebitCard.";
            }
        } else {
            return "Wrong number card, date expiration or cvv!";
        }
    }

    public void verify(DebitCard debitCard) {
        var numberAccount = debitCard.getNumber().equals(card.getNumber());
        var dateExpirationAccount = debitCard.getDateExpiration().equals(card.getDateExpiration());
        var cvvAccount = debitCard.getCvv().equals(card.getCvv());
        setSignedIn(numberAccount, dateExpirationAccount, cvvAccount);
    }

    public void setSignedIn(boolean signedIn, boolean signedIn2, boolean signedIn3) {
        this.signedIn = signedIn;
        this.signedIn2 = signedIn2;
        this.signedIn3 = signedIn3;
    }}

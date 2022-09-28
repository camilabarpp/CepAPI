package com.example.cepapi.patterns.strategy;

import com.example.cepapi.cafe.model.payment.DebitCard;
import com.example.cepapi.cafe.service.ShoppingCartService;
import com.example.cepapi.registrationPeople.service.CadastroServices;
import org.springframework.stereotype.Service;

@Service
public class PayByDebitCard implements PayStrategy {
    private boolean signedIn;
    private boolean signedIn2;
    private boolean signedIn3;
    private final ShoppingCartService shoppingCartService;
    private final CadastroServices cadastroServices;
    public PayByDebitCard(ShoppingCartService shoppingCartService, CadastroServices cadastroServices) {
        this.shoppingCartService = shoppingCartService;
        this.cadastroServices = cadastroServices;
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

    public void verify(String id, DebitCard debitCard) {
        var found = cadastroServices.findById(id);
        var numberAccount = debitCard.getNumber().equals(
                found.getDebitCard().getNumber());
        var dateExpirationAccount = debitCard.getDateExpiration().equals(
                found.getDebitCard().getDateExpiration());
        var cvvAccount = debitCard.getCvv().equals(
                found.getDebitCard().getCvv());
        setSignedIn(numberAccount, dateExpirationAccount, cvvAccount);
    }

    public void setSignedIn(boolean signedIn, boolean signedIn2, boolean signedIn3) {
        this.signedIn = signedIn;
        this.signedIn2 = signedIn2;
        this.signedIn3 = signedIn3;
    }}

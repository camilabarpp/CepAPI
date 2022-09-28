package com.example.cepapi.patterns.strategy;

import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.cafe.service.ShoppingCartService;
import com.example.cepapi.registrationPeople.service.CadastroServices;
import org.springframework.stereotype.Service;

@Service
public class PayByPayPal implements PayStrategy {
/*
    private final PayPal card = new PayPal
            .builder("camila@gmail.com","123456")
            .build();
*/

    private boolean signedIn;
    private boolean signedIn2;
    private final ShoppingCartService shoppingCartService;
    private final CadastroServices cadastroServices;

    public PayByPayPal(ShoppingCartService shoppingCartService, CadastroServices cadastroServices) {
        this.shoppingCartService = shoppingCartService;
        this.cadastroServices = cadastroServices;
    }

    public void verify(String id,PayPal payPal) {
        var found = cadastroServices.findById(id);
        var equals = payPal.getEmail().equals(
                found.getPaypal().getEmail());
        var equals2 = payPal.getPassword().equals(
                found.getPaypal().getPassword());
        setSignedIn(equals, equals2);
    }
    @Override
    public String pay(String paymentAmount) {
        if (signedIn && signedIn2) {
            if (paymentAmount.startsWith("0")) {
                return  "Total amount R$ 0,00" +
                        "\nShopping cart is empty!";
            } else {
                shoppingCartService.deleteShoppingCart();
                return "Data verification has been sucessfull. \n" +"Paying " + paymentAmount + " using PayPal.";
            }
        } else {
            return "Wrong email or password!";
        }
    }

    private void setSignedIn(boolean signedIn, boolean equals2) {
        this.signedIn = signedIn;
        this.signedIn2 = equals2;
    }
}

package com.example.cepapi.patterns.strategy;

import com.example.cepapi.model.payment.PayPal;
import com.example.cepapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class PayByPayPal implements PayStrategy {

    private final PayPal card = new PayPal
            .builder("camila@gmail.com","123456")
            .nometitular("Camila")
            .build();

    private boolean signedIn;
    private boolean signedIn2;
    private final ShoppingCartService shoppingCartService;

    public PayByPayPal(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    public void verify(PayPal payPal) {
        var equals = payPal.getEmail().equals(card.getEmail());
        var equals2 = payPal.getPassword().equals(card.getPassword());
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

package com.example.cepapi.patterns.strategy;

import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.cafe.service.ShoppingCartService;
import com.example.cepapi.registrationPeople.model.pessoa.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayByPayPal2 implements PayStrategy {
    private final PayPal card = new PayPal
            .builder("camila@gmail.com","123456")
            .build();

    private boolean signedIn;
    private boolean signedIn2;
    private ShoppingCartService shoppingCartService;
//    private CadastroServices cadastroServices;


    public PayByPayPal2(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public void verify(Pessoa pessoa, PayPal payPal) {
        var equals = payPal.getEmail().equals(pessoa.getPaypal());
        var equals2 = payPal.getPassword().equals(pessoa.getPaypal());
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

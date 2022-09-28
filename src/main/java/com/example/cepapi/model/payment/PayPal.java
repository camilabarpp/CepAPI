package com.example.cepapi.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
@Getter
@AllArgsConstructor
public class PayPal {
    private final String nomeTitular;
    private final String email;
    private final String password;

    public PayPal(builder builder) {
        this.nomeTitular = builder.nomeTitular;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class builder {

        private String nomeTitular;
        private final String email;
        private final String password;

        public builder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public builder nometitular(String nomeTitular) {
            this.nomeTitular = nomeTitular;
            return this;
        }

        public PayPal build() {
            return new PayPal(this);
        }
    }
}

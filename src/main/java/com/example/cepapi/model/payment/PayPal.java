package com.example.cepapi.model.payment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PayPal {
    private final String id;
    private final String nomeTitular;
    private final String email;
    private final String password;

    public PayPal(builder builder) {
        this.id = builder.id;
        this.nomeTitular = builder.nomeTitular;
        this.email = builder.email;
        this.password = builder.password;

    }

    public static class builder {

        private String id;
        private String nomeTitular;
        private final String email;
        private final String password;

        public builder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public builder id(String id) {
            this.id = id;
            return this;
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

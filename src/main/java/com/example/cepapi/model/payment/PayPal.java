package com.example.cepapi.model.payment;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PayPal {
    private String nomeTitular;
    private String email;
    private String password;

    public PayPal(builder builder) {
        this.nomeTitular = builder.nomeTitular;
        this.email = builder.email;
        this.password = builder.password;

    }
    @Getter @Setter
    public static class builder {
        private String nomeTitular;
        private String email;
        private String password;

        public builder() {
        }

        public builder email(String email) {
            this.email = email;
            return this;
        }

        public builder password(String password) {
            this.password = password;
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

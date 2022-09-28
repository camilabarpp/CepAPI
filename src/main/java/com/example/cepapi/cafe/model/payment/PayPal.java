package com.example.cepapi.cafe.model.payment;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class PayPal {
    private String email;
    private String password;

    public PayPal(builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }
    @Getter @Setter
    public static class builder {
        private final String email;
        private final String password;

        public builder(String email, String password) {
            this.email = email;
            this.password = password;
        }
        public PayPal build() {
            return new PayPal(this);
        }
    }
}

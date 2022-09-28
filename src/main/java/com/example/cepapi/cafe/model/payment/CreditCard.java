package com.example.cepapi.cafe.model.payment;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String number;
    private String dateExpiration;
    private String cvv;

    public CreditCard(Builder builder) {
        this.number = builder.number;
        this.dateExpiration = builder.dateExpiration;
        this.cvv = builder.cvv;
    }

    @Getter
    @Setter
    public static class Builder {
        private String number;
        private String dateExpiration;
        private String cvv;

        public Builder() {
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder dateExpiration(String dateExpiration) {
            this.dateExpiration = dateExpiration;
            return this;
        }

        public Builder cvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public CreditCard build() {
            return new CreditCard(this);
        }
    }
}

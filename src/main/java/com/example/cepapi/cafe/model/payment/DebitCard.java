package com.example.cepapi.cafe.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@AllArgsConstructor
public class DebitCard {
    private String number;
    private String dateExpiration;
    private String cvv;

    public DebitCard(Builder builder) {
        this.number = builder.number;
        this.dateExpiration = builder.dateExpiration;
        this.cvv = builder.cvv;
    }
    @Getter @Setter
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

        public DebitCard build() {
            return new DebitCard(this);
        }
    }
}

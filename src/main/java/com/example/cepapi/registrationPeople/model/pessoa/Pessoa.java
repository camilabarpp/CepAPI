package com.example.cepapi.registrationPeople.model.pessoa;

import com.example.cepapi.cep.model.CepEntity;
import com.example.cepapi.cafe.model.payment.CreditCard;
import com.example.cepapi.cafe.model.payment.DebitCard;
import com.example.cepapi.cafe.model.payment.PayPal;
import com.example.cepapi.registrationPeople.model.weather.WeatherEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@ApiModel
public class Pessoa {

    @Id
    @ApiModelProperty(notes = "id of the user")
    private String id;
    @ApiModelProperty(notes = "nome of the user")
    private String nome;
    @ApiModelProperty(notes = "Birthdate of the user")
    private String dataDeNascimento;
    @ApiModelProperty(notes = "Address of the user")
    private CepEntity endereco;
    @ApiModelProperty(notes = "Weather of the city")
    private WeatherEntity temperatura;
    @ApiModelProperty(notes = "Debit card of the user")
    private DebitCard.Builder debitCard;
    private CreditCard.Builder creditCard;
    private PayPal.builder paypal;

    public CepEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(CepEntity endereco) {
        this.endereco = endereco;
    }

    public WeatherEntity getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(WeatherEntity temperatura) {
        this.temperatura = temperatura;
    }

    public DebitCard.Builder getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard.Builder debitCard) {
        this.debitCard = debitCard;
    }
}



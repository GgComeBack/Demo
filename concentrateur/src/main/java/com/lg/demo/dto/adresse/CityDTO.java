package com.lg.demo.dto.adresse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO implements Serializable {

    private String nomCommune;

    private String nomAncienneCommune;

    private String codePostal;

    private String codeInsee;

    private String codeInseeAncienneCommune;

    private String nomVoie;

    private String libelleAcheminement;

    private String numero;

    private String repere;

    private String x;

    private String y;

}

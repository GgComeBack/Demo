package com.lg.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lg.demo.dto.adresse.CityDTO;
import com.lg.demo.dto.siret.StockEtablissementDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EtablissementDTO {

    @JsonIgnore
    private Long totalElement;

    private StockEtablissementDTO etablissementSiret;

    private CityDTO adresseVerifier;
}

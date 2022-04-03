package demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockEtablissementLiensSuccessionDTO implements Serializable {

    private String siretEtablissementPredecesseur;

    private String siretEtablissementSuccesseur;

    private LocalDateTime dateLienSuccession;

    private Boolean transfertSiege;

    private Boolean continuiteEconomique;

    private LocalDateTime dateDernierTraitementLienSuccession;

    private StockEtablissementDTO stockEtablissementPredecesseur;

    private StockEtablissementDTO stockEtablissementSuccesseur;
}
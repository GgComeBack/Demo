package demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockUniteLegaleHistoriqueDTO implements Serializable {

    private String siren;

    private LocalDate dateFin;

    private LocalDate dateDebut;

    private String etatAdministratifUniteLegale;

    private Boolean changementEtatAdministratifUniteLegale;

    private String nomUniteLegale;

    private Boolean changementNomUniteLegale;

    private String nomUsageUniteLegale;

    private Boolean changementNomUsageUniteLegale;

    private String denominationUniteLegale;

    private Boolean changementdenominationUniteLegale;

    private String denominationUsuelle1UniteLegale;

    private String denominationUsuelle2UniteLegale;

    private String denominationUsuelle3UniteLegale;

    private Boolean changementDenominationUsuelleUniteLegale;

    private String categorieJuridiqueUniteLegale;

    private Boolean changementCategorieJuridiqueUniteLegale;

    private String activitePrincipaleUniteLegale;

    private String nomenclatureActivitePrincipaleUniteLegale;

    private Boolean changementActivitePrincipaleUniteLegale;

    private String nicSiegeUniteLegale;

    private Boolean changementNicSiegeUniteLegale;

    private String economieSocialeSolidaireUniteLegale;

    private Boolean changementEconomieSocialeSolidaireUniteLegale;

    private String caractereEmployeurUniteLegale;

    private Boolean changementCaractereEmployeurUniteLegale;
}
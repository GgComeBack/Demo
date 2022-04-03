package com.lg.demo.dto.siret;

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
public class StockEtablissementHistoriqueDTO implements Serializable {

    private String siren;

    private String nic;

    private String siret;

    private LocalDate dateFin;

    private LocalDate dateDebut;

    private String etatAdministratifEtablissement;

    private Boolean changementEtatAdministratifEtablissement;

    private String enseigne1Etablissement;

    private String enseigne2Etablissement;

    private String enseigne3Etablissement;

    private Boolean changementEnseigneEtablissement;

    private String denominationUsuelleEtablissement;

    private Boolean changementDenominationUsuelleEtablissement;

    private String activitePrincipaleEtablissement;

    private String nomenclatureActivitePrincipaleEtablissement;

    private Boolean changementActivitePrincipaleEtablissement;

    private String caractereEmployeurEtablissement;

    private Boolean changementCaractereEmployeurEtablissement;
}
package com.lg.demo.dto.siret;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockEtablissementDTO implements Serializable {

    @JsonIgnore
    private Long totalElement;

    private String siren;

    private String nic;

    private String siret;

    private String statutDiffusionEtablissement;

    private LocalDate dateCreationEtablissement;

    private String trancheEffectifsEtablissement;

    private Integer anneeEffectifsEtablissement;

    private String activitePrincipaleRegistreMetiersEtablissement;

    private LocalDate dateDernierTraitementEtablissement;

    private boolean etablissementSiege;

    private Integer nombrePeriodesEtablissement;

    private String complementAdresseEtablissement;

    private String numeroVoieEtablissement;

    private String indiceRepetitionEtablissement;

    private String typeVoieEtablissement;

    private String libelleVoieEtablissement;

    private String codePostalEtablissement;

    private String libelleCommuneEtablissement;

    private String libelleCommuneEtrangerEtablissement;

    private String distributionSpecialeEtablissement;

    private String codeCommuneEtablissement;

    private String codeCedexEtablissement;

    private String libelleCedexEtablissement;

    private String codePaysEtrangerEtablissement;

    private String libellePaysEtrangerEtablissement;

    private String complementAdresse2Etablissement;

    private String numeroVoie2Etablissement;

    private String indiceRepetition2Etablissement;

    private String typeVoie2Etablissement;

    private String libelleVoie2Etablissement;

    private String codePostal2Etablissement;

    private String libelleCommune2Etablissement;

    private String libelleCommune2EtrangerEtablissement;

    private String distributionSpeciale2Etablissement;

    private String codeCommune2Etablissement;

    private String codeCedex2Etablissement;

    private String libelleCedex2Etablissement;

    private String codePaysEtranger2Etablissement;

    private String libellePaysEtranger2Etablissement;

    private LocalDate dateDebut;

    private String etatAdministratifEtablissement;

    private String enseigne1Etablissement;

    private String enseigne2Etablissement;

    private String enseigne3Etablissement;

    private String denominationUsuelleEtablissement;

    private String activitePrincipaleEtablissement;

    private String nomenclatureActivitePrincipaleEtablissement;

    private String caractereEmployeurEtablissement;

    private List<StockEtablissementHistoriqueDTO> stockEtablissementHistoriqueDTOS;
}
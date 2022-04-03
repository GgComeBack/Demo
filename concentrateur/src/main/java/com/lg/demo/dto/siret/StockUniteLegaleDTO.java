package com.lg.demo.dto.siret;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockUniteLegaleDTO implements Serializable {

    private String siren;

    private String statutdiffusionUniteLegale;

    private String UnitepurgeeUniteLegale;

    private Date dateCreationUniteLegale;

    private String sigleUniteLegale;

    private String sexeUniteLegale;

    private String prenom1UniteLegale;

    private String prenom2UniteLegale;

    private String prenom3UniteLegale;

    private String prenom4UniteLegale;

    private String prenomUsuelUniteLegale;

    private String pseudonymeUniteLegale;

    private String identifiantAssociationUniteLegale;

    private String trancheEffectifsUniteLegale;

    private Integer anneeEffectifsUniteLegale;

    private LocalDate dateDernierTraitementUniteLegale;

    private Integer nombrePeriodesUniteLegale;

    private String categorieEntreprise;

    private Integer anneeCategorieEntreprise;

    private LocalDate dateDebut;

    private String etatAdministratifUniteLegale;

    private String nomUniteLegale;

    private String nomUsageUniteLegale;

    private String denominationUniteLegale;

    private String denominationUsuelle1UniteLegale;

    private String denominationUsuelle2UniteLegale;

    private String denominationUsuelle3UniteLegale;

    private String categorieJuridiqueUniteLegale;

    private String activitePrincipaleUniteLegale;

    private String nomenclatureActivitePrincipaleUniteLegale;

    private String nicSiegeUniteLegale;

    private String economieSocialeSolidaireUniteLegale;

    private String caractereEmployeurUniteLegale;

    private List<StockUniteLegaleHistoriqueDTO> stockUniteLegaleHistoriqueDTOS;
}
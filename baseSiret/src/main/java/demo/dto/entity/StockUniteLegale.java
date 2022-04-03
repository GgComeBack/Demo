package demo.dto.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Table("stockUniteLegale")
public class StockUniteLegale implements Serializable {

    @Column("siren")
    private String siren;
    @Column("statutdiffusionUniteLegale")
    private String statutdiffusionUniteLegale;
    @Column("UnitepurgeeUniteLegale")
    private String UnitepurgeeUniteLegale;
    @Column("datecreationUniteLegale")
    private Date dateCreationUniteLegale;
    @Column("sigleUniteLegale")
    private String sigleUniteLegale;
    @Column("sexeUniteLegale")
    private String sexeUniteLegale;
    @Column("prenom1UniteLegale")
    private String prenom1UniteLegale;
    @Column("prenom2UniteLegale")
    private String prenom2UniteLegale;
    @Column("prenom3UniteLegale")
    private String prenom3UniteLegale;
    @Column("prenom4UniteLegale")
    private String prenom4UniteLegale;
    @Column("prenomusuelUniteLegale")
    private String prenomUsuelUniteLegale;
    @Column("pseudonymeUniteLegale")
    private String pseudonymeUniteLegale;
    @Column("identifiantassociationUniteLegale")
    private String identifiantAssociationUniteLegale;
    @Column("trancheeffectifsUniteLegale")
    private String trancheEffectifsUniteLegale;
    @Column("anneeeffectifsUniteLegale")
    private Integer anneeEffectifsUniteLegale;
    @Column("datederniertraitementUniteLegale")
    private LocalDate dateDernierTraitementUniteLegale;
    @Column("nombreperiodesUniteLegale")
    private Integer nombrePeriodesUniteLegale;
    @Column("categorieentreprise")
    private String categorieEntreprise;
    @Column("anneecategorieentreprise")
    private Integer anneeCategorieEntreprise;
    @Column("datedebut")
    private LocalDate dateDebut;
    @Column("etatadministratifUniteLegale")
    private String etatAdministratifUniteLegale;
    @Column("nomUniteLegale")
    private String nomUniteLegale;
    @Column("nomusageUniteLegale")
    private String nomUsageUniteLegale;
    @Column("denominationUniteLegale")
    private String denominationUniteLegale;
    @Column("denominationusuelle1UniteLegale")
    private String denominationUsuelle1UniteLegale;
    @Column("denominationusuelle2UniteLegale")
    private String denominationUsuelle2UniteLegale;
    @Column("denominationusuelle3UniteLegale")
    private String denominationUsuelle3UniteLegale;
    @Column("categoriejuridiqueUniteLegale")
    private String categorieJuridiqueUniteLegale;
    @Column("activiteprincipaleUniteLegale")
    private String activitePrincipaleUniteLegale;
    @Column("nomenclatureactiviteprincipaleUniteLegale")
    private String nomenclatureActivitePrincipaleUniteLegale;
    @Column("nicsiegeUniteLegale")
    private String nicSiegeUniteLegale;
    @Column("economiesocialesolidaireUniteLegale")
    private String economieSocialeSolidaireUniteLegale;
    @Column("caractereemployeurUniteLegale")
    private String caractereEmployeurUniteLegale;
}
package demo.dto.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Table("stockUniteLegalehistorique")
public class StockUniteLegaleHistorique implements Serializable {

    @Column("siren")
    private String siren;
    @Column("datefin")
    private LocalDate dateFin;
    @Column("datedebut")
    private LocalDate dateDebut;
    @Column("etatadministratifUniteLegale")
    private String etatAdministratifUniteLegale;
    @Column("changementetatadministratifUniteLegale")
    private Boolean changementEtatAdministratifUniteLegale;
    @Column("nomUniteLegale")
    private String nomUniteLegale;
    @Column("changementnomUniteLegale")
    private Boolean changementNomUniteLegale;
    @Column("nomusageUniteLegale")
    private String nomUsageUniteLegale;
    @Column("changementnomusageUniteLegale")
    private Boolean changementNomUsageUniteLegale;
    @Column("denominationUniteLegale")
    private String denominationUniteLegale;
    @Column("changementdenominationUniteLegale")
    private Boolean changementdenominationUniteLegale;
    @Column("denominationUsuelle1UniteLegale")
    private String denominationUsuelle1UniteLegale;
    @Column("denominationUsuelle2UniteLegale")
    private String denominationUsuelle2UniteLegale;
    @Column("denominationUsuelle3UniteLegale")
    private String denominationUsuelle3UniteLegale;
    @Column("changementdenominationUsuelleUniteLegale")
    private Boolean changementDenominationUsuelleUniteLegale;
    @Column("categoriejuridiqueUniteLegale")
    private String categorieJuridiqueUniteLegale;
    @Column("changementcategoriejuridiqueUniteLegale")
    private Boolean changementCategorieJuridiqueUniteLegale;
    @Column("activiteprincipaleUniteLegale")
    private String activitePrincipaleUniteLegale;
    @Column("nomenclatureactiviteprincipaleUniteLegale")
    private String nomenclatureActivitePrincipaleUniteLegale;
    @Column("changementactiviteprincipaleUniteLegale")
    private Boolean changementActivitePrincipaleUniteLegale;
    @Column("nicsiegeUniteLegale")
    private String nicSiegeUniteLegale;
    @Column("changementnicsiegeUniteLegale")
    private Boolean changementNicSiegeUniteLegale;
    @Column("economiesocialesolidaireUniteLegale")
    private String economieSocialeSolidaireUniteLegale;
    @Column("changementeconomiesocialesolidaireUniteLegale")
    private Boolean changementEconomieSocialeSolidaireUniteLegale;
    @Column("caractereemployeurUniteLegale")
    private String caractereEmployeurUniteLegale;
    @Column("changementcaractereemployeurUniteLegale")
    private Boolean changementCaractereEmployeurUniteLegale;
}
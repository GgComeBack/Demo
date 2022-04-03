package demo.dto.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Table("stockEtablissementhistorique")
public class StockEtablissementHistorique implements Serializable {

    @Id
    @Column("siren")
    private String siren;
    @Column("nic")
    private String nic;
    @Column("siret")
    private String siret;
    @Column("datefin")
    private LocalDate dateFin;
    @Column("datedebut")
    private LocalDate dateDebut;
    @Column("etatadministratifEtablissement")
    private String etatAdministratifEtablissement;
    @Column("changementetatadministratifEtablissement")
    private Boolean changementEtatAdministratifEtablissement;
    @Column("enseigne1Etablissement")
    private String enseigne1Etablissement;
    @Column("enseigne2Etablissement")
    private String enseigne2Etablissement;
    @Column("enseigne3Etablissement")
    private String enseigne3Etablissement;
    @Column("changementenseigneEtablissement")
    private Boolean changementEnseigneEtablissement;
    @Column("denominationusuelleEtablissement")
    private String denominationUsuelleEtablissement;
    @Column("changementdenominationusuelleEtablissement")
    private Boolean changementDenominationUsuelleEtablissement;
    @Column("activiteprincipaleEtablissement")
    private String activitePrincipaleEtablissement;
    @Column("nomenclatureactiviteprincipaleEtablissement")
    private String nomenclatureActivitePrincipaleEtablissement;
    @Column("changementactiviteprincipaleEtablissement")
    private Boolean changementActivitePrincipaleEtablissement;
    @Column("caractereemployeurEtablissement")
    private String caractereEmployeurEtablissement;
    @Column("changementcaractereemployeurEtablissement")
    private Boolean changementCaractereEmployeurEtablissement;
}
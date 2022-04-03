package demo.dto.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Table("stocketablissement")
public class StockEtablissement implements Serializable {

    @Id
    @Column("siren")
    private String siren;
    @Column("nic")
    private String nic;
    @Column("siret")
    private String siret;
    @Column("statutdiffusionetablissement")
    private String statutDiffusionEtablissement;
    @Column("datecreationetablissement")
    private LocalDate dateCreationEtablissement;
    @Column("trancheeffectifsetablissement")
    private String trancheEffectifsEtablissement;
    @Column("anneeeffectifsetablissement")
    private Integer anneeEffectifsEtablissement;
    @Column("activiteprincipaleregistremetiersetablissement")
    private String activitePrincipaleRegistreMetiersEtablissement;
    @Column("datederniertraitementetablissement")
    private LocalDate dateDernierTraitementEtablissement;
    @Column("etablissementsiege")
    private boolean etablissementSiege;
    @Column("nombreperiodesetablissement")
    private Integer nombrePeriodesEtablissement;
    @Column("complementadresseetablissement")
    private String complementAdresseEtablissement;
    @Column("numerovoieetablissement")
    private String numeroVoieEtablissement;
    @Column("indicerepetitionetablissement")
    private String indiceRepetitionEtablissement;
    @Column("typevoieetablissement")
    private String typeVoieEtablissement;
    @Column("libellevoieetablissement")
    private String libelleVoieEtablissement;
    @Column("codepostaletablissement")
    private String codePostalEtablissement;
    @Column("libellecommuneetablissement")
    private String libelleCommuneEtablissement;
    @Column("libellecommuneetrangeretablissement")
    private String libelleCommuneEtrangerEtablissement;
    @Column("distributionspecialeetablissement")
    private String distributionSpecialeEtablissement;
    @Column("codecommuneetablissement")
    private String codeCommuneEtablissement;
    @Column("codecedexetablissement")
    private String codeCedexEtablissement;
    @Column("libellecedexetablissement")
    private String libelleCedexEtablissement;
    @Column("codepaysetrangeretablissement")
    private String codePaysEtrangerEtablissement;
    @Column("libellepaysetrangeretablissement")
    private String libellePaysEtrangerEtablissement;
    @Column("complementadresse2etablissement")
    private String complementAdresse2Etablissement;
    @Column("numerovoie2etablissement")
    private String numeroVoie2Etablissement;
    @Column("indicerepetition2etablissement")
    private String indiceRepetition2Etablissement;
    @Column("typevoie2etablissement")
    private String typeVoie2Etablissement;
    @Column("libellevoie2etablissement")
    private String libelleVoie2Etablissement;
    @Column("codepostal2etablissement")
    private String codePostal2Etablissement;
    @Column("libellecommune2etablissement")
    private String libelleCommune2Etablissement;
    @Column("libellecommune2etrangeretablissement")
    private String libelleCommune2EtrangerEtablissement;
    @Column("distributionspeciale2etablissement")
    private String distributionSpeciale2Etablissement;
    @Column("codecommune2etablissement")
    private String codeCommune2Etablissement;
    @Column("codecedex2etablissement")
    private String codeCedex2Etablissement;
    @Column("libellecedex2etablissement")
    private String libelleCedex2Etablissement;
    @Column("codepaysetranger2etablissement")
    private String codePaysEtranger2Etablissement;
    @Column("libellepaysetranger2etablissement")
    private String libellePaysEtranger2Etablissement;
    @Column("datedebut")
    private LocalDate dateDebut;
    @Column("etatadministratifetablissement")
    private String etatAdministratifEtablissement;
    @Column("enseigne1etablissement")
    private String enseigne1Etablissement;
    @Column("enseigne2etablissement")
    private String enseigne2Etablissement;
    @Column("enseigne3etablissement")
    private String enseigne3Etablissement;
    @Column("denominationusuelleetablissement")
    private String denominationUsuelleEtablissement;
    @Column("activiteprincipaleetablissement")
    private String activitePrincipaleEtablissement;
    @Column("nomenclatureactiviteprincipaleetablissement")
    private String nomenclatureActivitePrincipaleEtablissement;
    @Column("caractereemployeuretablissement")
    private String caractereEmployeurEtablissement;
}
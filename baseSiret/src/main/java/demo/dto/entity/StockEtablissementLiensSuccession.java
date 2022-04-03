package demo.dto.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table("stocketablissementlienssuccession")
public class StockEtablissementLiensSuccession implements Serializable {

    @Column("siretetablissementpredecesseur")
    private String siretEtablissementPredecesseur;

    @Column("siretetablissementsuccesseur")
    private String siretEtablissementSuccesseur;

    @Column("dateliensuccession")
    private LocalDateTime dateLienSuccession;
    @Column("transfertsiege")
    private Boolean transfertSiege;
    @Column("continuiteeconomique")
    private Boolean continuiteEconomique;

    @Column("datederniertraitementliensuccession")
    private LocalDateTime dateDernierTraitementLienSuccession;
}
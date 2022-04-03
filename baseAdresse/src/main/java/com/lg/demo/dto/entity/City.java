package com.lg.demo.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "city")
public class City {

    @Id
    @Column("id")
    private String id;

    @Column("nom_commune")
    private String nomCommune;

    @Column("nom_ancienne_commune")
    private String nomAncienneCommune;

    @Column("code_postal")
    private String codePostal;

    @Column("code_insee")
    private String codeInsee;

    @Column("code_insee_ancienne_commune")
    private String codeInseeAncienneCommune;

    @Column("nom_voie")
    private String nomVoie;

    @Column("libelle_acheminement")
    private String libelleAcheminement;

    @Column("numero")
    private String numero;

    @Column("rep")
    private String repere;

    @Column("x")
    private String x;

    @Column("y")
    private String y;

}

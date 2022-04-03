drop table if exists city;

CREATE EXTENSION IF NOT EXISTS "unaccent";
CREATE EXTENSION IF NOT EXISTS btree_gist;

create table city (
id varchar(50),
id_fantoir varchar(50),
numero varchar(10),
rep varchar(10),
nom_voie varchar(500),
code_postal varchar(100),
code_insee varchar(100),
nom_commune varchar(250),
code_insee_ancienne_commune varchar(100),
nom_ancienne_commune varchar(250),
x varchar(25),
y varchar(25),
lon varchar(25),
lat varchar(25),
type_position varchar(50),
alias varchar(50),
nom_ld varchar(250),
libelle_acheminement varchar(250),
nom_afnor varchar(250),
source_position varchar(50),
source_nom_voie varchar(50),
certification_commune varchar(100),
cad_parcelles text
);
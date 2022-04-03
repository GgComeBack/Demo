copy city from program 'zcat /data/adresses-france.csv.gz' DELIMITER ';' CSV;

alter table city add column nom_voie_upper_case_unaccent varchar(500);
alter table city add column nom_commune_upper_case_unaccent varchar(250);
alter table city add column nom_ancienne_commune_upper_case_unaccent varchar(250);

update city set nom_voie_upper_case_unaccent = upper(unaccent(nom_voie)), nom_commune_upper_case_unaccent = upper(unaccent(nom_commune)),
nom_ancienne_commune_upper_case_unaccent = upper(unaccent(nom_ancienne_commune));

CREATE INDEX IF NOT EXISTS city_nom_voie_upper_case_unaccent_idx ON public.city (nom_voie_upper_case_unaccent);
CREATE INDEX IF NOT EXISTS city_nom_commune_upper_case_unaccent_idx ON public.city (nom_commune_upper_case_unaccent);
CREATE INDEX IF NOT EXISTS city_nom_ancienne_commune_upper_case_unaccent_idx ON public.city (nom_ancienne_commune_upper_case_unaccent);

CREATE INDEX IF NOT EXISTS city_code_postal_idx ON public.city (code_postal);
CREATE INDEX IF NOT EXISTS city_nom_commune_idx ON public.city (nom_commune);
CREATE INDEX IF NOT EXISTS city_nom_voie_idx ON public.city (nom_voie);
CREATE INDEX IF NOT EXISTS city_nom_afnor_upper_idx ON public.city (upper(nom_afnor));



CREATE INDEX IF NOT EXISTS city_nom_voie_upper_case_unaccent_trgm_idx ON public.city USING GIST (nom_voie_upper_case_unaccent);
CREATE INDEX IF NOT EXISTS city_nom_commune_upper_case_unaccent_trgm_idx ON public.city USING GIST (nom_commune_upper_case_unaccent);
CREATE INDEX IF NOT EXISTS city_nom_ancienne_commune_upper_case_unaccent_trgm_idx ON public.city USING GIST (nom_ancienne_commune_upper_case_unaccent);
CREATE INDEX IF NOT EXISTS city_nom_afnor_trgm_idx ON public.city USING GIST (nom_afnor);

CREATE FUNCTION OR REPLACE upperCaseUnaccentCity() RETURNS trigger AS $upperCaseUnaccentCity$
    BEGIN
        -- nous mettons à jour les champs pour les recherches sans accents
        NEW.nom_voie_upper_case_unaccent := upper(unaccent(nom_voie));
        NEW.nom_commune_upper_case_unaccent := upper(unaccent(nom_commune));
        NEW.nom_ancienne_commune_upper_case_unaccent := upper(unaccent(nom_ancienne_commune));
        RETURN NEW;
    END;
$upperCaseUnaccentCity$ LANGUAGE plpgsql;

CREATE TRIGGER city_uppercase_unaccent BEFORE INSERT OR UPDATE ON city
    FOR EACH ROW EXECUTE FUNCTION upperCaseUnaccentCity();



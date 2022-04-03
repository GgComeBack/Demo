copy stockUniteLegaleHistorique from program 'unzip -p /data/StockUniteLegaleHistorique_utf8.zip' HEADER DELIMITER ',' CSV  ;
copy stockEtablissement from program 'unzip -p /data/StockEtablissement_utf8.zip' DELIMITER ',' CSV HEADER ;
copy stockEtablissementHistorique from program 'unzip -p /data/StockEtablissementHistorique_utf8.zip' HEADER DELIMITER ',' CSV ;
copy stockUniteLegale from program 'unzip -p /data/StockUniteLegale_utf8.zip' DELIMITER ',' CSV HEADER;
copy stockEtablissementLiensSuccession from program 'unzip -p /data/StockEtablissementLiensSuccession_utf8.zip' DELIMITER ',' CSV HEADER ;


CREATE EXTENSION IF NOT EXISTS btree_gin;

CREATE INDEX if not exists stocketablissement_siret_gin_idx ON stocketablissement USING gin (siret);
CREATE INDEX if not exists stocketablissement_siren_idx ON stocketablissement USING btree (siren);
CREATE INDEX if not exists stocketablissement_nic ON stocketablissement USING btree (nic);
CREATE INDEX if not exists stocketablissement_nic ON stocketablissement USING btree (nic);
CREATE INDEX if not exists stocketablissement_siret ON stocketablissement USING btree (siret);
CREATE INDEX if not exists stocketablissement_historique_siret ON stocketablissementhistorique USING btree (siret);
CREATE INDEX if not exists stocketablissementlienssuccession_siretetablissementsuccesseur_ ON stocketablissementlienssuccession USING btree (siretetablissementsuccesseur);
CREATE INDEX if not exists stocketablissementlienssuccessione_siretetablissementpredecesse ON stocketablissementlienssuccession USING btree (siretetablissementpredecesseur);
CREATE INDEX if not exists stockunitelegale_siren_idx ON stockunitelegale USING btree (siren);
CREATE INDEX if not exists stockunitelegalehistoriques_siren_idx ON stockunitelegalehistorique USING btree (siren);

<h1>Demo reactor R2DBC</h1>

Les fichiers sources des données proviennent de source open source :

Pour les données des adresses :

- https://adresse.data.gouv.fr/data/ban/adresses/latest/csv/adresses-france.csv.gz

Pour les données des etablissements, ellse sont disponibles via l'url https://www.data.gouv.fr/fr/datasets/base-sirene-des-entreprises-et-de-leurs-etablissements-siren-siret

Voici les 5 fichiers par exemple pour le mois avril :

- https://www.data.gouv.fr/fr/datasets/r/88fbb6b4-0320-443e-b739-b4376a012c32
- https://www.data.gouv.fr/fr/datasets/r/0651fb76-bcf3-4f6a-a38d-bc04fa708576
- https://www.data.gouv.fr/fr/datasets/r/0835cd60-2c2a-497b-bc64-404de704ce89
- https://www.data.gouv.fr/fr/datasets/r/9c4d5d9c-4bbb-4b9c-837a-6155cb589e26
- https://www.data.gouv.fr/fr/datasets/r/825f4199-cadd-486c-ac46-a65a8ea1a047

Pour lancer le projet :
- Il faut creer deux Volumes :
  - `docker volume create baseAdresse`
  - `docker volume create baseSiret`
- Il faut creer le reseau : <br>
`docker network create -d bridge reactor`

L'ensemble est disponible au travers du docker-compose à la racine.
pour lancer le projet: `docker-compose .\docker-compose.yml up`

Pour visualiser les resultats sous Grafana l'interface est disponible sur le port 3000
Trois tableaux de bord sont disponibles pour visualiser les résultats du tire de perf lancer en automatique.




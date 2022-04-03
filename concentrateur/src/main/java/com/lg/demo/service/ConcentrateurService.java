package com.lg.demo.service;

import com.lg.demo.dto.EtablissementDTO;
import com.lg.demo.dto.siret.StockEtablissementDTO;
import com.lg.demo.repository.AdresseRepository;
import com.lg.demo.repository.SiretRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConcentrateurService {

    private final AdresseRepository adresseRepository;

    private final SiretRepository siretRepository;

    public Flux<EtablissementDTO> searchEtablissementFlux(String siret, Pageable pageable) {
        return siretRepository.searchSiretFlux(siret, pageable)
                .flatMap(stockEtablissementDTO -> Mono.empty().then(getAdresseVerifierFlux(stockEtablissementDTO)));
    }

    public Flux<EtablissementDTO> searchEtablissement(String siret, Pageable pageable) {
        return siretRepository.searchSiret(siret, pageable)
                .flatMap(stockEtablissementDTO -> Mono.empty().then(getAdresseVerifier(stockEtablissementDTO)));
    }

    public Flux<EtablissementDTO> searchEtablissementV2(String siret, Pageable pageable) {
        return siretRepository.searchSiret(siret, pageable)
                .flatMap(stockEtablissementDTO -> Mono.zip(Mono.just(new Object()), getAdresseVerifier(stockEtablissementDTO)))
                .flatMap(tuple2 -> Mono.just(tuple2.getT2()));
    }

    private Mono<EtablissementDTO> getAdresseVerifier(StockEtablissementDTO stockEtablissementDTO) {
        return adresseRepository
                .searchAdresse(stockEtablissementDTO.getLibelleCommuneEtablissement(),
                        stockEtablissementDTO.getCodePostalEtablissement(), stockEtablissementDTO.getLibelleVoieEtablissement())
                .filter(cityDTO -> StringUtils.equalsIgnoreCase(cityDTO.getNumero(), stockEtablissementDTO.getNumeroVoieEtablissement()))
                .map(cityDTO -> EtablissementDTO.builder().totalElement(stockEtablissementDTO.getTotalElement())
                        .etablissementSiret(stockEtablissementDTO).adresseVerifier(cityDTO).build())
                //.switchIfEmpty(Flux.defer(() -> Flux.just(stockEtablissementDTO)))
                .elementAt(0, EtablissementDTO.builder().etablissementSiret(stockEtablissementDTO).build());
    }

    private Mono<EtablissementDTO>  getAdresseVerifierFlux(StockEtablissementDTO stockEtablissementDTO) {
       return adresseRepository
                .searchAdresseFlux(stockEtablissementDTO.getLibelleCommuneEtablissement(),
                        stockEtablissementDTO.getCodePostalEtablissement(), stockEtablissementDTO.getLibelleVoieEtablissement())
                .filter(cityDTO -> StringUtils.equalsIgnoreCase(cityDTO.getNumero(), stockEtablissementDTO.getNumeroVoieEtablissement()))
                .map(cityDTO -> EtablissementDTO.builder().etablissementSiret(stockEtablissementDTO).adresseVerifier(cityDTO).build())
                .elementAt(0, EtablissementDTO.builder().etablissementSiret(stockEtablissementDTO).build());
    }


}

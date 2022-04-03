package com.lg.demo.repository;

import com.lg.demo.dto.adresse.CityDTO;
import io.netty.handler.codec.http.HttpHeaderNames;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@Repository
public class AdresseRepository {

    @Value("${api.partenaire.url.baseAdresse}")
    private String urlBase;

    private final WebClient webClient = WebClient.builder().build();

    public Flux<CityDTO> searchAdresse(String ville, String codePostal, String voie) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("adresse")
                        //.queryParam("name", ville)
                        .queryParam("postalCode", codePostal)
                        .queryParam("nomVoie", "%" + RegExUtils.replaceAll(voie, "^ST ", "SAINT ") + "%")
                        .queryParam("size", 1000).build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(CityDTO.class));
    }

    public Flux<CityDTO> searchAdresseFlux(String ville, String codePostal, String voie) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("adresse/test")
                        //.queryParam("name", ville)
                        .queryParam("postalCode", codePostal)
                        .queryParam("nomVoie", "%" + RegExUtils.replaceAll(voie, "^ST ", "SAINT ") + "%")
                        .queryParam("size", 1000).build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(CityDTO.class));
    }
}

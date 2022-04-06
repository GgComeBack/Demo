package com.lg.demo.repository;

import com.lg.demo.dto.adresse.CityDTO;
import io.netty.handler.codec.http.HttpHeaderNames;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.util.Logger;
import reactor.util.Loggers;
import reactor.util.retry.Retry;

import java.time.Duration;

@Repository
public class AdresseRepository implements HttpInitRepository, RetryRepository {

    private static final Logger logger = Loggers.getLogger(AdresseRepository.class);

    private static final long RETRY_MAX = 3;

    private static final long RETRY_MIN_DURATION = 300;

    @Value("${api.partenaire.url.baseAdresse}")
    private String urlBase;

    public static final String NAME_POOL = "poolAdresse";
    public static final int MAX_CONNEXTION = 100000;
    public static final int PENDING_ACQUIRE_MAX_COUNT = MAX_CONNEXTION * 5;

    private final  WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(
                    logHttpAndMaxPoolsite(NAME_POOL, MAX_CONNEXTION, PENDING_ACQUIRE_MAX_COUNT))
            ).build();


    public Flux<CityDTO> searchAdresse(String ville, String codePostal, String voie) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("adresses")
                        //.queryParam("name", ville)
                        .queryParam("postalCode", codePostal)
                        .queryParam("nomVoie", "%" + RegExUtils.replaceAll(voie, "^ST ", "SAINT ") + "%")
                        .queryParam("size", 1000).build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(CityDTO.class))
                .retryWhen(manageRetry())
                .doOnError(throwable -> logger.error(String.format("Erreur appel de l'api %s", serviceName()), throwable));
    }

    public Flux<CityDTO> searchAdresseFlux(String ville, String codePostal, String voie) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("adresses/test")
                        //.queryParam("name", ville)
                        .queryParam("postalCode", codePostal)
                        .queryParam("nomVoie", "%" + RegExUtils.replaceAll(voie, "^ST ", "SAINT ") + "%")
                        .queryParam("size", 1000).build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(CityDTO.class))
                .retryWhen(manageRetry())
                .doOnError(throwable -> logger.error(String.format("Erreur appel de l'api %s", serviceName()), throwable));
    }

    @Override
    public long getRetryMax() {
        return RETRY_MAX;
    }

    @Override
    public long getMinDuration() {
        return RETRY_MIN_DURATION;
    }

    @Override
    public String serviceName() {
        return "adresse";
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}

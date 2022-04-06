package com.lg.demo.repository;

import com.lg.demo.dto.siret.StockEtablissementDTO;
import io.netty.handler.codec.http.HttpHeaderNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

@Repository
public class SiretRepository implements HttpInitRepository, RetryRepository {

    private static final Logger logger = Loggers.getLogger(SiretRepository.class);

    public static final String NAME_POOL = "poolSiret";
    public static final int MAX_CONNEXTION = 100000;
    public static final int PENDING_ACQUIRE_MAX_COUNT = MAX_CONNEXTION * 5;

    private static final long RETRY_MAX = 3;

    private static final long RETRY_MIN_DURATION = 300;

    @Value("${api.partenaire.url.baseSiret}")
    private String urlBase;

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(logHttpAndMaxPoolsite(NAME_POOL, MAX_CONNEXTION, PENDING_ACQUIRE_MAX_COUNT)))
            .build();

    public Flux<StockEtablissementDTO> searchSiret(String siret, Pageable pageable) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("etablissements")
                        .queryParam("siret", siret)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> {
                    Long totalElement = Long.valueOf(clientResponse.headers().header("x-total-element").get(0));
                    return clientResponse.bodyToFlux(StockEtablissementDTO.class).flatMap(stockEtablissementDTO -> {
                        stockEtablissementDTO.setTotalElement(totalElement);
                        return Mono.just(stockEtablissementDTO);
                    });
                });
    }


    public Flux<StockEtablissementDTO> searchSiretFlux(String siret, Pageable pageable) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("etablissements/test")
                        .queryParam("siret", siret)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(StockEtablissementDTO.class));
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
        return "etablissements";
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}

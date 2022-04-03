package com.lg.demo.repository;

import com.lg.demo.dto.siret.StockEtablissementDTO;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.logging.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import reactor.util.function.Tuple2;

@Repository
public class SiretRepository {

    @Value("${api.partenaire.url.baseSiret}")
    private String urlBase;

    private final WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(logHttp())).build();

    private HttpClient logHttp() {
        return HttpClient
                .create()
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
    }


    public Flux<StockEtablissementDTO> searchSiret(String siret, Pageable pageable) {
        return webClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("etablissement")
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
                .uri(UriComponentsBuilder.fromHttpUrl(urlBase).path("etablissement")
                        .queryParam("siret", siret)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .build().toUri()
                ).header(HttpHeaderNames.CONTENT_TYPE.toString(), "application/stream+json")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(StockEtablissementDTO.class));
    }
}

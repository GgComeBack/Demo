package com.lg.demo.endpoint;

import com.lg.demo.dto.CityDTO;
import com.lg.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "adresses")
@RequiredArgsConstructor
public class CityControler {

    private final CityService cityService;

    @GetMapping("test")
    public Flux<CityDTO> searchCityFluxOnly(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String inseeCode,
            @RequestParam(required = false) String nomVoie,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return cityService.findCities2(name, postalCode, nomVoie, inseeCode, pageable);
    }

    @GetMapping(produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<Flux<CityDTO>>> searchCity(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) String inseeCode,
            @RequestParam(required = false) String nomVoie,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return Mono.zip(cityService.countCities2(name, postalCode, nomVoie, inseeCode),
                        Mono.just(cityService.findCities2(name, postalCode, nomVoie, inseeCode, pageable)))
                .filter(tuple -> tuple.getT1() != null && 0L < tuple.getT1())
                .flatMap(tuple -> {
                            if (tuple.getT1() <= Long.valueOf(pageable.getPageSize())) {
                                return Mono.just(ResponseEntity.ok()
                                        .header("x-total-element", String.valueOf(tuple.getT1()))
                                        .header("x-current-page", String.valueOf(pageable.getPageNumber()))
                                        .header("x-current-size", String.valueOf(pageable.getPageSize()))
                                        .body(tuple.getT2()));
                            }
                            return Mono.just(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                                    .header("x-total-element", String.valueOf(tuple.getT1()))
                                    .header("x-current-page", String.valueOf(pageable.getPageNumber()))
                                    .header("x-current-size", String.valueOf(pageable.getPageSize()))
                                    .body(tuple.getT2()));
                        }
                )
                .switchIfEmpty(Mono.just(ResponseEntity.noContent()
                        .header("x-total-element", "0")
                        .header("x-current-page", String.valueOf(pageable.getPageNumber()))
                        .header("x-current-size", String.valueOf(pageable.getPageSize())).build()));
    }
}

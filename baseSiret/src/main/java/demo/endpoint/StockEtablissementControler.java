package demo.endpoint;

import demo.dto.StockEtablissementDTO;
import demo.service.StockEtablissementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RestController
@RequestMapping(value = "etablissement")
@RequiredArgsConstructor
public class StockEtablissementControler {

    private final StockEtablissementService stockEtablissementService;

    @GetMapping("test")
    public Flux<StockEtablissementDTO> searchEtablissementFluxOnly(
            @RequestParam(required = false, name = "siret") String siret,
            @RequestParam(required = false, name = "postal-code") String postalCode,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return stockEtablissementService.findEtablissement(siret, postalCode, pageable);
    }

    @GetMapping()
    public Mono<ResponseEntity<Flux<StockEtablissementDTO>>> searchEtablissement(
            @RequestParam(required = false, name = "siret") String siret,
            @RequestParam(required = false, name = "postal-code") String postalCode,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return Mono.zip(stockEtablissementService.countEtablissement(siret, postalCode),
                Mono.just(stockEtablissementService.findEtablissement(siret, postalCode, pageable)))
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

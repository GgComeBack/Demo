package com.lg.demo.endpoint;

import com.lg.demo.dto.EtablissementDTO;
import com.lg.demo.service.ConcentrateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "concentrateur")
@RequiredArgsConstructor
public class ConcentrateurControler {

    private final ConcentrateurService concentrateurService;

    @GetMapping("test")
    public Flux<EtablissementDTO> searchEtablissementFlux(
            @RequestParam(required = false) String siret,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return concentrateurService.searchEtablissementFlux(siret, pageable);
    }

    @GetMapping
    public Mono<ResponseEntity<List<EtablissementDTO>>> searchEtablissement(
            @RequestParam(required = false) String siret,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return concentrateurService.searchEtablissement(siret, pageable)
                .filter(etablissementDTO -> etablissementDTO.getTotalElement() != null && 0L < etablissementDTO.getTotalElement())
                .collectList()
                .flatMap(etablissementDTOList -> {
                            if (!CollectionUtils.isEmpty(etablissementDTOList)) {
                                if (etablissementDTOList.get(0).getTotalElement() <= Long.valueOf(pageable.getPageSize())) {
                                    return Mono.just(ResponseEntity.ok()
                                            .header("x-total-element", String.valueOf(etablissementDTOList.get(0).getTotalElement()))
                                            .header("x-current-page", String.valueOf(pageable.getPageNumber()))
                                            .header("x-current-size", String.valueOf(pageable.getPageSize()))
                                            .body(etablissementDTOList));
                                }
                                return Mono.just(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                                        .header("x-total-element", String.valueOf(etablissementDTOList.get(0).getTotalElement()))
                                        .header("x-current-page", String.valueOf(pageable.getPageNumber()))
                                        .header("x-current-size", String.valueOf(pageable.getPageSize()))
                                        .body(etablissementDTOList));
                            }
                            return Mono.empty();
                        }
                ).switchIfEmpty(Mono.just(ResponseEntity.noContent()
                        .header("x-total-element", "0")
                        .header("x-current-page", String.valueOf(pageable.getPageNumber()))
                        .header("x-current-size", String.valueOf(pageable.getPageSize())).build()));

    }
}

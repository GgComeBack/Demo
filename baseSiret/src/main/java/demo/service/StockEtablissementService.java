package demo.service;

import demo.dto.StockEtablissementDTO;
import demo.dto.entity.StockEtablissement;
import demo.repository.StockEtablissementMapper;
import demo.repository.StockEtablissementRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockEtablissementService {

    private final StockEtablissementRepository etablissementRepository;

    private final StockEtablissementMapper stockEtablissementMapper;

    private final R2dbcEntityTemplate template;

    public Flux<StockEtablissementDTO> findEtablissement(String siret, String postalCode, Pageable pageable) {
        return template.select(StockEtablissement.class).matching(
                Query.query(createCriteria(siret, postalCode))
                        .offset(pageable.getPageNumber() * pageable.getPageSize())
                        .limit((pageable.getPageNumber() +1) * pageable.getPageSize())
                        .sort(pageable.getSort())
        ).all().flatMap(cityInfo -> Mono.just(stockEtablissementMapper.fromEntity(cityInfo)));
    }

    public Mono<Long> countEtablissement(String siret, String postalCode) {
        return template.select(StockEtablissement.class).matching(
                Query.query(createCriteria(siret, postalCode))).count();
    }

    CriteriaDefinition createCriteria(String siret, String postalCode) {
        CriteriaDefinition criteriaDefinition = null;
        if(StringUtils.isNotBlank(siret)) {
             criteriaDefinition = getCriteriaDefinitionLike(StringUtils.substring(siret,0, 9), criteriaDefinition, "siren", CriteriaDefinition.Combinator.INITIAL, false);
        }
        criteriaDefinition = getCriteriaDefinitionLike(siret, criteriaDefinition, "siret", CriteriaDefinition.Combinator.AND, false);
        CriteriaDefinition criteriaDefinitionCombinee = getCriteriaDefinitionLike(postalCode, null, "codepostaletablissement", CriteriaDefinition.Combinator.AND, true);
        criteriaDefinitionCombinee = getCriteriaDefinitionLike(postalCode, criteriaDefinitionCombinee, "codepostal2etablissement", CriteriaDefinition.Combinator.OR, true);
        if(criteriaDefinition != null) {
            if(criteriaDefinitionCombinee != null) {
                return ((Criteria) criteriaDefinition).and(criteriaDefinitionCombinee);
            }
            return criteriaDefinition;
        }
        return criteriaDefinitionCombinee;
    }

    private CriteriaDefinition getCriteriaDefinitionLike(String valeur, CriteriaDefinition criteriaDefinition, String nomColunm, CriteriaDefinition.Combinator combinator, boolean ignoreCase) {
        if (StringUtils.isNotBlank(valeur)) {
            if (criteriaDefinition == null || CriteriaDefinition.Combinator.INITIAL.equals(combinator)) {
                criteriaDefinition = Criteria.where(nomColunm).like(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase);
            } else if (CriteriaDefinition.Combinator.AND.equals(combinator)) {
                criteriaDefinition = ((Criteria) criteriaDefinition).and(Criteria.where(nomColunm).like(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase));
            } else if (CriteriaDefinition.Combinator.OR.equals(combinator)) {
                criteriaDefinition = ((Criteria) criteriaDefinition).or(Criteria.where(nomColunm).like(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase));
            }
        }
        return criteriaDefinition;
    }

    private CriteriaDefinition getCriteriaDefinitionEqual(String valeur, CriteriaDefinition criteriaDefinition, String nomColunm, CriteriaDefinition.Combinator combinator, boolean ignoreCase) {
        if (StringUtils.isNotBlank(valeur)) {
            if (criteriaDefinition == null || CriteriaDefinition.Combinator.INITIAL.equals(combinator)) {
                criteriaDefinition = Criteria.where(nomColunm).is(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase);
            } else if (CriteriaDefinition.Combinator.AND.equals(combinator)) {
                criteriaDefinition = ((Criteria) criteriaDefinition).and(Criteria.where(nomColunm).is(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase));
            } else if (CriteriaDefinition.Combinator.OR.equals(combinator)) {
                criteriaDefinition = ((Criteria) criteriaDefinition).or(Criteria.where(nomColunm).is(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase));
            }
        }
        return criteriaDefinition;
    }

}

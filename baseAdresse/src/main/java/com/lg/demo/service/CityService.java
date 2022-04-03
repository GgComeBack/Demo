package com.lg.demo.service;

import com.lg.demo.dto.CityDTO;
import com.lg.demo.dto.entity.City;
import com.lg.demo.repository.CityMapper;
import com.lg.demo.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    private final R2dbcEntityTemplate template;

    /*public Flux<CityDTO> findCities(String name, String postalCode, String inseeCode, Pageable pageable) {
        City city = new City();
        city.setName(name);
        city.setCodePostal(postalCode);
        city.setCodeInsee(inseeCode);
        return cityRepository
                .findAll(Example.of(city, ExampleMatcher.matching().withIgnoreNullValues()))
                .flatMap(cityInfo -> Mono.just(cityMapper.fromEntity((City) cityInfo)));
    }*/


    /*public Flux<CityDTO> findAllCities(String name, String postalCode, String inseeCode, Pageable pageable) {
        City city = new City();
        city.setName(name);
        city.setCodePostal(postalCode);
        city.setCodeInsee(inseeCode);
        return cityRepository
                .findBy(Example.of(city, ExampleMatcher.matching().withIgnoreNullValues()),
                        (FluentQuery.ReactiveFluentQuery<City> cityReactiveFluentQuery) -> cityReactiveFluentQuery.page(pageable))
                .flatMapMany(Flux::fromIterable)
                .flatMap(cityInfo -> Mono.just(cityMapper.fromEntity(cityInfo)));
    }*/

    public Flux<CityDTO> findCities2(String name, String postalCode, String nomVoie, String inseeCode, Pageable pageable) {
        return template.select(City.class).matching(
                Query.query(createCriteria(name, postalCode, nomVoie, inseeCode))
                        .limit((pageable.getPageNumber() +1) *pageable.getPageSize())
                        .offset(pageable.getPageNumber() * pageable.getPageSize())
                        .sort(pageable.getSort())
        ).all().flatMap(cityInfo -> Mono.just(cityMapper.fromEntity(cityInfo)));
    }

    public Mono<Long> countCities2(String name, String postalCode, String nomVoie, String inseeCode) {
        City city = new City();
        city.setNomCommune(name);
        city.setCodePostal(postalCode);
        city.setCodeInsee(inseeCode);
        return template.select(City.class).matching(
                Query.query(createCriteria(name, postalCode, nomVoie, inseeCode))).count();
    }

    CriteriaDefinition createCriteria(String name, String postalCode, String nomVoie, String inseeCode) {
        CriteriaDefinition criteriaDefinition = getCriteriaDefinitionLikeIgnoreCase(StringUtils.stripAccents(name), null, "nom_commune_upper_case_unaccent", CriteriaDefinition.Combinator.AND, false);
        criteriaDefinition = getCriteriaDefinitionLikeIgnoreCase(inseeCode, criteriaDefinition, "code_insee", CriteriaDefinition.Combinator.AND, true);
        criteriaDefinition = getCriteriaDefinitionLikeIgnoreCase(postalCode, criteriaDefinition, "code_postal", CriteriaDefinition.Combinator.AND, false);
        CriteriaDefinition criteriaDefinitionCombinee = getCriteriaDefinitionLikeIgnoreCase(nomVoie, null, "nom_voie_upper_case_unaccent", CriteriaDefinition.Combinator.AND, false);
        criteriaDefinitionCombinee = getCriteriaDefinitionLikeIgnoreCase(nomVoie, criteriaDefinitionCombinee, "nom_afnor", CriteriaDefinition.Combinator.OR, true);
        if(criteriaDefinition != null) {
            if(criteriaDefinitionCombinee != null) {
                return ((Criteria) criteriaDefinition).and(criteriaDefinitionCombinee);
            }
            return criteriaDefinition;
        }
        return criteriaDefinitionCombinee;
    }

    private CriteriaDefinition getCriteriaDefinitionLikeIgnoreCase(String valeur, CriteriaDefinition criteriaDefinition, String nomColunm, CriteriaDefinition.Combinator combinator, boolean ignoreCase) {
        if(StringUtils.isNotBlank(valeur)) {
            if(criteriaDefinition == null || CriteriaDefinition.Combinator.INITIAL.equals(combinator)) {
                criteriaDefinition = Criteria.where(nomColunm).like(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase);
            } else if (CriteriaDefinition.Combinator.AND.equals(combinator)){
                criteriaDefinition = ((Criteria) criteriaDefinition).and(Criteria.where(nomColunm).like(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase));
            } else if (CriteriaDefinition.Combinator.OR.equals(combinator)){
                criteriaDefinition = ((Criteria) criteriaDefinition).or(Criteria.where(nomColunm).like(StringUtils.stripAccents(valeur)).ignoreCase(ignoreCase));
            }
        }
        return criteriaDefinition;
    }

}

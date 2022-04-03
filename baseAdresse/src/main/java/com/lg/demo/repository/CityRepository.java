package com.lg.demo.repository;

import com.lg.demo.dto.entity.City;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CityRepository extends ReactiveCrudRepository<City, Long>, ReactiveQueryByExampleExecutor<City> {

}

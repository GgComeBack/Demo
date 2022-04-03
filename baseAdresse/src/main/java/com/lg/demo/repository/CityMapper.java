package com.lg.demo.repository;

import com.lg.demo.dto.CityDTO;
import com.lg.demo.dto.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDTO fromEntity(City city);
}

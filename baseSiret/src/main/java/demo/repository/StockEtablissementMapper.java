package demo.repository;

import demo.dto.StockEtablissementDTO;
import demo.dto.entity.StockEtablissement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockEtablissementMapper {

    StockEtablissementDTO fromEntity(StockEtablissement stockEtablissement);
}

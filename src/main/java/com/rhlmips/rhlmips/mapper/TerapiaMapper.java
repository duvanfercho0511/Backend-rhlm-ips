package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.TerapiaDTO;
import com.rhlmips.rhlmips.model.Terapia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TerapiaMapper {

    TerapiaMapper INSTANCE = Mappers.getMapper(TerapiaMapper.class);

    //DTO TO ENTITY

    Terapia toTerapia(TerapiaDTO terapiaDTO);

    //ENTITY TO DTO
    TerapiaDTO toTerapiaDTO(Terapia terapia);
}

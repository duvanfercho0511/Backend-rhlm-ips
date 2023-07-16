package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.SedeDTO;
import com.rhlmips.rhlmips.model.Sede;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SedeMapper {

    SedeMapper INSTANCE = Mappers.getMapper(SedeMapper.class);

    //DTO TO ENTITY
    Sede toSede(SedeDTO sedeDTO);

    //ENTITY TO DTO
    SedeDTO toSedeDTO(Sede sede);
}

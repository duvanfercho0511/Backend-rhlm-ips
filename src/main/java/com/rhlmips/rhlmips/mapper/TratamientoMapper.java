package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.TratamientoDTO;
import com.rhlmips.rhlmips.model.Tratamiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TratamientoMapper {

    TratamientoMapper INSTANCE = Mappers.getMapper(TratamientoMapper.class);

    //DTO TO ENTITY
    Tratamiento toTratamiento(TratamientoDTO tratamientoDTO);

    //ENTITY TO DTO

    @Mapping(target = "nombreMedicamento", source = "medicamento.nombre")
    @Mapping(target = "nombreTerapia", source = "terapia.nombre")
    TratamientoDTO toTratamientoDTO(Tratamiento tratamiento);
}

package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.PersonaDTO;
import com.rhlmips.rhlmips.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    //DTO TO ENTITY

    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "fechaDesde", source = "fechaDesde")
    @Mapping(target = "fechaHasta", source = "fechaHasta")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    Persona toPersona(PersonaDTO personaDTO);

    //ENTITY TO DTO

    @Mapping(target = "documento", source = "documento")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "fechaDesde", source = "fechaDesde")
    @Mapping(target = "fechaHasta", source = "fechaHasta")
    @Mapping(target = "fechaNacimiento", source = "fechaNacimiento")
    PersonaDTO toPersonaDTO(Persona persona);
}

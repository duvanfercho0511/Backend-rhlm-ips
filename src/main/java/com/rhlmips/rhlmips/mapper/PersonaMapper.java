package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.PersonaDTO;
import com.rhlmips.rhlmips.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    //DTO TO ENTITY

    Persona toPersona(PersonaDTO personaDTO);

    //ENTITY TO DTO

    PersonaDTO toPersonaDTO(Persona persona);
}

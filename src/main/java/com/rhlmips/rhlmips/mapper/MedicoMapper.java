package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.MedicoDTO;
import com.rhlmips.rhlmips.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicoMapper {

    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    //DTO TO ENTITY
    Medico toMedico(MedicoDTO medicoDTO);

    //ENTITY TO DTO

    @Mapping(target = "nombrePersona", source = "persona.nombre")
    MedicoDTO toMedicoDTO(Medico medico);
}

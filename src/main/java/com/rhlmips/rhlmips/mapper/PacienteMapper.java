package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.PacienteDTO;
import com.rhlmips.rhlmips.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    //DTO TO ENTITY
    Paciente toPaciente(PacienteDTO pacienteDTO);

    //ENTITY TO DTO

    @Mapping(target = "nombrePersona", source = "persona.nombre")
    PacienteDTO toPacienteDTO(Paciente paciente);

}

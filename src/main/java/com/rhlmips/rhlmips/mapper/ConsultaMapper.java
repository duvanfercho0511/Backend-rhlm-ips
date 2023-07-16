package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.ConsultaDTO;
import com.rhlmips.rhlmips.model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsultaMapper {

    ConsultaMapper INSTANCE = Mappers.getMapper(ConsultaMapper.class);

    //DTO TO ENTITY
    Consulta toConsulta(ConsultaDTO consultaDTO);

    //ENTITY TO DTO

    @Mapping(target = "nombreSede", source = "sede.nombre")
    @Mapping(target = "nombrePaciente", source = "paciente.persona.nombre")
    @Mapping(target = "nombreMedico", source = "medico.persona.nombre")
    ConsultaDTO toConsultaDTO(Consulta consulta);
}

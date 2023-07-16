package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.AgendaDTO;
import com.rhlmips.rhlmips.model.Agenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgendaMapper {

    AgendaMapper INSTANCE = Mappers.getMapper(AgendaMapper.class);

    //DTO TO ENTITY
    Agenda toAgenda(AgendaDTO agendaDTO);

    //ENTITY TO DTO
    @Mapping(target = "nombreMedico", source = "medico.persona.nombre")
    AgendaDTO toAgendaDTO(Agenda agenda);
}

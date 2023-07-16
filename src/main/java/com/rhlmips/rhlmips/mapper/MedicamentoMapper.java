package com.rhlmips.rhlmips.mapper;

import com.rhlmips.rhlmips.dto.MedicamentoDTO;
import com.rhlmips.rhlmips.model.Medicamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicamentoMapper {

    MedicamentoMapper INSTANCE = Mappers.getMapper(MedicamentoMapper.class);

    //DTO TO ENTITY
    Medicamento toMedicamento(MedicamentoDTO medicamentoDTO);

    //ENTITY TO DTO
    MedicamentoDTO toMedicamentoDTO(Medicamento medicamento);
}

package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Consulta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IConsultaService {

    List<Consulta> getAllByIdPacienteAndIdMedico(Long idPaciente, Long idMedico);

    Consulta findConsultaById(Long id);

    Consulta createConsulta(Consulta consulta);

    Consulta updateConsulta(Consulta consulta);

    void deleteConsultaById(Long id);


}

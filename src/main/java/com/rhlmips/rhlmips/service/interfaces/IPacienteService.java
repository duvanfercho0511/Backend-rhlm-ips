package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {

    List<Paciente> getAll();

    Paciente findPacienteById(Long id);

    Paciente createPaciente(Paciente paciente);

    void deletePacienteById(Long id);


}

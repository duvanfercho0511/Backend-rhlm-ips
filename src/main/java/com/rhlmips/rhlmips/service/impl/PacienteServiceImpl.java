package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Paciente;
import com.rhlmips.rhlmips.model.Sede;
import com.rhlmips.rhlmips.repository.IPacienteRepository;
import com.rhlmips.rhlmips.repository.IPersonaRepository;
import com.rhlmips.rhlmips.service.interfaces.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private IPacienteRepository pacienteRepository;

    private EntityManager entityManager;

    @Override
    public List<Paciente> getAll() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Paciente findPacienteById(Long id) {
        return this.pacienteRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Paciente createPaciente(Paciente paciente) {
        this.validarCreacionPaciente(paciente);
        var pacienteBD = this.pacienteRepository.save(paciente);
        this.entityManager.refresh(pacienteBD);
        return pacienteBD;
    }

    @Override
    public void deletePacienteById(Long id) {
        var pacienteBD = this.pacienteRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.pacienteRepository.deleteById(pacienteBD.getId());
    }

    private void validarCreacionPaciente(Paciente paciente){
        var existsSimilar = this.pacienteRepository.existsByIdPersona(paciente.getId(), paciente.getIdPersona());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Esta persona ya está registrada como paciente");
    }

    @Autowired
    public void setPacienteRepository(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

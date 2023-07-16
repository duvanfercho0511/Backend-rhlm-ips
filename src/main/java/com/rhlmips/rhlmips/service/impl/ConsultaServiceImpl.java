package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Consulta;
import com.rhlmips.rhlmips.repository.*;
import com.rhlmips.rhlmips.service.interfaces.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    private IConsultaRepository consultaRepository;

    private ISedeRepository sedeRepository;

    private IPacienteRepository pacienteRepository;

    private IMedicoRepository medicoRepository;

    private ITratamientoRepository tratamientoRepository;

    private EntityManager entityManager;

    @Override
    public List<Consulta> getAllByIdPacienteAndIdMedico(Long idPaciente, Long idMedico) {
        return this.consultaRepository.getAllByIdPacienteAndIdMedico(idPaciente, idMedico);
    }

    @Override
    public Consulta findConsultaById(Long id) {
        return this.consultaRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Consulta createConsulta(Consulta consulta) {
        this.validarCreacionConsulta(consulta);
        var consultaBD = this.consultaRepository.save(consulta);
        this.entityManager.refresh(consultaBD);
        return consultaBD;
    }

    @Override
    public Consulta updateConsulta(Consulta consulta) {
        this.validarCreacionConsulta(consulta);
        var consultaBD = this.consultaRepository.findById(consulta.getId()).orElseThrow(DataNotFoundException::new);
        consultaBD.setIdSede(consulta.getIdSede());
        consultaBD.setIdPaciente(consulta.getIdPaciente());
        consultaBD.setIdMedico(consulta.getIdMedico());
        consultaBD.setIdTratamiento(consulta.getIdTratamiento());
        consultaBD.setFecha(consulta.getFecha());
        consultaBD.setSintomas(consulta.getSintomas());
        consultaBD.setDiagnostico(consulta.getDiagnostico());
        return this.setValoresDTO(consultaBD);
    }

    @Override
    public void deleteConsultaById(Long id) {
        var consultaBD = this.consultaRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.consultaRepository.deleteById(consultaBD.getId());
    }

    private void validarCreacionConsulta(Consulta consulta){
        var existsSimilar = this.consultaRepository.existsBySedeAndPacienteAndMedico(consulta.getId(), consulta.getIdSede(), consulta.getIdPaciente(), consulta.getIdMedico(), consulta.getFecha());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe una consulta en esta fecha para esta sede, paciente o medico.");
    }

    private Consulta setValoresDTO(Consulta consulta){

        var consultaSaved = this.consultaRepository.save(consulta);

        var sede = this.sedeRepository.findById(consultaSaved.getIdSede()).orElseThrow(DataNotFoundException::new);
        var paciente = this.pacienteRepository.findById(consultaSaved.getIdPaciente()).orElseThrow(DataNotFoundException::new);
        var medico = this.medicoRepository.findById(consultaSaved.getIdMedico()).orElseThrow(DataNotFoundException::new);
        var tratamiento = this.tratamientoRepository.findById(consultaSaved.getIdTratamiento()).orElseThrow(DataNotFoundException::new);

        if(sede!= null){
            consultaSaved.setSede(sede);
        }
        if(paciente!= null){
            consultaSaved.setPaciente(paciente);
        }
        if(medico!= null){
            consultaSaved.setMedico(medico);
        }
        if(tratamiento!= null){
            consultaSaved.setTratamiento(tratamiento);
        }
        return consultaSaved;
    }


    @Autowired
    public void setConsultaRepository(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Autowired
    public void setTratamientoRepository(ITratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    @Autowired
    public void setMedicoRepository(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Autowired
    public void setPacienteRepository(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Autowired
    public void setSedeRepository(ISedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

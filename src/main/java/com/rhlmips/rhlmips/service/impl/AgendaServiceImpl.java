package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Agenda;
import com.rhlmips.rhlmips.repository.IAgendaRepository;
import com.rhlmips.rhlmips.repository.IConsultaRepository;
import com.rhlmips.rhlmips.repository.IMedicoRepository;
import com.rhlmips.rhlmips.service.interfaces.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AgendaServiceImpl implements IAgendaService {

    private IAgendaRepository agendaRepository;

    private IMedicoRepository medicoRepository;

    private IConsultaRepository consultaRepository;

    private EntityManager entityManager;

    @Override
    public List<Agenda> getAllByIdMedico(Long idMedico) {
        return this.agendaRepository.getAllByIdMedico(idMedico);
    }

    @Override
    public Agenda findAgendaById(Long id) {
        return this.agendaRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Agenda createAgenda(Agenda agenda) {
        //this.validarCreacionAgenda(agenda);
        var agendaBD =  this.agendaRepository.save(agenda);
        this.entityManager.refresh(agendaBD);
        return agendaBD;
    }

    @Override
    public Agenda updateAgenda(Agenda agenda) {
        //this.validarCreacionAgenda(agenda);
        var agendaBD = this.agendaRepository.findById(agenda.getId()).orElseThrow(DataNotFoundException::new);
        agendaBD.setIdMedico(agenda.getIdMedico());
        agendaBD.setIdConsulta(agenda.getIdConsulta());
        agendaBD.setHoraInicio(agenda.getHoraInicio());
        agendaBD.setHoraFin(agenda.getHoraFin());
        agendaBD.setDia(agenda.getDia());
        return this.setValoresDTO(agendaBD);
    }

    @Override
    public void deleteAgendaById(Long id) {
        var agendaBD = this.agendaRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.agendaRepository.deleteById(agendaBD.getId());
    }

    private void validarCreacionAgenda(Agenda agenda){
        var existsSimilar = this.agendaRepository.existsByIdMedicoAndFechasContenidas(agenda.getId(), agenda.getIdMedico(), agenda.getHoraInicio(), agenda.getHoraFin(), agenda.getDia());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro en la agenda de este medico a esta hora.");
    }

    private Agenda setValoresDTO(Agenda agenda){

        var agendaSaved = this.agendaRepository.save(agenda);

        var medico = this.medicoRepository.findById(agendaSaved.getIdMedico()).orElseThrow(DataNotFoundException::new);
        var consulta = this.consultaRepository.findById(agendaSaved.getIdConsulta()).orElseThrow(DataNotFoundException::new);

        if(medico!= null){
            agendaSaved.setMedico(medico);
        }
        if(consulta!= null){
            agendaSaved.setConsulta(consulta);
        }
        return agendaSaved;
    }

    @Autowired
    public void setAgendaRepository(IAgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Autowired
    public void setMedicoRepository(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Autowired
    public void setConsultaRepository(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

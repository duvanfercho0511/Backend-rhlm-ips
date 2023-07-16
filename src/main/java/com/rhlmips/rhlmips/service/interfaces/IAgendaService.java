package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Agenda;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAgendaService {

    List<Agenda> getAllByIdMedico(Long idMedico);

    Agenda findAgendaById(Long id);

    Agenda createAgenda(Agenda agenda);

    Agenda updateAgenda(Agenda agenda);

    void deleteAgendaById(Long id);


}

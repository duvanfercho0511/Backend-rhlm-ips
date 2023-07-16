package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Medico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMedicoService {

    List<Medico> getAll();

    Medico findMedicoById(Long id);

    Medico createMedico(Medico medico);

    Medico updateMedico(Medico medico);

    void deleteMedicoById(Long id);
}

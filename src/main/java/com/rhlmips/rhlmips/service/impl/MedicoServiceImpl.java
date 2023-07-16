package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Medico;
import com.rhlmips.rhlmips.model.Paciente;
import com.rhlmips.rhlmips.repository.IMedicoRepository;
import com.rhlmips.rhlmips.service.interfaces.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements IMedicoService {

    private IMedicoRepository medicoRepository;

    @Override
    public List<Medico> getAll() {
        return this.medicoRepository.findAll();
    }

    @Override
    public Medico findMedicoById(Long id) {
        return this.medicoRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Medico createMedico(Medico medico) {
        this.validarCreacionMedico(medico);
        return this.medicoRepository.save(medico);
    }

    @Override
    public Medico updateMedico(Medico medico) {
        this.validarCreacionMedico(medico);
        var medicoBD = this.medicoRepository.findById(medico.getId()).orElseThrow(DataNotFoundException::new);
        medicoBD.setEspecialidad(medico.getEspecialidad());
        return this.medicoRepository.save(medicoBD);
    }

    @Override
    public void deleteMedicoById(Long id) {
        var medicoBD = this.medicoRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.medicoRepository.deleteById(medicoBD.getId());
    }

    private void validarCreacionMedico(Medico medico){
        var existsSimilar = this.medicoRepository.existsByIdPersona(medico.getId(), medico.getIdPersona());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Esta persona ya está registrada como medico");
    }

    @Autowired
    public void setMedicoRepository(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
}

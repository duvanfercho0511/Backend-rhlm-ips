package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Medicamento;
import com.rhlmips.rhlmips.model.Sede;
import com.rhlmips.rhlmips.repository.IMedicamentoRepository;
import com.rhlmips.rhlmips.service.interfaces.IMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

    private IMedicamentoRepository medicamentoRepository;

    private EntityManager entityManager;

    @Override
    public List<Medicamento> getAll() {
        return this.medicamentoRepository.findAll();
    }

    @Override
    public Medicamento findMedicamentoById(Long id) {
        return this.medicamentoRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Medicamento createMedicamento(Medicamento medicamento) {
        this.validarCreacionMedicamento(medicamento);
        var medicamentoBD =  this.medicamentoRepository.save(medicamento);
        this.entityManager.refresh(medicamentoBD);
        return medicamentoBD;
    }

    @Override
    public Medicamento updateMedicamento(Medicamento medicamento) {
        this.validarCreacionMedicamento(medicamento);
        var medicamentoBD = this.medicamentoRepository.findById(medicamento.getId()).orElseThrow(DataNotFoundException::new);
        medicamentoBD.setNombre(medicamento.getNombre());
        medicamentoBD.setConcentracion(medicamento.getConcentracion());
        medicamentoBD.setDescripcion(medicamento.getDescripcion());
        return this.medicamentoRepository.save(medicamentoBD);
    }

    @Override
    public void deleteMedicamentoById(Long id) {
        var medicamentoBD = this.medicamentoRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.medicamentoRepository.deleteById(medicamentoBD.getId());
    }

    private void validarCreacionMedicamento(Medicamento medicamento){
        var existsSimilar = this.medicamentoRepository.existsByNombre(medicamento.getId(), medicamento.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un medicamento con este nombre");
    }

    @Autowired
    public void setMedicamentoRepository(IMedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

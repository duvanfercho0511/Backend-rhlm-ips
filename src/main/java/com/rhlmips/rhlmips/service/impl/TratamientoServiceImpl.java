package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Tratamiento;
import com.rhlmips.rhlmips.repository.IMedicamentoRepository;
import com.rhlmips.rhlmips.repository.ITerapiaRepository;
import com.rhlmips.rhlmips.repository.ITratamientoRepository;
import com.rhlmips.rhlmips.service.interfaces.ITratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TratamientoServiceImpl implements ITratamientoService {

    private ITratamientoRepository tratamientoRepository;

    private ITerapiaRepository terapiaRepository;

    private IMedicamentoRepository medicamentoRepository;

    private EntityManager entityManager;


    @Override
    public List<Tratamiento> getAll() {
        return this.tratamientoRepository.findAll();
    }

    @Override
    public Tratamiento findTratamientoById(Long id) {
        return this.tratamientoRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Tratamiento createTratamiento(Tratamiento tratamiento) {
        this.validarCreacionTratamiento(tratamiento);
        var tratamientoBD = this.tratamientoRepository.save(tratamiento);
        this.entityManager.refresh(tratamientoBD);
        return tratamientoBD;
    }

    @Override
    public Tratamiento updateTratamiento(Tratamiento tratamiento) {
        this.validarCreacionTratamiento(tratamiento);
        var tratamientoBD = this.tratamientoRepository.findById(tratamiento.getId()).orElseThrow(DataNotFoundException::new);
        tratamientoBD.setIdMedicamento(tratamiento.getIdMedicamento());
        tratamientoBD.setIdTerapia(tratamiento.getIdTerapia());
        tratamientoBD.setDosisMedicamento(tratamiento.getDosisMedicamento());
        tratamientoBD.setCantidadTerapias(tratamiento.getCantidadTerapias());
        return this.setValoresDTO(tratamientoBD);
    }

    @Override
    public void deleteTratamientoById(Long id) {
        var tratamientoBD = this.tratamientoRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.tratamientoRepository.deleteById(tratamientoBD.getId());
    }

    private void validarCreacionTratamiento(Tratamiento tratamiento){
        var existsSimilar = this.tratamientoRepository.existsByMedicamentoAndTerapia(tratamiento.getId(), tratamiento.getIdMedicamento(), tratamiento.getIdTerapia(), tratamiento.getDosisMedicamento(), tratamiento.getCantidadTerapias());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un tratamiento con este medicamento, terapia, dosis y cantidad de terapias");
    }

    private Tratamiento setValoresDTO(Tratamiento tratamiento){

        var tratamientoSaved = this.tratamientoRepository.save(tratamiento);

        if(tratamientoSaved.getIdTerapia()!= null){
            var terapia = this.terapiaRepository.findById(tratamientoSaved.getIdTerapia()).orElseThrow(DataNotFoundException::new);
            tratamientoSaved.setTerapia(terapia);
        }
        if(tratamientoSaved.getIdMedicamento()!= null){
            var medicamento = this.medicamentoRepository.findById(tratamientoSaved.getIdMedicamento()).orElseThrow(DataNotFoundException::new);
            tratamientoSaved.setMedicamento(medicamento);
        }

        return tratamientoSaved;
    }


    @Autowired
    public void setTerapiaRepository(ITerapiaRepository terapiaRepository) {
        this.terapiaRepository = terapiaRepository;
    }

    @Autowired
    public void setMedicamentoRepository(IMedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Autowired
    public void setTratamientoRepository(ITratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

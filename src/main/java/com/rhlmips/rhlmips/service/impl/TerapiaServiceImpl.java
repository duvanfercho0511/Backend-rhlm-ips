package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Sede;
import com.rhlmips.rhlmips.model.Terapia;
import com.rhlmips.rhlmips.repository.ITerapiaRepository;
import com.rhlmips.rhlmips.service.interfaces.ITerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerapiaServiceImpl implements ITerapiaService {

    private ITerapiaRepository terapiaRepository;

    @Override
    public List<Terapia> getAll() {
        return this.terapiaRepository.findAll();
    }

    @Override
    public Terapia findTerapiaById(Long id) {
        return this.terapiaRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Terapia createTerapia(Terapia terapia) {
        this.validarCreacionTerapia(terapia);
        return this.terapiaRepository.save(terapia);
    }

    @Override
    public Terapia updateTerapia(Terapia terapia) {
        this.validarCreacionTerapia(terapia);
        var terapiaBD = this.terapiaRepository.findById(terapia.getId()).orElseThrow(DataNotFoundException::new);
        terapiaBD.setNombre(terapia.getNombre());
        terapiaBD.setDescripcion(terapia.getDescripcion());
        return this.terapiaRepository.save(terapiaBD);
    }

    @Override
    public void deleteTerapiaById(Long id) {
        var terapiaBD = this.terapiaRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.terapiaRepository.deleteById(terapiaBD.getId());
    }

    private void validarCreacionTerapia(Terapia terapia){
        var existsSimilar = this.terapiaRepository.existsByNombre(terapia.getId(), terapia.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe una terapia con este nombre");
    }


    @Autowired
    public void setTerapiaRepository(ITerapiaRepository terapiaRepository) {
        this.terapiaRepository = terapiaRepository;
    }
}

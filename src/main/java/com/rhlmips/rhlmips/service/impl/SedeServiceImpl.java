package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Sede;
import com.rhlmips.rhlmips.repository.ISedeRepository;
import com.rhlmips.rhlmips.service.interfaces.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SedeServiceImpl implements ISedeService {

    private ISedeRepository sedeRepository;

    @Override
    public List<Sede> getAll() {
        return this.sedeRepository.findAll();
    }

    @Override
    public Sede findSedeById(Long id) {
        return this.sedeRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Sede createSede(Sede sede) {
        this.validarCreacionSede(sede);
        return this.sedeRepository.save(sede);
    }

    @Override
    public Sede updateSede(Sede sede) {
        this.validarCreacionSede(sede);
        var sedeBD = this.sedeRepository.findById(sede.getId()).orElseThrow(DataNotFoundException::new);
        sedeBD.setNombre(sede.getNombre());
        sedeBD.setDireccion(sede.getDireccion());
        return this.sedeRepository.save(sedeBD);
    }

    @Override
    public void deleteSedeById(Long id) {
        var sedeBD = this.sedeRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.sedeRepository.deleteById(sedeBD.getId());
    }

    private void validarCreacionSede(Sede sede){
        var existsSimilar = this.sedeRepository.existsByNombre(sede.getId(), sede.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe una sede con este nombre");
    }

    @Autowired
    public void setSedeRepository(ISedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }
}

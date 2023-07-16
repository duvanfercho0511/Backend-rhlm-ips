package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.Util.ITools;
import com.rhlmips.rhlmips.exception.DataNotFoundException;
import com.rhlmips.rhlmips.exception.ValidacionException;
import com.rhlmips.rhlmips.model.Persona;
import com.rhlmips.rhlmips.repository.IPersonaRepository;
import com.rhlmips.rhlmips.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private IPersonaRepository personaRepository;

    private EntityManager entityManager;

    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Override
    public Persona findPersonaById(Long id) {
        return this.personaRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public Persona createPersona(Persona persona) {
        this.validarCreacionPersona(persona);
        var personaBD = this.personaRepository.save(persona);
        this.entityManager.refresh(personaBD);
        return personaBD;
    }

    @Override
    public Persona updatePersona(Persona persona) {
        this.validarCreacionPersona(persona);
        var personaBD = this.personaRepository.findById(persona.getId()).orElseThrow(DataNotFoundException::new);
        personaBD.setDocumento(persona.getDocumento());
        personaBD.setNombre(persona.getNombre());
        personaBD.setTelefono(persona.getTelefono());
        personaBD.setFechaNacimiento(persona.getFechaNacimiento());
        personaBD.setFechaDesde(persona.getFechaDesde());
        personaBD.setFechaHasta(persona.getFechaHasta());
        return this.personaRepository.save(personaBD);
    }

    @Override
    public void deletePersonaById(Long id) {
        var personaBD = this.personaRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.validarEliminacionPersona(personaBD);
        this.personaRepository.deleteById(personaBD.getId());
    }

    private void validarCreacionPersona(Persona persona){
        if (persona.getFechaHasta() != null &&
                ITools.isFechaAMayorIgualQueFechaB(persona.getFechaDesde(),
                        persona.getFechaHasta(), ">"))
            throw new ValidacionException("La fecha hasta es menor que la fecha desde");

        var existsSimilar = this.personaRepository.existsByDocumento(persona.getId(), persona.getDocumento());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe una persona con este numero de documento");
    }

    private void validarEliminacionPersona(Persona persona){
        Date hoy = new Date();
        if (persona.getFechaDesde() != null &&
                ITools.isFechaAMayorIgualQueFechaB(hoy, persona.getFechaDesde(), ">"))
            throw new ValidacionException("La fecha desde debe ser mayor a hoy, esto quiere decir que el usuario no está activo");
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

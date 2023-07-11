package com.rhlmips.rhlmips.service.impl;

import com.rhlmips.rhlmips.model.Persona;
import com.rhlmips.rhlmips.repository.IPersonaRepository;
import com.rhlmips.rhlmips.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private IPersonaRepository personaRepository;

    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
}

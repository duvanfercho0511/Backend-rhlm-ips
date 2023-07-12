package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonaService {

    List<Persona> getAll();

    Persona findPersonaById(Long id);

    Persona createPersona(Persona persona);

    Persona updatePersona(Persona persona);

    void deletePersonaById(Long id);

}

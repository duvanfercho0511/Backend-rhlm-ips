package com.rhlmips.rhlmips.controller;


import com.rhlmips.rhlmips.dto.PersonaDTO;
import com.rhlmips.rhlmips.mapper.PersonaMapper;
import com.rhlmips.rhlmips.model.Persona;
import com.rhlmips.rhlmips.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    private IPersonaService personaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonaDTO>> getAll(){
        List<Persona> personaList = this.personaService.getAll();
        if (personaList == null || personaList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personaList.stream()
                .map(PersonaMapper.INSTANCE::toPersonaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }
}

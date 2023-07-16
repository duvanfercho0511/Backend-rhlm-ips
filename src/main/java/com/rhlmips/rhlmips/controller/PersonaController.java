package com.rhlmips.rhlmips.controller;


import com.rhlmips.rhlmips.dto.PersonaDTO;
import com.rhlmips.rhlmips.mapper.PersonaMapper;
import com.rhlmips.rhlmips.model.Persona;
import com.rhlmips.rhlmips.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> findPersonaById(
            @PathVariable(value = "id") Long id){
        Persona persona = this.personaService.findPersonaById(id);
        if (persona == null) {
            return new ResponseEntity<>(new PersonaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonaMapper.INSTANCE.toPersonaDTO(persona), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> createPersona(
            @Valid @RequestBody PersonaDTO personaDTO){
        Persona persona = PersonaMapper.INSTANCE.toPersona(personaDTO);
        var personaBD = this.personaService.createPersona(persona);
        if(personaBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonaMapper.INSTANCE.toPersonaDTO(personaBD));
    }

    @PutMapping
    public ResponseEntity<PersonaDTO> updatePersona(
            @Valid @RequestBody PersonaDTO personaDTO){
        Persona persona = PersonaMapper.INSTANCE.toPersona(personaDTO);
        var personaBD = this.personaService.updatePersona(persona);
        if (personaBD == null) {
            return new ResponseEntity<>(new PersonaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonaMapper.INSTANCE.toPersonaDTO(personaBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersona(
            @PathVariable(value = "id") Long id){
        this.personaService.deletePersonaById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }
}

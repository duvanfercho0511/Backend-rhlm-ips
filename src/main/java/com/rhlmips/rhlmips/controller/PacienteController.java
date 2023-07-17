package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.PacienteDTO;
import com.rhlmips.rhlmips.mapper.PacienteMapper;
import com.rhlmips.rhlmips.model.Paciente;
import com.rhlmips.rhlmips.service.interfaces.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private IPacienteService pacienteService;
    @GetMapping("/getAllPaciente")
    public ResponseEntity<List<PacienteDTO>> getAllPaciente(){
        List<Paciente> pacienteList = this.pacienteService.getAll();
        if (pacienteList == null || pacienteList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pacienteList.stream()
                .map(PacienteMapper.INSTANCE::toPacienteDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findPacienteById(
            @PathVariable(value = "id") Long id){
        Paciente paciente = this.pacienteService.findPacienteById(id);
        if (paciente == null) {
            return new ResponseEntity<>(new PacienteDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PacienteMapper.INSTANCE.toPacienteDTO(paciente), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(
            @Valid @RequestBody PacienteDTO pacienteDTO){
        Paciente paciente = PacienteMapper.INSTANCE.toPaciente(pacienteDTO);
        var pacienteBD = this.pacienteService.createPaciente(paciente);
        if(pacienteBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PacienteMapper.INSTANCE.toPacienteDTO(pacienteBD));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePaciente(
            @PathVariable(value = "id") Long id){
        this.pacienteService.deletePacienteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPacienteService(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
}

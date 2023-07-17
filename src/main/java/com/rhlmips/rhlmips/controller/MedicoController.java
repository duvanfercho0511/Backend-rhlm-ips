package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.MedicoDTO;
import com.rhlmips.rhlmips.dto.PersonaDTO;
import com.rhlmips.rhlmips.mapper.MedicamentoMapper;
import com.rhlmips.rhlmips.mapper.MedicoMapper;
import com.rhlmips.rhlmips.mapper.PersonaMapper;
import com.rhlmips.rhlmips.model.Medico;
import com.rhlmips.rhlmips.model.Persona;
import com.rhlmips.rhlmips.service.interfaces.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private IMedicoService medicoService;

    @GetMapping("/getAllMedico")
    public ResponseEntity<List<MedicoDTO>> getAllMedico(){
        List<Medico> medicoList = this.medicoService.getAll();
        if (medicoList == null || medicoList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicoList.stream()
                .map(MedicoMapper.INSTANCE::toMedicoDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> findMedicoById(
            @PathVariable(value = "id") Long id){
        Medico medico = this.medicoService.findMedicoById(id);
        if (medico == null) {
            return new ResponseEntity<>(new MedicoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(MedicoMapper.INSTANCE.toMedicoDTO(medico), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> createMedico(
            @Valid @RequestBody MedicoDTO medicoDTO){
        Medico medico = MedicoMapper.INSTANCE.toMedico(medicoDTO);
        var medicoBD = this.medicoService.createMedico(medico);
        if(medicoBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(MedicoMapper.INSTANCE.toMedicoDTO(medicoBD));
    }

    @PutMapping
    public ResponseEntity<MedicoDTO> updateMedico(
            @Valid @RequestBody MedicoDTO medicoDTO){
        Medico medico = MedicoMapper.INSTANCE.toMedico(medicoDTO);
        var medicoBD = this.medicoService.updateMedico(medico);
        if (medicoBD == null) {
            return new ResponseEntity<>(new MedicoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(MedicoMapper.INSTANCE.toMedicoDTO(medicoBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedico(
            @PathVariable(value = "id") Long id){
        this.medicoService.deleteMedicoById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setMedicoService(IMedicoService medicoService) {
        this.medicoService = medicoService;
    }
}

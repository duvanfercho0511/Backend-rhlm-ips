package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.AgendaDTO;
import com.rhlmips.rhlmips.dto.ConsultaDTO;
import com.rhlmips.rhlmips.mapper.AgendaMapper;
import com.rhlmips.rhlmips.mapper.ConsultaMapper;
import com.rhlmips.rhlmips.model.Agenda;
import com.rhlmips.rhlmips.model.Consulta;
import com.rhlmips.rhlmips.service.interfaces.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private IAgendaService agendaService;

    @GetMapping("/getAllAgenda")
    public ResponseEntity<List<AgendaDTO>> getAllAgenda(
            @RequestParam (value = "idMedico", required = false) Long idMedico){
        List<Agenda> agendaList = this.agendaService.getAllByIdMedico(idMedico);
        if (agendaList == null || agendaList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(agendaList.stream()
                .map(AgendaMapper.INSTANCE::toAgendaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> findAgendaById(
            @PathVariable(value = "id") Long id){
        Agenda agenda = this.agendaService.findAgendaById(id);
        if (agenda == null) {
            return new ResponseEntity<>(new AgendaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AgendaMapper.INSTANCE.toAgendaDTO(agenda), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AgendaDTO> createAgenda(
            @Valid @RequestBody AgendaDTO agendaDTO){
        Agenda agenda = AgendaMapper.INSTANCE.toAgenda(agendaDTO);
        var agendaBD = this.agendaService.createAgenda(agenda);
        if(agendaBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(AgendaMapper.INSTANCE.toAgendaDTO(agendaBD));
    }

    @PutMapping
    public ResponseEntity<AgendaDTO> updateAgenda(
            @Valid @RequestBody AgendaDTO agendaDTO){
        Agenda agenda = AgendaMapper.INSTANCE.toAgenda(agendaDTO);
        var agendaBD = this.agendaService.updateAgenda(agenda);
        if (agendaBD == null) {
            return new ResponseEntity<>(new AgendaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AgendaMapper.INSTANCE.toAgendaDTO(agendaBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAgenda(
            @PathVariable(value = "id") Long id){
        this.agendaService.deleteAgendaById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setAgendaService(IAgendaService agendaService) {
        this.agendaService = agendaService;
    }
}

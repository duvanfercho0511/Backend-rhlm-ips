package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.ConsultaDTO;
import com.rhlmips.rhlmips.mapper.ConsultaMapper;
import com.rhlmips.rhlmips.model.Consulta;
import com.rhlmips.rhlmips.service.interfaces.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private IConsultaService consultaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ConsultaDTO>> getAll(
            @RequestParam (value = "idPaciente", required = false) Long idPaciente,
            @RequestParam (value = "idMedico", required = false) Long idMedico){
        List<Consulta> consultaList = this.consultaService.getAllByIdPacienteAndIdMedico(idPaciente, idMedico);
        if (consultaList == null || consultaList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(consultaList.stream()
                .map(ConsultaMapper.INSTANCE::toConsultaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> findConsultaById(
            @PathVariable(value = "id") Long id){
        Consulta consulta = this.consultaService.findConsultaById(id);
        if (consulta == null) {
            return new ResponseEntity<>(new ConsultaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ConsultaMapper.INSTANCE.toConsultaDTO(consulta), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(
            @Valid @RequestBody ConsultaDTO consultaDTO){
        Consulta consulta = ConsultaMapper.INSTANCE.toConsulta(consultaDTO);
        var consultaBD = this.consultaService.createConsulta(consulta);
        if(consultaBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ConsultaMapper.INSTANCE.toConsultaDTO(consultaBD));
    }

    @PutMapping
    public ResponseEntity<ConsultaDTO> updateConsulta(
            @Valid @RequestBody ConsultaDTO consultaDTO){
        Consulta consulta = ConsultaMapper.INSTANCE.toConsulta(consultaDTO);
        var consultaBD = this.consultaService.updateConsulta(consulta);
        if (consultaBD == null) {
            return new ResponseEntity<>(new ConsultaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ConsultaMapper.INSTANCE.toConsultaDTO(consultaBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteConsulta(
            @PathVariable(value = "id") Long id){
        this.consultaService.deleteConsultaById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setConsultaService(IConsultaService consultaService) {
        this.consultaService = consultaService;
    }
}

package com.rhlmips.rhlmips.controller;


import com.rhlmips.rhlmips.dto.MedicamentoDTO;
import com.rhlmips.rhlmips.dto.TerapiaDTO;
import com.rhlmips.rhlmips.mapper.MedicamentoMapper;
import com.rhlmips.rhlmips.mapper.TerapiaMapper;
import com.rhlmips.rhlmips.model.Medicamento;
import com.rhlmips.rhlmips.model.Terapia;
import com.rhlmips.rhlmips.service.interfaces.ITerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/terapia")
public class TerapiaController {

    private ITerapiaService terapiaService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TerapiaDTO>> getAll(){
        List<Terapia> terapiaList = this.terapiaService.getAll();
        if (terapiaList == null || terapiaList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(terapiaList.stream()
                .map(TerapiaMapper.INSTANCE::toTerapiaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerapiaDTO> findTerapiaById(
            @PathVariable(value = "id") Long id){
        Terapia terapia = this.terapiaService.findTerapiaById(id);
        if (terapia == null) {
            return new ResponseEntity<>(new TerapiaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TerapiaMapper.INSTANCE.toTerapiaDTO(terapia), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TerapiaDTO> createTerapia(
            @Valid @RequestBody TerapiaDTO terapiaDTO){
        Terapia terapia = TerapiaMapper.INSTANCE.toTerapia(terapiaDTO);
        var terapiaBD = this.terapiaService.createTerapia(terapia);
        if(terapiaBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(TerapiaMapper.INSTANCE.toTerapiaDTO(terapiaBD));
    }

    @PutMapping
    public ResponseEntity<TerapiaDTO> updateTerapia(
            @Valid @RequestBody TerapiaDTO terapiaDTO){
        Terapia terapia = TerapiaMapper.INSTANCE.toTerapia(terapiaDTO);
        var terapiaBD = this.terapiaService.updateTerapia(terapia);
        if (terapiaBD == null) {
            return new ResponseEntity<>(new TerapiaDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TerapiaMapper.INSTANCE.toTerapiaDTO(terapiaBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTerapia(
            @PathVariable(value = "id") Long id){
        this.terapiaService.deleteTerapiaById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setTerapiaService(ITerapiaService terapiaService) {
        this.terapiaService = terapiaService;
    }
}

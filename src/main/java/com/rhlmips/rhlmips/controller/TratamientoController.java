package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.MedicoDTO;
import com.rhlmips.rhlmips.dto.TratamientoDTO;
import com.rhlmips.rhlmips.mapper.MedicoMapper;
import com.rhlmips.rhlmips.mapper.TratamientoMapper;
import com.rhlmips.rhlmips.model.Medico;
import com.rhlmips.rhlmips.model.Tratamiento;
import com.rhlmips.rhlmips.service.interfaces.ITratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tratamiento")
public class TratamientoController {

    private ITratamientoService tratamientoService;

    @GetMapping("/getAllTratamiento")
    public ResponseEntity<List<TratamientoDTO>> getAllTratamiento(){
        List<Tratamiento> tratamientoList = this.tratamientoService.getAll();
        if (tratamientoList == null || tratamientoList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tratamientoList.stream()
                .map(TratamientoMapper.INSTANCE::toTratamientoDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamientoDTO> findTratamientoById(
            @PathVariable(value = "id") Long id){
        Tratamiento tratamiento = this.tratamientoService.findTratamientoById(id);
        if (tratamiento == null) {
            return new ResponseEntity<>(new TratamientoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TratamientoMapper.INSTANCE.toTratamientoDTO(tratamiento), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TratamientoDTO> createTratamiento(
            @Valid @RequestBody TratamientoDTO tratamientoDTO){
        Tratamiento tratamiento = TratamientoMapper.INSTANCE.toTratamiento(tratamientoDTO);
        var tratamientoBD = this.tratamientoService.createTratamiento(tratamiento);
        if(tratamientoBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(TratamientoMapper.INSTANCE.toTratamientoDTO(tratamientoBD));
    }

    @PutMapping
    public ResponseEntity<TratamientoDTO> updateTratamiento(
            @Valid @RequestBody TratamientoDTO tratamientoDTO){
        Tratamiento tratamiento = TratamientoMapper.INSTANCE.toTratamiento(tratamientoDTO);
        var tratamientoBD = this.tratamientoService.updateTratamiento(tratamiento);
        if (tratamientoBD == null) {
            return new ResponseEntity<>(new TratamientoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TratamientoMapper.INSTANCE.toTratamientoDTO(tratamientoBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTratamiento(
            @PathVariable(value = "id") Long id){
        this.tratamientoService.deleteTratamientoById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setTratamientoService(ITratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }
}

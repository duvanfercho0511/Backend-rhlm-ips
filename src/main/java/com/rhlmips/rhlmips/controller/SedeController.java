package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.SedeDTO;
import com.rhlmips.rhlmips.mapper.SedeMapper;
import com.rhlmips.rhlmips.model.Sede;
import com.rhlmips.rhlmips.service.interfaces.ISedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sede")
public class SedeController {

    private ISedeService sedeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SedeDTO>> getAll(){
        List<Sede> sedeList = this.sedeService.getAll();
        if (sedeList == null || sedeList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sedeList.stream()
                .map(SedeMapper.INSTANCE::toSedeDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> findSedeById(
            @PathVariable(value = "id") Long id){
        Sede sede = this.sedeService.findSedeById(id);
        if (sede == null) {
            return new ResponseEntity<>(new SedeDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SedeMapper.INSTANCE.toSedeDTO(sede), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SedeDTO> createSede(
            @Valid @RequestBody SedeDTO sedeDTO){
        Sede sede = SedeMapper.INSTANCE.toSede(sedeDTO);
        var sedeBD = this.sedeService.createSede(sede);
        if(sedeBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SedeMapper.INSTANCE.toSedeDTO(sedeBD));
    }

    @PutMapping
    public ResponseEntity<SedeDTO> updateSede(
            @Valid @RequestBody SedeDTO sedeDTO){
        Sede sede = SedeMapper.INSTANCE.toSede(sedeDTO);
        var sedeBD = this.sedeService.updateSede(sede);
        if (sedeBD == null) {
            return new ResponseEntity<>(new SedeDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SedeMapper.INSTANCE.toSedeDTO(sedeBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSede(
            @PathVariable(value = "id") Long id){
        this.sedeService.deleteSedeById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setSedeService(ISedeService sedeService) {
        this.sedeService = sedeService;
    }
}

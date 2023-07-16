package com.rhlmips.rhlmips.controller;

import com.rhlmips.rhlmips.dto.MedicamentoDTO;
import com.rhlmips.rhlmips.mapper.MedicamentoMapper;
import com.rhlmips.rhlmips.model.Medicamento;
import com.rhlmips.rhlmips.service.interfaces.IMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

    private IMedicamentoService medicamentoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicamentoDTO>> getAll(){
        List<Medicamento> medicamentoList = this.medicamentoService.getAll();
        if (medicamentoList == null || medicamentoList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicamentoList.stream()
                .map(MedicamentoMapper.INSTANCE::toMedicamentoDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> findMedicamentoById(
            @PathVariable(value = "id") Long id){
        Medicamento medicamento = this.medicamentoService.findMedicamentoById(id);
        if (medicamento == null) {
            return new ResponseEntity<>(new MedicamentoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(MedicamentoMapper.INSTANCE.toMedicamentoDTO(medicamento), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicamentoDTO> createMedicamento(
            @Valid @RequestBody MedicamentoDTO medicamentoDTO){
        Medicamento medicamento = MedicamentoMapper.INSTANCE.toMedicamento(medicamentoDTO);
        var medicamentoBD = this.medicamentoService.createMedicamento(medicamento);
        if(medicamentoBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(MedicamentoMapper.INSTANCE.toMedicamentoDTO(medicamento));
    }

    @PutMapping
    public ResponseEntity<MedicamentoDTO> updateMedicamento(
            @Valid @RequestBody MedicamentoDTO medicamentoDTO){
        Medicamento medicamento = MedicamentoMapper.INSTANCE.toMedicamento(medicamentoDTO);
        var medicamentoBD = this.medicamentoService.updateMedicamento(medicamento);
        if (medicamentoBD == null) {
            return new ResponseEntity<>(new MedicamentoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(MedicamentoMapper.INSTANCE.toMedicamentoDTO(medicamento), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMedicamento(
            @PathVariable(value = "id") Long id){
        this.medicamentoService.deleteMedicamentoById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setMedicamentoService(IMedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }
}

package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Medicamento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMedicamentoService {

    List<Medicamento> getAll();

    Medicamento findMedicamentoById(Long id);

    Medicamento createMedicamento(Medicamento medicamento);

    Medicamento updateMedicamento(Medicamento medicamento);

    void deleteMedicamentoById(Long id);

}

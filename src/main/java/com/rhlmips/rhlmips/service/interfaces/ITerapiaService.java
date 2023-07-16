package com.rhlmips.rhlmips.service.interfaces;


import com.rhlmips.rhlmips.model.Terapia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITerapiaService {

    List<Terapia> getAll();

    Terapia findTerapiaById(Long id);

    Terapia createTerapia(Terapia terapia);

    Terapia updateTerapia(Terapia terapia);

    void deleteTerapiaById(Long id);

}

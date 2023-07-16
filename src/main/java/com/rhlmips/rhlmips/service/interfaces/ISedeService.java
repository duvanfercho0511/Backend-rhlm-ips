package com.rhlmips.rhlmips.service.interfaces;

import com.rhlmips.rhlmips.model.Sede;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISedeService {

    List<Sede> getAll();

    Sede findSedeById(Long id);

    Sede createSede(Sede sede);

    Sede updateSede(Sede sede);

    void deleteSedeById(Long id);
}

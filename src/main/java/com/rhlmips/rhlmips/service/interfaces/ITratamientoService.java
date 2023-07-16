package com.rhlmips.rhlmips.service.interfaces;


import com.rhlmips.rhlmips.model.Tratamiento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITratamientoService {

    List<Tratamiento> getAll();

    Tratamiento findTratamientoById(Long id);

    Tratamiento createTratamiento(Tratamiento tratamiento);

    Tratamiento updateTratamiento(Tratamiento tratamiento);

    void deleteTratamientoById(Long id);


}

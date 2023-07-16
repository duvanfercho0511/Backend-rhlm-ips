package com.rhlmips.rhlmips.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MedicamentoDTO implements Serializable {

    private static final long serialVersionUID = 3393961955308562496L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private String nombre;

    @NotNull(message = "Campo no puede ser nulo")
    private String concentracion;

    private String descripcion;
}

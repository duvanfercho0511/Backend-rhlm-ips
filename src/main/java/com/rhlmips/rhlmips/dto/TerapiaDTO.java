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
public class TerapiaDTO implements Serializable {

    private static final long serialVersionUID = 2787822677533346003L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private String nombre;

    private String descripcion;
}

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
public class MedicoDTO implements Serializable {

    private static final long serialVersionUID = 3275964245701691924L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idPersona;
    private String nombrePersona;

    @NotNull(message = "Campo no puede ser nulo")
    private String especialidad;

}

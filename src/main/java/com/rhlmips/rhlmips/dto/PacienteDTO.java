package com.rhlmips.rhlmips.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDTO implements Serializable {

    private static final long serialVersionUID = 6862481552065199746L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idPersona;
    private String nombrePersona;

}

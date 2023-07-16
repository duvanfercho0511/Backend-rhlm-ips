package com.rhlmips.rhlmips.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SedeDTO implements Serializable {

    private static final long serialVersionUID = -7900044663746989992L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private String nombre;

    @NotNull(message = "Campo no puede ser nulo")
    private String direccion;

}

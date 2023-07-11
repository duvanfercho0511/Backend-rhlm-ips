package com.rhlmips.rhlmips.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDTO implements Serializable {

    private static final long serialVersionUID = 3680251739268601659L;


    private Long id;

    @NotNull(message = "{validDTO.NotNull}")
    private String documento;


    @NotNull(message = "{validDTO.NotNull}")
    private String nombre;


    private String telefono;

    @NotNull(message = "{validDTO.NotNull}")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;


    @NotNull(message = "{validDTO.NotNull}")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;


    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
}

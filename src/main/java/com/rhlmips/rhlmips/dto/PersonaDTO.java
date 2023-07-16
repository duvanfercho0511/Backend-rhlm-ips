package com.rhlmips.rhlmips.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhlmips.rhlmips.Util.ITools;
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

    @NotNull(message = "Campo no puede ser nulo")
    private String documento;


    @NotNull(message = "Campo no puede ser nulo")
    private String nombre;


    private String telefono;

    @NotNull(message = "Campo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaNacimiento;


    @NotNull(message = "{validDTO.NotNull}")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaDesde;


    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = ITools.PATTERN_DATE, timezone = ITools.ZONA_HORARIA_BOGOTA)
    private Date fechaHasta;
}

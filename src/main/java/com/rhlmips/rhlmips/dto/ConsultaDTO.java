package com.rhlmips.rhlmips.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ConsultaDTO implements Serializable {

    private static final long serialVersionUID = -148403037725527092L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idSede;
    private String nombreSede;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idPaciente;
    private String nombrePaciente;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idMedico;
    private String nombreMedico;

    private Long idTratamiento;


    @NotNull(message = "Campo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    private Date fecha;


    private String sintomas;


    private String diagnostico;

}

package com.rhlmips.rhlmips.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AgendaDTO implements Serializable {

    private static final long serialVersionUID = -6692213849259004622L;

    private Long id;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idMedico;
    private String nombreMedico;

    @NotNull(message = "Campo no puede ser nulo")
    private Long idConsulta;

    @NotNull(message = "Campo no puede ser nulo")
    private Time horaInicio;

    @NotNull(message = "Campo no puede ser nulo")
    private Time horaFin;

    @NotNull(message = "Campo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    private Date dia;


}

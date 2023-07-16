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
public class TratamientoDTO implements Serializable {

    private static final long serialVersionUID = -400035251248335306L;

    private Long id;

    // hay consultas las cuales tienen medicamento o terapia.

    private Long idMedicamento;
    private String nombreMedicamento;


    private Long idTerapia;
    private String nombreTerapia;


    private String dosisMedicamento;


    private String cantidadTerapias;

}

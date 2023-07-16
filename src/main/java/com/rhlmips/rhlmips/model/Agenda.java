package com.rhlmips.rhlmips.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "agenda",schema = "ips")
public class Agenda implements Serializable {

    private static final long serialVersionUID = 7409448870390517277L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "id_consulta")
    private Long idConsulta;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;

    @Column(name = "dia")
    @Temporal(TemporalType.DATE)
    private Date dia;


    //Relaciones


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico",insertable = false,updatable = false)
    private Medico medico;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consulta",insertable = false,updatable = false)
    private Consulta consulta;
}

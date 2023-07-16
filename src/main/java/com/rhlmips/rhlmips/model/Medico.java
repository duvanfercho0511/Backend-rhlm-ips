package com.rhlmips.rhlmips.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medico",schema = "ips")
public class Medico implements Serializable {

    private static final long serialVersionUID = 8957037935235165255L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "especialidad")
    private String especialidad;

    //Relaciones

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona",insertable = false,updatable = false)
    private Persona persona;

}

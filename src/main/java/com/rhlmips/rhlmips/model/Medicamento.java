package com.rhlmips.rhlmips.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medicamento",schema = "ips")
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 7745130483000521134L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "concentracion")
    private String concentracion;

    @Column(name = "descripcion")
    private String descripcion;
}

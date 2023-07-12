package com.rhlmips.rhlmips.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "persona",schema = "ips")
public class Persona implements Serializable {

    private static final long serialVersionUID = 3680251739268601659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "documento")
    private String documento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

}

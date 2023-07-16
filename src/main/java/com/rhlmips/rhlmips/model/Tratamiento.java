package com.rhlmips.rhlmips.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tratamiento",schema = "ips")
public class Tratamiento implements Serializable {

    private static final long serialVersionUID = -7210817075733793486L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_medicamento")
    private Long idMedicamento;

    @Column(name = "id_terapia")
    private Long idTerapia;

    @Column(name = "dosis_medicamento")
    private String dosisMedicamento;

    @Column(name = "cantidad_terapias")
    private String cantidadTerapias;

    //Relaciones

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medicamento",insertable = false,updatable = false)
    private Medicamento medicamento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_terapia",insertable = false,updatable = false)
    private Terapia terapia;
}

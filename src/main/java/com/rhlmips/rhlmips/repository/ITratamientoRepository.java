package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITratamientoRepository extends JpaRepository<Tratamiento, Long> {

    @Query("SELECT CASE WHEN COUNT (t) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Tratamiento t " +
            "WHERE (t.id <> :id or :id is null) " +
            "AND t.idTerapia = :idTerapia " +
            "AND t.idMedicamento = :idMedicamento " +
            "AND t.dosisMedicamento = :dosisMedicamento " +
            "AND t.cantidadTerapias = :cantidadTerapias")
    Boolean existsByMedicamentoAndTerapia(Long id,  Long idMedicamento, Long idTerapia, String dosisMedicamento, String cantidadTerapias);

}

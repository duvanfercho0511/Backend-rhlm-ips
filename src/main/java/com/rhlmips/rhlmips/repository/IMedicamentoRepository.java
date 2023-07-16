package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicamentoRepository extends JpaRepository<Medicamento,Long> {

    @Query("SELECT CASE WHEN COUNT (m) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Medicamento m " +
            "WHERE (m.id <> :id or :id is null) " +
            "AND m.nombre = :nombre ")
    Boolean existsByNombre(Long id, String nombre);

}

package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Terapia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITerapiaRepository extends JpaRepository<Terapia,Long> {

    @Query("SELECT CASE WHEN COUNT (t) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Terapia t " +
            "WHERE (t.id <> :id or :id is null) " +
            "AND t.nombre = :nombre ")
    Boolean existsByNombre(Long id, String nombre);
}

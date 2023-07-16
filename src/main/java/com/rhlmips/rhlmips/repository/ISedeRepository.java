package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISedeRepository extends JpaRepository<Sede,Long> {

    @Query("SELECT CASE WHEN COUNT (s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Sede s " +
            "WHERE (s.id <> :id or :id is null) " +
            "AND s.nombre = :nombre ")
    Boolean existsByNombre(Long id, String nombre);
}

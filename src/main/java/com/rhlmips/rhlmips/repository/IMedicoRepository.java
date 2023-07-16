package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long> {

    @Query("SELECT CASE WHEN COUNT (m) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Medico m " +
            "WHERE (m.id <> :id or :id is null) " +
            "AND m.idPersona = :idPersona ")
    Boolean existsByIdPersona(Long id, Long idPersona);

}

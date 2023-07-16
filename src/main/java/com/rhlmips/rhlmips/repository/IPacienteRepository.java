package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Paciente p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.idPersona = :idPersona ")
    Boolean existsByIdPersona(Long id, Long idPersona);
}

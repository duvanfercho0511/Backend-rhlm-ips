package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT CASE WHEN COUNT (c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Consulta c " +
            "WHERE (c.id <> :id or :id is null) " +
            "AND ( c.idSede = :idSede " +
            "OR c.idPaciente = :idPaciente " +
            "OR c.idMedico = :idMedico) ")
    Boolean existsBySedeAndPacienteAndMedico(Long id, Long idSede, Long idPaciente, Long idMedico, Date fecha);


    @Query("SELECT c " +
            "FROM Consulta c " +
            "WHERE (c.idPaciente = :idPaciente OR :idPaciente IS NULL) " +
            "AND (c.idMedico = :idMedico OR :idMedico IS NULL) ")
    List<Consulta> getAllByIdPacienteAndIdMedico(Long idPaciente, Long idMedico);

}

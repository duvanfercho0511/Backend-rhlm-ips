package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Agenda;
import com.rhlmips.rhlmips.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface IAgendaRepository extends JpaRepository<Agenda,Long> {

    @Query("SELECT CASE WHEN COUNT (a) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Agenda a " +
            "WHERE (a.id <> :id or :id is null) " +
            "AND a.idMedico = :idMedico " +
            "AND ((:horaFin >= a.horaInicio AND :horaInicio <= a.horaFin) " +
            "OR (:horaInicio <= a.horaFin ) " +
            "OR (:horaFin >= a.horaInicio )) " +
            "AND a.dia = :dia ")
    Boolean existsByIdMedicoAndFechasContenidas(Long id, Long idMedico, Time horaInicio, Time horaFin, Date dia);


    @Query("SELECT a " +
            "FROM Agenda a " +
            "WHERE (a.idMedico = :idMedico OR :idMedico IS NULL) ")
    List<Agenda> getAllByIdMedico(Long idMedico);

}

package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgendaRepository extends JpaRepository<Agenda,Long> {
}

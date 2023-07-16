package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
}

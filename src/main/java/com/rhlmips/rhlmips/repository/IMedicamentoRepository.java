package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicamentoRepository extends JpaRepository<Medicamento,Long> {
}

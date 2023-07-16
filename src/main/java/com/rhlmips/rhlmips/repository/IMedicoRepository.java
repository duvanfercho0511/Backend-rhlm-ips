package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long> {
}

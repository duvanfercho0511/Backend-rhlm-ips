package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Terapia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITerapiaRepository extends JpaRepository<Terapia,Long> {
}

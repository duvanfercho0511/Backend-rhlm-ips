package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITratamientoRepository extends JpaRepository<Tratamiento, Long> {
}

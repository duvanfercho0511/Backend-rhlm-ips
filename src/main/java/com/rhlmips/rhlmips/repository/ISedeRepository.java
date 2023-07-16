package com.rhlmips.rhlmips.repository;

import com.rhlmips.rhlmips.model.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISedeRepository extends JpaRepository<Sede,Long> {
}

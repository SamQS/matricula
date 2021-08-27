package com.universidad.matricula.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import com.universidad.matricula.model.*;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface MatriculaRepository extends JpaRepository<Matricula,Integer>{
    @Query(value = "SELECT o FROM Matricula o WHERE o.nombre=?1")
    Optional<Matricula> findByNombre(String nombre);
}

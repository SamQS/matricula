package com.universidad.matricula.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import com.universidad.matricula.model.*;
import java.util.*;
@Repository
public interface ResultadoMatriculaRepository extends JpaRepository<ResultadoMatricula, Integer>{
    @Query(value = "SELECT o FROM ResultadoMatricula o WHERE o.matricula=?1")
    List<ResultadoMatricula> findItemsByMatricula(Matricula matricula);
}

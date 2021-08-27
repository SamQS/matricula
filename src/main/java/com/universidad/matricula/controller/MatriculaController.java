package com.universidad.matricula.controller;


import com.universidad.matricula.model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.universidad.matricula.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/matricula", produces ="application/json")
public class MatriculaController {

  private final MatriculaRepository matriculaData;
  private final ResultadoMatriculaRepository resultadoMatriculaData;



public MatriculaController(MatriculaRepository matriculaData ,
ResultadoMatriculaRepository resultadoMatriculaData ){

  this.matriculaData = matriculaData;
  this.resultadoMatriculaData = resultadoMatriculaData;

 }

  
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<Integer> create(@RequestBody Matricula m){
      matriculaData.save(m);
      matriculaData.flush();
      Matricula generada = m;
        List<ResultadoMatricula> listItems = m.getResultadoMatriculas();
        listItems.stream().forEach(o -> o.setMatricula(generada));
        resultadoMatriculaData.saveAllAndFlush(listItems);
      return  new ResponseEntity<Integer>(m.getId(), HttpStatus.CREATED);

        
}

@GetMapping(value = "/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Matricula> findByNombre(@PathVariable String nombre){
    Optional<Matricula> optMatricula = matriculaData.findByNombre(nombre);
    if(optMatricula.isPresent()){
        Matricula matricula = optMatricula.get();
        List<ResultadoMatricula> resultadoMatriculas = resultadoMatriculaData.findItemsByMatricula(matricula);
        matricula.setResultadoMatriculas(resultadoMatriculas);
        return new ResponseEntity<Matricula>(matricula,HttpStatus.OK);
    }else{
        return new ResponseEntity<Matricula>(HttpStatus.NOT_FOUND);
    }

    
}
}

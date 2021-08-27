package com.universidad.matricula.model;

import javax.persistence.*;
import lombok.*;
import java.util.List;


@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_matricula")
public class Matricula {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String fechaNacimiento;
    private String genero;
    private String carrera;
    @Transient
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "matricula_id")
    private List<ResultadoMatricula> resultadoMatriculas;

    
}

package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nome) Like LOWER('%' :NOME'%'))")
    List<Paciente> buscarPaciente(@Param("NOME") String nome);

}

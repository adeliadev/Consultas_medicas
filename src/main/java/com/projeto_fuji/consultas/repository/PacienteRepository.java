package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.nome LIKE %:nome%")
    Optional<Paciente> buscarPaciente(@Param("nome") String nome);
}

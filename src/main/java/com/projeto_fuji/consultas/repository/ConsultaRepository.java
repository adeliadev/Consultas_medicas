package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
  @Query("SELECT c.Medico.nome, c.Paciente.nome FROM Consulta c")
  List<Object[]> buscarMedicoEPaciente(@Param("CONSULTA") String nome);
    

}

package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Query("SELECT m FROM Medico m WHERE LOWER(m.nome) Like LOWER(CONCAT('%', :NOME%'%'))")
    List<Medico> buscarMedico(@Param("NOME") String nome);

    @Query("SELECT m FROM Medico m WHERE m.especializacao Like %:ESPECIALIZACAO%")
    List<Medico> buscarEspecializacao(@Param("ESPECIALIZACAO") String especializacao);

}

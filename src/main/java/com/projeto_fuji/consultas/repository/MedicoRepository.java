package com.projeto_fuji.consultas.repository;

import com.projeto_fuji.consultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}

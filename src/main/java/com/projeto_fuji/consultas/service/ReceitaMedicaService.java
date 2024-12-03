package com.projeto_fuji.consultas.service;

import com.projeto_fuji.consultas.model.Paciente;
import com.projeto_fuji.consultas.model.ReceitaMedica;
import com.projeto_fuji.consultas.repository.ReceitaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaMedicaService {

    private final ReceitaMedicaRepository receitaMedicaRepository;

    public List<ReceitaMedica> retornarTodasReceitas() {return receitaMedicaRepository.findAll();
    }

    public Optional<ReceitaMedica> buscarReceita(Long id) {return receitaMedicaRepository.findById(id);
    }

    public ReceitaMedica criarReceita(ReceitaMedica receitaMedica) {
        ReceitaMedica novaReceitaMedica = new ReceitaMedica();
        novaReceitaMedica.setNomePaciente(receitaMedica.getNomePaciente());
        novaReceitaMedica.setNomeMedico(receitaMedica.getNomeMedico());
        novaReceitaMedica.setDescricaoMedicamentos(receitaMedica.getDescricaoMedicamentos());
        return receitaMedicaRepository.save(novaReceitaMedica);
    }

    public ReceitaMedica atualizarReceita(Long id, ReceitaMedica receitaDetalhes) {
        Optional<ReceitaMedica> receitaMedica = receitaMedicaRepository.findById(id);
        if (receitaMedica.isPresent()) {
            ReceitaMedica receitaAtualizado = receitaMedica.get();
            receitaAtualizado.setNomePaciente(receitaDetalhes.getNomePaciente());
            receitaAtualizado.setNomeMedico(receitaDetalhes.getNomeMedico());
            return receitaMedicaRepository.save(receitaAtualizado);
        } else {
            throw new RuntimeException("Receita Médica não encontrada!");
        }
    }

    public boolean deletarReceita(Long id) {
        if (receitaMedicaRepository.existsById(id)) {
            receitaMedicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

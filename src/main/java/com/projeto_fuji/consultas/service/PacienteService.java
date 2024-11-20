package com.projeto_fuji.consultas.service;

import com.projeto_fuji.consultas.model.Paciente;
import com.projeto_fuji.consultas.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<Paciente> retornarTodosPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente criarPaciente(Paciente paciente) {
        Paciente novoPaciente = new Paciente();
        novoPaciente.setNome(paciente.getNome());
        novoPaciente.setEmail(paciente.getEmail());
        return pacienteRepository.save(novoPaciente);
    }

    public Paciente atualizarPaciente(Long id, Paciente pacienteDetalhes) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            Paciente pacienteAtualizado = paciente.get();
            pacienteAtualizado.setNome(pacienteDetalhes.getNome());
            pacienteAtualizado.setEmail(pacienteDetalhes.getEmail());
            return pacienteRepository.save(pacienteAtualizado);
        } else {
            throw new RuntimeException("Paciente não encontrado!");
        }
    }

    public boolean deletarPaciente(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

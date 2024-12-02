package com.projeto_fuji.consultas.service;

import com.projeto_fuji.consultas.model.Consulta;
import com.projeto_fuji.consultas.model.Paciente;
import com.projeto_fuji.consultas.repository.ConsultaRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Autowired
    private EmailService emailService;

    public Consulta salvarConsulta(Consulta consulta) {

        Consulta consultaSalva = consultaRepository.save(consulta);

        Paciente paciente = consultaSalva.getPaciente();
        if (paciente != null && paciente.getEmail() != null) {
            emailService.enviarEmailTexto(
                    paciente.getEmail(),
                    "Confirmação de Consulta",
                    "Olá " + paciente.getNome() + ", sua consulta foi agendada com sucesso para " + consultaSalva.getDataConsulta()
            );
        }

        return consultaSalva;
    }

    public List<Consulta> buscarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
    }

    public Consulta atualizarConsulta(Long id, Consulta consultaAtualizada) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));

        consulta.setDescricao(consultaAtualizada.getDescricao());
        consulta.setDataConsulta(consultaAtualizada.getDataConsulta());
        consulta.setStatus(consultaAtualizada.getStatus());

        Consulta consultaSalva = consultaRepository.save(consulta);

        Paciente paciente = consultaSalva.getPaciente();
        if (paciente != null && paciente.getEmail() != null) {
            emailService.enviarEmailTexto(
                    paciente.getEmail(),
                    "Atualização de Consulta",
                    "Olá " + paciente.getNome() + ", sua consulta foi atualizada para " + consultaSalva.getDataConsulta()
            );
        }

        return consultaSalva;
    }

    public void deletarConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada!");
        }
        consultaRepository.deleteById(id);
    }
}

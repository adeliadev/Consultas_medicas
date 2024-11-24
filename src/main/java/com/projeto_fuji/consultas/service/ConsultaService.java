package com.projeto_fuji.consultas.service;

import com.projeto_fuji.consultas.model.Consulta;
import com.projeto_fuji.consultas.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public Consulta salvarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
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

        return consultaRepository.save(consulta);
    }

    public void deletarConsulta(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada!");
        }
        consultaRepository.deleteById(id);
    }
}

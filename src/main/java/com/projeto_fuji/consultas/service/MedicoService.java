package com.projeto_fuji.consultas.service;

import com.projeto_fuji.consultas.model.Medico;
import com.projeto_fuji.consultas.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public List<Medico> retornarTodosMedicos() { return medicoRepository.findAll();}

    public Optional<Medico> buscarMedico(Long id) { return medicoRepository.findById(id);}

    public Medico criarMedico(Medico medico){
        Medico novoMedico = new Medico();
        novoMedico.setNome(medico.getNome());
        novoMedico.setEspecializacao(medico.getEspecializacao());
        novoMedico.setConsultorio(medico.getConsultorio());
        return medicoRepository.save(novoMedico);
    }

    public Medico atualizarMedico(Long id, Medico medicoDetalhes){
        Optional<Medico> medico = medicoRepository.findById(id);
        if(medico.isPresent()){
            Medico medicoAtualizado = medico.get();
            medicoAtualizado.setNome(medicoDetalhes.getNome());
            medicoAtualizado.setEspecializacao(medicoDetalhes.getEspecializacao());
            medicoAtualizado.setConsultorio(medicoDetalhes.getConsultorio());
            return medicoRepository.save(medicoAtualizado);
        }
        else{
            throw new RuntimeException("Medico não encontrado!");
        }
    }

    public boolean deletarMedico(Long id) {
        if (medicoRepository.existsById(id)){
            medicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


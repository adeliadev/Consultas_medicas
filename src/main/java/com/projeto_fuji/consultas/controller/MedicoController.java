package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.MedicoNotFoundException;
import com.projeto_fuji.consultas.model.Medico;
import com.projeto_fuji.consultas.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> retornarTodosMedicos() {
        List<Medico> medicos = medicoService.retornarTodosMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable Long id) {
        return medicoService.buscarMedico(id)
                .map(ResponseEntity::ok)
                .orElseThrow(MedicoNotFoundException::new);
    }

    @PostMapping("/criar")
    public ResponseEntity<Medico> criarMedico(@RequestBody Medico medico) {
        Medico novoMedico = medicoService.criarMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedico);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable Long id, @RequestBody Medico medicoDetalhes) {
        try {
            Medico medicoAtualizado = medicoService.atualizarMedico(id, medicoDetalhes);
            return ResponseEntity.ok(medicoAtualizado);
        } catch (MedicoNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        boolean deletado = medicoService.deletarMedico(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            throw new MedicoNotFoundException();
        }
    }
}

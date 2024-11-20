package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.EmailAlreadyBeingUsedException;
import com.projeto_fuji.consultas.exception.PacientNotFoundException;
import com.projeto_fuji.consultas.model.Paciente;
import com.projeto_fuji.consultas.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public List<Paciente> retornarTodosPacientes() {
        return pacienteService.retornarTodosPacientes();
    }

    @GetMapping("/buscar/{id}")
    public Optional<Paciente> buscarPaciente(@PathVariable Long id) {
        return pacienteService.buscarPaciente(id);
    }

    @PostMapping("/criar")
    public ResponseEntity<Paciente> criarPaciente(@RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.criarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Paciente> atualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetalhes) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, pacienteDetalhes);
            return ResponseEntity.ok(pacienteAtualizado);
        } catch (PacientNotFoundException e) {
            throw new PacientNotFoundException();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {

        boolean deletado = pacienteService.deletarPaciente(id);
        if (deletado) {
            return ResponseEntity.ok().build();

        } else {
            throw new EmailAlreadyBeingUsedException();
        }
    }

}

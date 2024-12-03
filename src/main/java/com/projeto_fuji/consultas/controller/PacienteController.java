package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.EmailAlreadyBeingUsedException;
import com.projeto_fuji.consultas.exception.PacientNotFoundException;
import com.projeto_fuji.consultas.model.Paciente;
import com.projeto_fuji.consultas.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;
    private final View error;

    @GetMapping
    public List<Paciente> retornarTodosPacientes() {
        return pacienteService.retornarTodosPacientes();
    }


    @Operation(description = "Busca o paciente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o paciente por id"),
            @ApiResponse(responseCode = "400", description = "Não existe o paciente com o id informado")
    })
    @GetMapping("/buscar/{id}")
    public Optional<Paciente> buscarPaciente(@PathVariable Long id) {
        return pacienteService.buscarPaciente(id);
    }


    @Operation(description = "Cadastrar de um novo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente criado"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar paciente")
    })
    @PostMapping("/criar")
    public ResponseEntity<Paciente> criarPaciente(@RequestBody @Valid Paciente paciente) {
        Paciente novoPaciente = pacienteService.criarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }


    @Operation(description = "Atualizando dados de pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado dados de paciente"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar dados de paciente")
    })
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


    @Operation(description = "Exclui paciente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente exluido"),
            @ApiResponse(responseCode = "400", description = "Erro ao excluir paciente")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {

        boolean deletado = pacienteService.deletarPaciente(id);
        if (deletado) {
            return ResponseEntity.ok().build();

        } else {
            throw new EmailAlreadyBeingUsedException();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidetionException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

                errors.put(fieldName, errorMessage);
                });

            return errors;
    }
}
package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.MedicoNotFoundException;
import com.projeto_fuji.consultas.model.Medico;
import com.projeto_fuji.consultas.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(description = "Busca o médico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o médico por id"),
            @ApiResponse(responseCode = "400", description = "Não existe o médico com o id informado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Medico> buscarMedico(@PathVariable Long id) {
        return medicoService.buscarMedico(id)
                .map(ResponseEntity::ok)
                .orElseThrow(MedicoNotFoundException::new);
    }


    @Operation(description = "Cadastrar de um novo médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico criado"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar médico")
    })
    @PostMapping("/criar")
    public ResponseEntity<Medico> criarMedico(@RequestBody Medico medico) {
        Medico novoMedico = medicoService.criarMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMedico);
    }


    @Operation(description = "Atualizando dados de médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado dados de médico"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar dados de médico")
    })
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


    @Operation(description = "Exclui médico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medico exluido"),
            @ApiResponse(responseCode = "400", description = "Erro ao excluir médico")
    })
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

package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.ConsultaNotFoundException;
import com.projeto_fuji.consultas.exception.MedicoNotFoundException;
import com.projeto_fuji.consultas.model.Consulta;
import com.projeto_fuji.consultas.model.Medico;
import com.projeto_fuji.consultas.model.Paciente;
import com.projeto_fuji.consultas.service.ConsultaService;
import com.projeto_fuji.consultas.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;


    @Operation(description = "Cria uma nova consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta criado"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar consulta")
    })
    @PostMapping("/criando_consulta")
    public Consulta criarConsulta(@RequestBody Consulta consulta) {
        return consultaService.salvarConsulta(consulta);
    }

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.buscarTodas();
    }


    @Operation(description = "Busca o médico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o médico por id"),
            @ApiResponse(responseCode = "400", description = "Não existe o médico com o id informado")
    })
    @GetMapping("/buscar/{id}")
    public Consulta buscarConsultaPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }


    @Operation(description = "Atualizando dados da consulta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados da consulta atualizado"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar dados da consulta")
    })
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(@PathVariable Long id, @RequestBody Consulta consultaDetalhes) {
        try {
            Consulta consultaAtualizado = consultaService.atualizarConsulta(id, consultaDetalhes);
            return ResponseEntity.ok(consultaAtualizado);
        } catch (ConsultaNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Operation(description = "Exclui consulta por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exluida"),
            @ApiResponse(responseCode = "400", description = "Erro ao excluir consulta")
    })
    @DeleteMapping("deletar/{id}")
    public void deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
    }
}

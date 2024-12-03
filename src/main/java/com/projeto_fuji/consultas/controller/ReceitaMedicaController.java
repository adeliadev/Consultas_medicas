package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.ReceitaMedicaNotFoundException;
import com.projeto_fuji.consultas.model.ReceitaMedica;
import com.projeto_fuji.consultas.service.ReceitaMedicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaMedicaController {
    private final ReceitaMedicaService receitaMedicaService;

    @GetMapping
    public List<ReceitaMedica> retornarTodasReceitas() {return receitaMedicaService.retornarTodasReceitas();}


    @Operation(description = "Busca o receita médica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o receita médica por id"),
            @ApiResponse(responseCode = "400", description = "Não existe a receita médica com o id informado")
    })
    @GetMapping("/buscar/{id}")
    public Optional<ReceitaMedica> buscarReceita(@PathVariable Long id){return receitaMedicaService.buscarReceita(id);}


    @Operation(description = "Cria uma nova receita médica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita médica criada"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar receita médica")
    })
    @PostMapping("/criar")
    public ResponseEntity<ReceitaMedica> criarReceita(@RequestBody ReceitaMedica receitaMedica){
        ReceitaMedica novaReceita = receitaMedicaService.criarReceita(receitaMedica);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
    }


    @Operation(description = "Atualizando dados da receita médica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados da receita médica atualizado"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar dados da receita médica")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ReceitaMedica> atualizarReceita(@PathVariable Long id, @RequestBody ReceitaMedica receitaDetalhes){
        try{
            ReceitaMedica receitaAtualizada = receitaMedicaService.atualizarReceita(id, receitaDetalhes);
            return ResponseEntity.ok(receitaAtualizada);
        } catch (ReceitaMedicaNotFoundException e) {
            throw new ReceitaMedicaNotFoundException();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @Operation(description = "Exclui receita médica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receita médica exluida"),
            @ApiResponse(responseCode = "400", description = "Erro ao excluir receita médica")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id){
        boolean deletado = receitaMedicaService.deletarReceita(id);
        if (deletado){
            return ResponseEntity.ok().build();

        } else {
            throw new ReceitaMedicaNotFoundException();
        }
    }

}
package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.model.Consulta;
import com.projeto_fuji.consultas.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public Consulta criarConsulta(@RequestBody Consulta consulta) {
        return consultaService.salvarConsulta(consulta);
    }

    @GetMapping
    public List<Consulta> listarConsultas() {
        return consultaService.buscarTodas();
    }

    @GetMapping("/{id}")
    public Consulta buscarConsultaPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Consulta atualizarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        return consultaService.atualizarConsulta(id, consulta);
    }

    @DeleteMapping("/{id}")
    public void deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
    }
}

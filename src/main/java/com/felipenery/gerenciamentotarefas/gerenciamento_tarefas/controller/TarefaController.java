package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.controller;

import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")

public class TarefaController {

    private final TarefaService tarefaService;
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public List<Tarefa> listarTodas(
            @RequestParam(value ="status", required = false) String status,
            @RequestParam(value = "ordenarPor", required = false) String ordenarPor
    ){
        return tarefaService.listarTodas(status,ordenarPor);
    }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return tarefaService.criar(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable UUID id ,@RequestBody Tarefa tarefaEdidata){
        return tarefaService.atualizar(id, tarefaEdidata);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id){
        boolean deletado = tarefaService.deletar(id);

    if(deletado){
        return ResponseEntity.noContent().build();
    }else {
        return ResponseEntity.notFound().build();
      }
    }
}

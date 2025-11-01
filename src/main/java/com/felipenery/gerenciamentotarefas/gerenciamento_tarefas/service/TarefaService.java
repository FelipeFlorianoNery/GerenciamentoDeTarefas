package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.service;

import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;

@Service

public class TarefaService {

        private final TarefaRepository tarefaRepository;

        public TarefaService(TarefaRepository tarefaRepository) {
            this.tarefaRepository = tarefaRepository;
        }

    public Tarefa criar(Tarefa novaTarefa){
            novaTarefa.setId(UUID.randomUUID());
            novaTarefa.setDataDeCriacao(LocalDateTime.now());
            return tarefaRepository.save(novaTarefa);
        }

    public Tarefa atualizar(UUID id, Tarefa tarefaEditada) {
        Optional<Tarefa> tarefaExistenteOpt = tarefaRepository.findById(id);

        if (tarefaExistenteOpt.isEmpty()) {
            return null; // Ou lançar uma exceção
        }

        Tarefa tarefaExistente = tarefaExistenteOpt.get();
        tarefaExistente.setTitulo(tarefaEditada.getTitulo());
        tarefaExistente.setDescricao(tarefaEditada.getDescricao());
        tarefaExistente.setStatus(tarefaEditada.getStatusParaJson());

        return tarefaRepository.save(tarefaExistente);
        }

    public boolean deletar(UUID id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public List<Tarefa> listarTodas(String status,  String ordenarPor){
            List<Tarefa> tarefas = this.tarefaRepository.findAll();
            Stream<Tarefa> tarefasAtuais = tarefas.stream();

            if(status != null && !status.isBlank()){
                tarefasAtuais = tarefasAtuais.filter(
                        tarefa -> tarefa.getStatusParaJson().equalsIgnoreCase(status));
            }

            if ("status".equalsIgnoreCase(ordenarPor)) {
                tarefasAtuais = tarefasAtuais.sorted(Comparator.comparing(Tarefa::getStatusParaJson));
            } else if ("dataDeCriação".equalsIgnoreCase(ordenarPor)){
                tarefasAtuais = tarefasAtuais.sorted(Comparator.comparing(Tarefa::getDataDeCriacao));
            }
            return tarefasAtuais.collect(Collectors.toList());
    }
}

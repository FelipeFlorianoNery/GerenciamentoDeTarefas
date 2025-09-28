package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.service;

import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service

public class TarefaService {

        private final TarefaRepository tarefaRepository;

        public TarefaService(TarefaRepository tarefaRepository) {
            this.tarefaRepository = tarefaRepository;
        }

        public Tarefa criar(Tarefa novaTarefa){
            novaTarefa.setId(UUID.randomUUID());
            novaTarefa.setDataDeCriacao(LocalDateTime.now());
            List<Tarefa> tarefasAtuais = this.tarefaRepository.encontrarTodas();
            tarefasAtuais.add(novaTarefa);
            this.tarefaRepository.salvarTodas(tarefasAtuais);
            return novaTarefa;
        }

    public Tarefa atualizar(UUID id, Tarefa tarefaEditada) {
        List<Tarefa> tarefasAtuais = this.tarefaRepository.encontrarTodas();
        for (Tarefa tarefa : tarefasAtuais) {
            if (tarefa.getId().equals(id)) {
                tarefa.setTitulo(tarefaEditada.getTitulo());
                tarefa.setDescricao(tarefaEditada.getDescricao());
                tarefa.setStatus(tarefaEditada.getStatusParaJson());
                this.tarefaRepository.salvarTodas(tarefasAtuais);
                return tarefa;
            }
        }
        return null;
    }

    public boolean deletar(UUID id) {
            List<Tarefa> tarefaAtuais = this.tarefaRepository.encontrarTodas();

            boolean tarefaDeletada = tarefaAtuais.
                    removeIf(tarefa -> tarefa.getId().equals(id));
            if (tarefaDeletada) {
                this.tarefaRepository.salvarTodas(tarefaAtuais);
            }
            return tarefaDeletada;
    }

    public List<Tarefa> listarTodas(){
            return this.tarefaRepository.encontrarTodas();
    }
}

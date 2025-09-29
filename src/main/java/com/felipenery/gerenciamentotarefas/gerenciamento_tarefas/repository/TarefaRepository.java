package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class TarefaRepository {

    private static final String CAMINHO_ARQUIVO = "tarefas.json";
    private final ObjectMapper objectMapper;

    public TarefaRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Tarefa> encontrarTodas(){
try{
    return objectMapper.readValue(new File(CAMINHO_ARQUIVO),
            new TypeReference<List<Tarefa>>() {});

    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
}

    public void salvarTodas(List<Tarefa> tarefas){
    try{
        objectMapper.writeValue(new File(CAMINHO_ARQUIVO), tarefas);
    }catch (IOException e){
        e.printStackTrace();
    }
    }
}
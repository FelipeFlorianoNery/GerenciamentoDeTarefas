package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefas;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class TarefasRepository {

    private static final String CAMINHO_ARQUIVO = "tarefas.json";
    private final ObjectMapper objectmapper = new ObjectMapper();

    public List<Tarefas> visualizarTarefas(){

    }
        return new ArrayList<>();
}

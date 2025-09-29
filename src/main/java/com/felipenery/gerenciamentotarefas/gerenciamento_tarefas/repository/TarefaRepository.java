package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TarefaRepository {

    private final String caminhoArquivo;
    private final ObjectMapper objectMapper;

    public TarefaRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        String pathFromEnv = System.getenv("DATA_FILE_PATH");
        if (pathFromEnv != null && !pathFromEnv.isBlank()) {
            this.caminhoArquivo = pathFromEnv;
        } else {
            this.caminhoArquivo = "tarefas.json";
        }
    }

    public List<Tarefa> encontrarTodas() {
        File file = new File(this.caminhoArquivo);
        if (!file.exists()) {
            return new ArrayList<>(); // Retorna lista vazia se o arquivo não existe
        }

        try {
            return objectMapper.readValue(file, new TypeReference<List<Tarefa>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvarTodas(List<Tarefa> tarefas) {
        try {
            Path path = Paths.get(this.caminhoArquivo);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            objectMapper.writeValue(new File(this.caminhoArquivo), tarefas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
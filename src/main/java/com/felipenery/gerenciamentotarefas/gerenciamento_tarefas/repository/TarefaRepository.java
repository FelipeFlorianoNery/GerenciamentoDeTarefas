package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository;

import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository; // Importar
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {

}
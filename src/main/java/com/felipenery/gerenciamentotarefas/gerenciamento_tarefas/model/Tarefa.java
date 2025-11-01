package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tarefas")

public class Tarefa {

    @Id
    private UUID id;

    private String titulo;
    private String descricao;
    private boolean concluida;

    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm:ss")
    private LocalDateTime dataDeCriacao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public void setStatus(String statusTexto) {
        this.concluida = "Concluído".equalsIgnoreCase(statusTexto) || "Concluido".equalsIgnoreCase(statusTexto);
    }

    @JsonProperty("status")
    public String getStatusParaJson() {
        return this.concluida ? "Concluído" : "Pendente";
    }

    @JsonIgnore
    public boolean isConcluida() {
        return concluida;
    }
}


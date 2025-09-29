package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.service;

import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTeste {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefaExemplo;

    @BeforeEach
    void setUp() {
        tarefaExemplo = new Tarefa();
        tarefaExemplo.setId(UUID.randomUUID());
        tarefaExemplo.setTitulo("Tarefa Original");
        tarefaExemplo.setDescricao("Descrição original da tarefa.");
        tarefaExemplo.setDataDeCriacao(LocalDateTime.now());
        tarefaExemplo.setStatus("Pendente");
    }

    @Test
    @DisplayName("Deve criar uma nova tarefa com sucesso")
    void deveCriarTarefa() {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo("Criando API de Tarefas");
        novaTarefa.setDescricao("Usando o que estou aprendendo e colocando em prática novos conhecimentos");

        when(tarefaRepository.encontrarTodas()).thenReturn(new ArrayList<>());

        Tarefa tarefaCriada = tarefaService.criar(novaTarefa);

        assertNotNull(tarefaCriada);
        assertNotNull(tarefaCriada.getId());
        assertNotNull(tarefaCriada.getDataDeCriacao());
        assertEquals("Criando API de Tarefas", tarefaCriada.getTitulo());
        assertEquals("Pendente", tarefaCriada.getStatusParaJson());

        ArgumentCaptor<List<Tarefa>> captorDeLista = ArgumentCaptor.forClass(List.class);
        verify(tarefaRepository, times(1)).salvarTodas(captorDeLista.capture());
        List<Tarefa> listaSalva = captorDeLista.getValue();

        assertEquals(1, listaSalva.size());
        assertEquals(tarefaCriada, listaSalva.get(0));
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa existente com sucesso")
    void deveAtualizarTarefa() {
        List<Tarefa> tarefasAtuais = new ArrayList<>();
        tarefasAtuais.add(tarefaExemplo);

        when(tarefaRepository.encontrarTodas()).thenReturn(tarefasAtuais);

        Tarefa tarefaEditada = new Tarefa();
        tarefaEditada.setTitulo("Título Atualizado");
        tarefaEditada.setDescricao("Descrição atualizada.");
        tarefaEditada.setStatus("Concluído");

        Tarefa tarefaRetornada = tarefaService.atualizar(tarefaExemplo.getId(), tarefaEditada);

        assertNotNull(tarefaRetornada);
        assertEquals(tarefaExemplo.getId(), tarefaRetornada.getId()); // O ID não deve mudar
        assertEquals("Título Atualizado", tarefaRetornada.getTitulo());
        assertEquals("Descrição atualizada.", tarefaRetornada.getDescricao());
        assertEquals("Concluído", tarefaRetornada.getStatusParaJson());

        ArgumentCaptor<List<Tarefa>> captorDeLista = ArgumentCaptor.forClass(List.class);
        verify(tarefaRepository, times(1)).salvarTodas(captorDeLista.capture());
        List<Tarefa> listaSalva = captorDeLista.getValue();

        assertEquals(1, listaSalva.size());
        assertEquals("Título Atualizado", listaSalva.get(0).getTitulo());
    }
}
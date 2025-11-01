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
import java.util.Optional;
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

        when(tarefaRepository.save(any(Tarefa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Tarefa tarefaCriada = tarefaService.criar(novaTarefa);

        assertNotNull(tarefaCriada);
        assertNotNull(tarefaCriada.getId());
        assertNotNull(tarefaCriada.getDataDeCriacao());
        assertEquals("Criando API de Tarefas", tarefaCriada.getTitulo());
        assertEquals("Pendente", tarefaCriada.getStatusParaJson());

        ArgumentCaptor<Tarefa> captorDeTarefa = ArgumentCaptor.forClass(Tarefa.class);

        verify(tarefaRepository, times(1)).save(captorDeTarefa.capture());
        Tarefa tarefaSalva = captorDeTarefa.getValue();

        assertEquals(1, 1);
        assertEquals(tarefaCriada, tarefaSalva);
        assertEquals("Criando API de Tarefas", tarefaSalva.getTitulo());
    }

    @Test
    @DisplayName("Deve atualizar uma tarefa existente com sucesso")
    void deveAtualizarTarefa() {
        when(tarefaRepository.findById(tarefaExemplo.getId())).thenReturn(Optional.of(tarefaExemplo));

        when(tarefaRepository.save(any(Tarefa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Tarefa tarefaEditada = new Tarefa();
        tarefaEditada.setTitulo("Título Atualizado");
        tarefaEditada.setDescricao("Descrição atualizada.");
        tarefaEditada.setStatus("Concluído");

        Tarefa tarefaRetornada = tarefaService.atualizar(tarefaExemplo.getId(), tarefaEditada);

        assertNotNull(tarefaRetornada);
        assertEquals(tarefaExemplo.getId(), tarefaRetornada.getId());
        assertEquals("Título Atualizado", tarefaRetornada.getTitulo());
        assertEquals("Descrição atualizada.", tarefaRetornada.getDescricao());
        assertEquals("Concluído", tarefaRetornada.getStatusParaJson());

        ArgumentCaptor<Tarefa> captorDeTarefa = ArgumentCaptor.forClass(Tarefa.class);
        verify(tarefaRepository, times(1)).save(captorDeTarefa.capture());
        Tarefa listaSalva = captorDeTarefa.getValue();

        assertEquals(1, 1);
        assertEquals("Título Atualizado", listaSalva.getTitulo());
    }

    @Test
    @DisplayName("Deve deletar uma tarefa com sucesso")
    void deveDeletarTarefa() {
        UUID idParaDeletar = tarefaExemplo.getId();

        when(tarefaRepository.existsById(idParaDeletar)).thenReturn(true);

        boolean deletado = tarefaService.deletar(idParaDeletar);

        assertTrue(deletado);

        verify(tarefaRepository, times(1)).deleteById(idParaDeletar);
    }

    @Test
    @DisplayName("Não deve deletar tarefa inexistente")
    void naoDeveDeletarTarefaInexistente() {
        UUID idInexistente = UUID.randomUUID();

        when(tarefaRepository.existsById(idInexistente)).thenReturn(false);

        boolean deletado = tarefaService.deletar(idInexistente);

        assertFalse(deletado);

        verify(tarefaRepository, never()).deleteById(idInexistente);
    }
}
package com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.service;

import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.model.Tarefa;
import com.felipenery.gerenciamentotarefas.gerenciamento_tarefas.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTeste {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void criarTarefa() {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo("Criando Software de Tarefas");
        novaTarefa.setDescricao("Usando o conhecimento que tem e aprendendo novos conhecimentos");

        when(tarefaRepository.encontrarTodas()).thenReturn(new ArrayList<>());

        Tarefa tarefaCriada = tarefaService.criar(novaTarefa);

        assertNotNull(tarefaCriada);
        assertNotNull(tarefaCriada.getId());
        assertNotNull(tarefaCriada.getDescricao());

        assertEquals("Criando Software de Tarefas", tarefaCriada.getTitulo());

        ArgumentCaptor<List<Tarefa>> captorDeLista = ArgumentCaptor.forClass(List.class);

        verify(tarefaRepository, times(1)).salvarTodas(captorDeLista.capture());

        List<Tarefa> listaSalva = captorDeLista.getValue();

        assertEquals(1, listaSalva.size());

        assertEquals(tarefaCriada, listaSalva.get(0));
    }


}

package org.esig.controller;

import org.esig.model.Tarefa;
import org.esig.service.TarefaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class TarefaBeanTest {

    @InjectMocks
    private TarefaBean tarefaBean;

    @Mock
    private TarefaService tarefaService;

    private List<Tarefa> tarefas;

    @BeforeEach
    void setUp() {
        tarefas = new ArrayList<>();
        when(tarefaService.listar()).thenReturn(tarefas);
    }

    @Test
    void testSalvar() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Nova Tarefa");
        tarefaBean.setTarefa(tarefa);

        tarefaBean.salvar();

        verify(tarefaService, times(1)).salvar(tarefa);

       
        tarefas.add(tarefa);
        
        
        when(tarefaService.listar()).thenReturn(tarefas);

        assertEquals(1, tarefaBean.getTarefas().size());
        assertEquals("Nova Tarefa", tarefaBean.getTarefas().get(0).getTitulo());
    }

    @Test
    void testRemover() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Tarefa para Remover");
        tarefaBean.setTarefa(tarefa1);
        tarefaBean.salvar();

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Outra Tarefa");
        tarefaBean.setTarefa(tarefa2);
        tarefaBean.salvar();

        tarefas.add(tarefa1);
        tarefas.add(tarefa2);

       
        when(tarefaService.listar()).thenReturn(tarefas);

        tarefaBean.remover(tarefa1);

        verify(tarefaService, times(1)).remover(tarefa1);

        tarefas.remove(tarefa1);

        
        when(tarefaService.listar()).thenReturn(tarefas);

        assertEquals(1, tarefaBean.getTarefas().size());
        assertEquals("Outra Tarefa", tarefaBean.getTarefas().get(0).getTitulo());
    }
}

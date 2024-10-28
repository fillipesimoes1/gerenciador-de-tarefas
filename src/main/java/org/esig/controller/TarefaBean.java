package org.esig.controller;

import org.esig.model.Tarefa;
import org.esig.service.TarefaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("tarefaBean")
@RequestScoped
public class TarefaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TarefaService tarefaService;

    private Tarefa tarefa = new Tarefa();

    
    private List<Tarefa> tarefas;

  
    public List<Tarefa> getTarefas() {
        if (tarefas == null) {
            tarefas = tarefaService.listar(); 
        }
        return tarefas;
    }

    public void salvar() {
        tarefaService.salvar(tarefa);
        tarefa = new Tarefa(); 
        tarefas = tarefaService.listar();
    }

    public void remover(Tarefa tarefa) {
        tarefaService.remover(tarefa);
        tarefas = tarefaService.listar(); 
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}

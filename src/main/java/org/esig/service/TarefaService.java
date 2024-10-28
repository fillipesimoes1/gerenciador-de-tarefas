package org.esig.service;

import org.esig.model.Tarefa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TarefaService {

    @Inject
    private EntityManager em;

    @Transactional
    public void salvar(Tarefa tarefa) {
        if (tarefa.getId() == null) {
            em.persist(tarefa);
        } else {
            em.merge(tarefa);
        }
    }

    public List<Tarefa> listar() {
        return em.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
    }

    @Transactional
    public void remover(Tarefa tarefa) {
        em.remove(em.contains(tarefa) ? tarefa : em.merge(tarefa));
    }
}

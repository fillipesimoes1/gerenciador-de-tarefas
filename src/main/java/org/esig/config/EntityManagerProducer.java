package org.esig.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "TaskManagerPU")
    private EntityManager em;

    @Produces
    public EntityManager produceEntityManager() {
        return em;
    }
}


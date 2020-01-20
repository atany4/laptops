package io.laptops.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryManager {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("laptopsjpa");
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
}

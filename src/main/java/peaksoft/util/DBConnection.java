package peaksoft.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBConnection {
    static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("peaksoft");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}

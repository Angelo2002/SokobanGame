package com.mycompany.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {

    private static final EntityManagerHelper SINGLENTON = new EntityManagerHelper();
    private static EntityManagerFactory emf;
    private static EntityManager em;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("com.mycompany_SokobanPiloto_jar_1.0-SNAPSHOTPU");
            em = emf.createEntityManager();
        } catch (ExceptionInInitializerError e) {
            throw e;
        }
    }

    public static EntityManagerHelper getInstance() {
        return SINGLENTON;
    }

    public static EntityManager getManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("com.mycompany_SokobanPiloto_jar_1.0-SNAPSHOTPU");
            em = emf.createEntityManager();
        }
        return em;
    }


}
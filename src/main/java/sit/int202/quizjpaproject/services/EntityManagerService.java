package sit.int202.quizjpaproject.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerService {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default") ;

    public static EntityManager getEntityManager(){
        return emf.createEntityManager() ;
    }
}



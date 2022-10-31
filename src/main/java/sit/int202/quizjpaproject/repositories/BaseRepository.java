package sit.int202.quizjpaproject.repositories;

import jakarta.persistence.*;
import sit.int202.quizjpaproject.services.EntityManagerService;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseRepository<T,ID> {

    private String entityName ;
    private Class<T> persistentClass ;

    public BaseRepository(){
        this.persistentClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0] ;
        this.entityName = this.persistentClass.getSimpleName() ;
    }

    public BaseRepository(String entityName) {
        this() ;
        this.entityName = entityName ;
    }

    public EntityManager getEntityManager(){
        return EntityManagerService.getEntityManager();
    }

    public String getEntityName(){
        return this.entityName ;
    }

    public void setEntityName(String entityName){
        this.entityName = entityName ;
    }

    public T find(Class<T> aClass, ID id){
        EntityManager entityManager = this.getEntityManager() ;
        T t = entityManager.find(aClass, id);
        entityManager.close();
        return t ;
    }

    public T find(ID id){
        return this.find(this.persistentClass,id) ;
    }

    public List<T> findAll(){
        EntityManager entityManager = this.getEntityManager() ;
        List<T> resultList = entityManager.createQuery("SELECT e FROM "+entityName+" e").getResultList() ;
        entityManager.close();
        return resultList ;
    }

    public List<T> getResultListFromNamedQuery(String namedQuery){
        EntityManager entityManager = this.getEntityManager() ;
        Query query = entityManager.createNamedQuery(namedQuery) ;
        List<T> resultList = query.getResultList() ;
        entityManager.close();
        return resultList ;
    }

    public List<T> getResultListFromNamedQueryWithParam(String namedQuery,Object param){
        EntityManager entityManager = this.getEntityManager() ;
        Query query = entityManager.createNamedQuery(namedQuery).setParameter("param",param) ;
        List<T> resultList = query.getResultList() ;
        entityManager.close();
        return resultList ;
    }

    public List<T> getResultListFromQuery(String statement){
        EntityManager entityManager = this.getEntityManager() ;
        Query query  = entityManager.createQuery(statement) ;
        List<T> resultList = query.getResultList() ;
        entityManager.close();
        return resultList ;
    }

    public int countRows(){
        EntityManager entityManager = this.getEntityManager() ;
        Query query = entityManager.createQuery("SELECT COUNT(e) FROM "+entityName+" e") ;
        int countAll = ( (Number) query.getSingleResult() ).intValue() ;
        entityManager.close() ;
        return countAll ;
    }

    public boolean save(T t){
        try{
            EntityManager entityManager = getEntityManager() ;
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e){
            System.out.println("ERROR : " +e.getMessage());
            return  false ;
        }
        return true ;
    }

    public boolean update(T t){
        try{
            EntityManager entityManager = getEntityManager() ;
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e){
            System.out.println("ERROR : " +e.getMessage());
            return  false ;
        }
        return true ;
    }

    public boolean delete(Class<T> aClass, ID id){
        try{
            EntityManager entityManager = getEntityManager() ;
            entityManager.getTransaction().begin();
            T t = entityManager.find(aClass,id) ;
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e){
            System.out.println("ERROR : " +e.getMessage());
            return  false ;
        }
        return true ;
    }

    public boolean delete(ID id){
        return this.delete(this.persistentClass,id) ;
    }
}






















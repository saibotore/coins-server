package de.tgehring.coins.rest.dao;

import de.tgehring.coins.general.Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GenericDao<T extends Entity> {

    private static GenericDao instance = null;
    private static final String PERSISTENCE_UNIT_NAME = "coins-persistence";
    private static EntityManagerFactory factory;
    private EntityManager em;

    /**
     * Class constructor.
     */
    private GenericDao() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static GenericDao getInstance() {
        if(instance == null) {
            instance = new GenericDao();
        }

        return instance;
    }

    public void create(T entity) {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public Entity read(long id) {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        Entity entity = em.find(Entity.class, id);
        em.getTransaction().commit();
        em.close();

        return entity;
    }

    public List<Entity> read(String query) {
        return em.createNamedQuery(query).getResultList();
    }

    public void update(Entity entity) {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id) {
        Entity entity = em.find(Entity.class, id);
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }


}

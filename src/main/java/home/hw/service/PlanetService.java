package home.hw.service;

import home.hw.entity.Planet;
import home.hw.exception.InvalidDataEntityException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PlanetService implements Crudable<Planet, String> {
    private final SessionFactory sessionFactory;

    public PlanetService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(Planet planet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
        session.close();
        return planet.getId();
    }

    @Override
    public void update(Planet planet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(planet);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Planet planet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(planet);
        transaction.commit();
        session.close();
    }

    @Override
    public Planet getById(String id) {
        Session session = sessionFactory.openSession();
        Planet planet = session.get(Planet.class, id);
        session.close();
        return planet;
    }

    @Override
    public List<Planet> getAll() {
        Session session = sessionFactory.openSession();
        List<Planet> planets = session.createQuery("from " + Planet.class.getName(), Planet.class).list();
        session.close();
        return planets;
    }

    @Override
    public void isValidEntity(Planet planet) throws InvalidDataEntityException {
        List<String> errorItem = new ArrayList<>();
        if (planet.getName().isEmpty()) errorItem.add("Name");
        if (planet.getId().isEmpty()) errorItem.add("Id");

        if (!errorItem.isEmpty()) {
            throw new InvalidDataEntityException("Not filled " + String.join(", ", errorItem));
        }
    }
}

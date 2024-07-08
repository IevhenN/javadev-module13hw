package home.hw.service;

import home.hw.entity.Client;
import home.hw.exception.InvalidDataEntityException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ClientService implements Crudable<Client, Long> {
    private final SessionFactory sessionFactory;

    public ClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(client);
        transaction.commit();
        session.close();
        return client.getId();

    }

    @Override
    public void update(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(client);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(client);
        transaction.commit();
        session.close();
    }

    @Override
    public Client getById(Long id) {
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    @Override
    public List<Client> getAll() {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from " + Client.class.getName(), Client.class).list();
        session.close();
        return clients;
    }

    @Override
    public void isValidEntity(Client client) throws InvalidDataEntityException {
        if (client.getName().isEmpty()) throw new InvalidDataEntityException("Not filled name");
    }

    public Client getByName(String name) {
        Session session = sessionFactory.openSession();
        Query<Client> query = session.createQuery("from Client where name=:name", Client.class);
        query.setParameter("name", name);
        Client client = query.uniqueResult();
        session.close();

        return client;
    }
}

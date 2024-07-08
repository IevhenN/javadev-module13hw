package home.hw.service;

import home.hw.entity.Client;
import home.hw.entity.Ticket;
import home.hw.exception.InvalidDataEntityException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TicketService implements Crudable<Ticket, Long> {
    private final SessionFactory sessionFactory;

    public TicketService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(Ticket ticket) throws InvalidDataEntityException {
        isValidEntity(ticket);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
        return ticket.getId();
    }

    @Override
    public void update(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public Ticket getById(Long id) {
        Session session = sessionFactory.openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        Session session = sessionFactory.openSession();
        List<Ticket> tickets = session.createQuery("from " + Ticket.class.getName(), Ticket.class).list();
        session.close();
        return tickets;
    }

    public List<Ticket> getTicketsByClient(Client client) {
        Session session = sessionFactory.openSession();
        Query<Ticket> query = session.createQuery("from ticket where client=:client", Ticket.class);
        query.setParameter("client", client);
        List<Ticket> tickets = query.list();
        session.close();
        return tickets;
    }

    @Override
    public void isValidEntity(Ticket ticket) throws InvalidDataEntityException {
        List<String> errorItem = new ArrayList<>();
        if (ticket.client == null) errorItem.add("Client");
        if (ticket.fromPlanet == null) errorItem.add("From planet");
        if (ticket.toPlanet == null) errorItem.add("To planet");

        if (!errorItem.isEmpty()) {
            throw new InvalidDataEntityException("Not filled " + String.join(", ", errorItem));
        }
    }
}

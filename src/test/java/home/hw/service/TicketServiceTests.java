package home.hw.service;

import home.hw.dao.DbInit;
import home.hw.dao.HibernateUtil;
import home.hw.entity.Client;
import home.hw.entity.Planet;
import home.hw.entity.Ticket;
import home.hw.exception.InvalidDataEntityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTests {
    private TicketService ticketService;
    private ClientService clientService;
    private PlanetService planetService;

    @BeforeAll
    static void setUp() {
        HibernateUtil.getInstance();
        DbInit.initTestDb();
    }

    @BeforeEach
    void setClientService() {
        ticketService = new TicketService(HibernateUtil.getInstance().getTestSessionFactory());
        clientService = new ClientService(HibernateUtil.getInstance().getTestSessionFactory());
        planetService = new PlanetService(HibernateUtil.getInstance().getTestSessionFactory());
    }

    @AfterEach
    void cleanUp() {
        ticketService = null;
        clientService = null;
        planetService = null;
    }

    @Test
    void ValidParamCreateTicket() {
        //then
        Client client = clientService.getById(1L);
        Planet earth = planetService.getById("EARTH");
        Planet mars = planetService.getById("MAR");

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(earth);
        ticket.setToPlanet(mars);
        //when
        Long id = null;
        try {
            id = ticketService.create(ticket);
        } catch (InvalidDataEntityException ignored) {
        }
        assertNotNull(id);
    }

    @Test
    void NullClientCreateTicket() {
        //then
        Client client = null;
        Planet earth = planetService.getById("EARTH");
        Planet mars = planetService.getById("MAR");

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(earth);
        ticket.setToPlanet(mars);

        //when
        assertThrows(InvalidDataEntityException.class, () -> ticketService.create(ticket));
    }

    @Test
    void NullFromPlanetCreateTicket() {
        //then
        Client client = clientService.getById(1L);
        ;
        Planet from = null;
        Planet to = planetService.getById("MAR");

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(from);
        ticket.setToPlanet(to);

        //when
        assertThrows(InvalidDataEntityException.class, () -> ticketService.create(ticket));
    }

    @Test
    void NullToPlanetCreateTicket() {
        //then
        Client client = clientService.getById(1L);
        ;
        Planet from = planetService.getById("EARTH");
        Planet to = null;

        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setFromPlanet(from);
        ticket.setToPlanet(to);

        //when
        assertThrows(InvalidDataEntityException.class, () -> ticketService.create(ticket));
    }

    @Test
    void ValidUpdateTicket() {
        //then
        Ticket expected = ticketService.getById(1L);
        expected.setToPlanet(planetService.getById("VEN"));
        //when
        ticketService.update(expected);
        Ticket result = ticketService.getById(1L);
        assertEquals(expected, result);
    }

    @Test
    void ValidDeleteClient() {
        //then
        Ticket expected = ticketService.getById(3L);

        //when
        ticketService.delete(expected);
        Ticket result = ticketService.getById(3L);

        assertTrue(expected != null && result == null);
    }
}

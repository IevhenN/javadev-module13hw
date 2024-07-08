package home.hw.service;

import home.hw.dao.DbInit;
import home.hw.dao.HibernateUtil;
import home.hw.entity.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTests {
    private ClientService clientService;

    @BeforeAll
    static void setUp() {
        HibernateUtil.getInstance();
        DbInit.initTestDb();
    }

    @BeforeEach
    void setClientService() {
        clientService = new ClientService(HibernateUtil.getInstance().getTestSessionFactory());
    }

    @AfterEach
    void cleanUp() {
        clientService = null;
    }

    @Test
    void ValidParamCreateClient() {
        //then
        Client client = new Client();
        client.setName("Petrovich Arsen");

        //when
        Long id = clientService.create(client);

        assertNotNull(id);
    }

    @Test
    void ValidUpdateClient() {
        //then
        Client expected = clientService.getById(1L);
        expected.setName("New name");
        //when
        clientService.update(expected);
        Client result = clientService.getById(1L);
        assertEquals(expected, result);
    }

    @Test
    void ValidDeleteClient() {
        //then
        Client expected = clientService.getById(3L);

        //when
        clientService.delete(expected);
        Client result = clientService.getById(3L);

        assertTrue(expected != null && result == null);
    }

}

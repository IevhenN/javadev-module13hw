package home.hw.app;

import home.hw.dao.HibernateUtil;
import home.hw.entity.Client;
import home.hw.entity.Planet;
import home.hw.entity.Ticket;
import home.hw.service.ClientService;
import home.hw.service.PlanetService;
import home.hw.service.TicketService;

import java.util.Scanner;

public class TicketSelling {
    private ClientService clientService;
    private PlanetService planetService;
    private TicketService ticketService;

    public TicketSelling() {
        clientService = new ClientService(HibernateUtil.getInstance().getSessionFactory());
        planetService = new PlanetService(HibernateUtil.getInstance().getSessionFactory());
        ticketService = new TicketService(HibernateUtil.getInstance().getSessionFactory());
    }

    public void StartSales() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Passenger: ");
            String passenger = scanner.nextLine();
            if (isExit(passenger)) break;

            System.out.print("From: ");
            String from = scanner.nextLine();
            if (isExit(from)) break;

            System.out.print("To: ");
            String to = scanner.nextLine();
            if (isExit(to)) break;

            Planet fromPlanet = planetService.getById(from);
            Planet toPlanet = planetService.getById(to);
            Client client = clientService.getByName(passenger);

            if (client == null) {
                client = new Client();
                client.setName(passenger);
                clientService.create(client);

                System.out.println("create new client " + client.getName());
            }

            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);
            try {
                ticketService.create(ticket);
                System.out.println(ticket);
            } catch (Exception e) {
                System.out.println("Failed to create ticket: " + e.getMessage());
            }
        }
    }

    public boolean isExit(String line) {
        return line.equalsIgnoreCase("exit");
    }

}

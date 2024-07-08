package home.hw.app;

import home.hw.dao.DbInit;

public class App {
    public static void main(String[] args) {
        DbInit.initProdDb();
        new TicketSelling().StartSales();
    }
}

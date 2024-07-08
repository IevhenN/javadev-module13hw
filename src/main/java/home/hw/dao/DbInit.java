package home.hw.dao;

import home.hw.config.Constant;
import org.flywaydb.core.Flyway;

public class DbInit {
    public static void initProdDb(){
        initDB(Constant.DB_URL);
    }
    public static void initTestDb(){
        initDB(Constant.TEST_DB_URL);
    }

    private static void initDB(String connectionUrl) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();
    }

}


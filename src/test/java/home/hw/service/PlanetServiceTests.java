
package home.hw.service;

        import home.hw.dao.DbInit;
        import home.hw.dao.HibernateUtil;
        import home.hw.entity.Planet;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

class PlanetServiceTests {
    private PlanetService planetService;
    @BeforeAll
    static void setUp(){
        HibernateUtil.getInstance();
        DbInit.initTestDb();
    }
    @BeforeEach
    void setPlanetService() {
        planetService = new PlanetService(HibernateUtil.getInstance().getTestSessionFactory());
    }

    @AfterEach
    void cleanUp() {
        planetService = null;
    }
    @Test
    void ValidParamCreatePlanet() {
        //then
        String expected = "MRC";
        Planet planet = new Planet();
        planet.setName("Mercury");
        planet.setId(expected);

        //when
        String result = planetService.create(planet);

        assertEquals(expected,result);
    }

    @Test
    void ValidUpdatePlanet(){
        //then
        String id = "MRC";
        Planet expected = planetService.getById(id);
        expected.setName("Mercuryyy");
        //when
        planetService.update(expected);
        Planet result = planetService.getById(id);
        assertEquals(expected,result);
    }

    @Test
    void ValidDeletePlanet(){
        //then
        String id = "MRC";
        Planet expected = planetService.getById(id);

        //when
        planetService.delete(expected);
        Planet result = planetService.getById(id);

        assertTrue(expected!=null&&result==null);
    }

}

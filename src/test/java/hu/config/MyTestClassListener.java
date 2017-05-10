package hu.config;

/**
 * Ennek és a MySpringJUnit4ClassRunner használatával elérhető hogy ne csak static
 * tipusú metódus lehessen csak @BeforeClass vagy @AfterClass tulajdonságokkal felruházva.
 * @author Apati
 *
 */
public interface MyTestClassListener {
    void beforeClassSetup();
    void afterClassSetup();
}
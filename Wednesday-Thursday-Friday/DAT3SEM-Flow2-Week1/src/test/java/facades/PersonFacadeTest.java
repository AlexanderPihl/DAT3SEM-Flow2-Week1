package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private Person p1;
    private Person p2;
    private Person p3;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Gunner", "Storstrømpe", "83654121");
        p2 = new Person("Carl", "Vingearm", "14545679");
        p3 = new Person("Lise", "Piversen", "21354312");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Person p").executeUpdate();
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, facade.getPersonCount(), "Expects three rows in the database");
    }
    
    @Test
    public void testPersonCount() {
        assertEquals(3, facade.getPersonCount(), "Expects three rows in the database");
    }

    @Test
    public void testGetAllPersons() {
        PersonsDTO personsDTO = facade.getAllPersons();
        
        assertEquals(facade.getAllPersons().equals(personsDTO), personsDTO.equals(personsDTO));
    }
    
    @Test
    public void testGetPerson() throws PersonNotFoundException {
        
        PersonDTO personDTO = facade.getPerson(p1.getId());
        assertEquals("Gunner", personDTO.getFirstName());
        
    }
    
//    @Test
//    public void testAddPerson() throws MissingInputException {
//        PersonDTO person = facade.addPerson("J-P", "L-M", "1234");
//        PersonsDTO list = facade.getAllPersons();
//        
//        assertEquals(3, person.equals(list));
//        assertThat(person.getFirstName(), Matchers.hasItemInArray(list.));
//        
//    }

}

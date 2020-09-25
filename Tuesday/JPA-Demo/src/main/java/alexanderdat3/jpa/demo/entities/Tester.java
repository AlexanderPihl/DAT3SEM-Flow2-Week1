package alexanderdat3.jpa.demo.entities;

import dto.PersonStyleDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author alexa
 */
public class Tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Janni", 1990);
        Person p2 = new Person("Kim", 2000);

        Address a1 = new Address("Ende Alle", 2700, "Tarm");
        Address a2 = new Address("Kattevejen", 2300, "Hundested");

        p1.setAddress(a1);
        p2.setAddress(a2);

        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);

        p1.AddFee(f1);
        p1.AddFee(f3);
        p2.AddFee(f2);

        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");
        SwimStyle s3 = new SwimStyle("Back Stroke");

        p1.AddSwimStyle(s1);
        p1.AddSwimStyle(s2);
        p2.AddSwimStyle(s3);

        em.getTransaction().begin();
//            em.persist(a1);
//            em.persist(a2);
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();

        //Fjerner swimstyle fra p1
        em.getTransaction().begin();
        p1.removeSwimStyle(s2);
        em.getTransaction().commit();

        System.out.println("p1: " + p1.getP_id() + " Name: " + p1.getName());
        System.out.println("p2: " + p2.getP_id() + " Name: " + p2.getName());

        System.out.println("Janni's gade: " + p1.getAddress().getStreet());
        System.out.println("Kim's gade: " + p2.getAddress().getStreet());

        System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());

        System.out.println("Hvem har betalt f2? Det har: " + f2.getPerson().getName());

        System.out.println("-------------------------------------------------------------");

        System.out.println("Hvad er der blevet betalt i alt?");
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        for (Fee f : fees) {
            System.out.println(f.getPerson().getName() + ": " + f.getAmount() + "kr" + " / Date: " + f.getPayDate() + " / Address: " + f.getPerson().getAddress().getStreet() + " , " + f.getPerson().getAddress().getCity());
        }

        System.out.println("-------------------------------------------------------------");

        // list of Persons
        TypedQuery<Person> q2 = em.createQuery("SELECT p FROM Person p ORDER BY p.name ASC", Person.class);
        List<Person> persons = q2.getResultList();
        System.out.println("Names of every person in db in order:");
        for (Person p : persons) {
            System.out.println("- " + p.getName());
        }

        System.out.println("-------------------------------------------------------------");

        // list of Fees desc
        TypedQuery<Fee> q3 = em.createQuery("SELECT f FROM Fee f ORDER BY f.amount DESC", Fee.class);
        List<Fee> fees2 = q3.getResultList();
        System.out.println("Names of every person in db in order:");
        for (Fee f : fees2) {
            System.out.println("- " + f.getPerson().getName() + " : " + f.getAmount());
        }

        System.out.println("-------------------------------------------------------------");

        //Get person with amount of 300 in fees
        TypedQuery<Fee> q4 = em.createQuery("SELECT f FROM Fee f WHERE f.amount >= 300", Fee.class);
        Fee fees3 = q4.getSingleResult();
        System.out.println(fees3.getPerson().getName() + " has the highest fee of: " + fees3.getAmount());

        System.out.println("-------------------------------------------------------------");

        //Navneliste
        Query q5 = em.createQuery("SELECT new dto.PersonStyleDTO(p.name, p.year, s.styleName) FROM Person p JOIN p.styles s");
        List<PersonStyleDTO> personList = q5.getResultList();
        System.out.println("Navneliste");
        for (PersonStyleDTO p : personList) {
            System.out.println(p.getName() + ", " + p.getYear() + ", " + p.getSwimStyle());
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("Graver lidt mere kontra JPQL i forrige eksempel");
        
        TypedQuery<Person> q6 = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons2 = q6.getResultList();
        for (Person p : persons2) {
            System.out.println("Navn: " + p.getName());
            System.out.println("-- Fees:");
            for (Fee f : p.getFees()) {
                System.out.println("---- Beløb " + f.getAmount() + ", " + f.getPayDate().toString());
            }
            System.out.println("-- Styles:");
            for (SwimStyle ss : p.getStyles()) {
                System.out.println("---- Style: " + ss.getStyleName());
            }
        }

    }

}

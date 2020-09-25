package facades;

import entities.Address;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Person p1 = new Person("Gunner", "Storstrømpe", "83654121");
        Person p2 = new Person("Carl", "Vingearm", "14545679");
        Person p3 = new Person("Lise", "Piversen", "21354312");

        Address a1 = new Address("Ende Alle", "2700", "Tarm");
        Address a2 = new Address("Kattevejen", "2750", "Hundested");
        Address a3 = new Address("Rottesvinget", "2300", "Giftby");
        
        p1.setAddress(a1);
        p2.setAddress(a1);
        p3.setAddress(a3);

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();

    }

}

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DEmo {
public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("onetoone_uni_country_prime");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	Person s = em.find(Person.class, 13);
	Aadhar a = s.getAadhar();
	a.setGender("female");
	et.begin();
	em.merge(a);
	et.commit();
}
}

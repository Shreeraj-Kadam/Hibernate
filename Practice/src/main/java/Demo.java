import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Demo {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("practice");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et= em.getTransaction();

//		Subject sub1 = new Subject(1,"Subj1");
//		Subject sub2 = new Subject(2,"Subj2");
//		Student student  = new Student(1, "Student1");
//		List<Subject> subjects = new ArrayList<Subject>();
//		subjects.add(sub1);
//		subjects.add(sub2);
//		student.subjects = subjects;
		
		Student student  = new Student(3, "Student3");		
		Subject sub = em.find(Subject.class, 1);
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(sub);
		student.subjects = subjects;
		
		et.begin();
		
		em.persist(student);
		et.commit();
		
	}




}

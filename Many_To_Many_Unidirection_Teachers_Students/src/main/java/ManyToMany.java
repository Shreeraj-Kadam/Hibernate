import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;
public class ManyToMany {
	 EntityManagerFactory emf ;
	 EntityManager em ;
	 EntityTransaction et ;
	 Scanner input ;
	 
	 ManyToMany(){
		 emf = Persistence.createEntityManagerFactory("manytomany_unidirection");
		 em = emf.createEntityManager();
		 et = em.getTransaction();
		 input = new Scanner(System.in);
	 }
	
	public void createRecords() {
		Teachers teacher = new Teachers();
		System.out.println("Enter the Teacher name");
		String tName = input.nextLine();		
		teacher.setName(tName);
		System.out.println("Enter the Teacher Subject");
		String tSubject= input.nextLine();		
		teacher.setName(tSubject);
		
		System.out.println("How may students are  associated by this Teacher");
		int sCount = input.nextInt();
		input.nextLine();
		 List<Student> students= addstudent(sCount);
		 teacher.setStudents(students);
		 
		 et.begin();
		 em.persist(teacher);
		 et.commit();
		
	}
	
	

	
	public List<Student> addstudent(int count){
		List<Student> students = new ArrayList<Student>();
		for(int i = 0; i< count ;i++) {
			Student student = new Student();
			System.out.println("Enter the Student Name");
			String name = input.nextLine();
			System.out.println("Enter the Student age");
			int age = input.nextInt();
			input.nextLine();			
			student.setName(name);
			student.setAge(age);
			students.add(student);
			}
		return students;
	}
	
	public void retrieveData() {
		System.out.println("Enter the teacher id");
		long id = input.nextLong();
		input.nextLine();
		Teachers teacher = em.find(Teachers.class, id);
		System.out.println("Teacher Name: " + teacher.getName());
		for(Student s : teacher.getStudents()) {
			System.out.println("Student Name : "+  s.getName() + " " + "Student Age : " + s.getAge());
			
		}
		
	}
	
	public void updateData() {
		System.out.println("Enter the teacher id");
		long id = input.nextLong();
		input.nextLine();
		Teachers teacher = em.find(Teachers.class, id);
		
		System.out.println("Enter the teacher new name");
		String name = input.nextLine();
		teacher.setName(name);
		System.out.println("Enter the teacher new subject");
		String subject = input.nextLine();
		teacher.setSubject(subject);
		
		et.begin();
		em.merge(teacher);
		et.commit();
		
	}
	
	public void removeData() {
		System.out.println("Enter the teacher id");
		int id = input.nextInt();
		input.nextLine();
		Teachers teacher = em.find(Teachers.class, id);
		
		et.begin();
		em.remove(teacher);
		et.commit();
	}
	public static void main(String[] args) {
		ManyToMany mtm = new ManyToMany();
		while(true) {
			System.out.println("Enter the choice \n 1: Create \n 2: Retrieve \n 3: Update \n 4: Delete");
			int choice = mtm.input.nextInt();
			mtm.input.nextLine();
			switch (choice) {
			case 1:
			{
				mtm.createRecords();
				break;
			}
			case 2:
			{
				
				mtm.retrieveData();
				break;
			}
			case 3:
			{
				mtm.updateData();
				break;
			}
			case 4:
			{	
				mtm.removeData();
			}
				break;
				
			}
		}

			
		}
	}


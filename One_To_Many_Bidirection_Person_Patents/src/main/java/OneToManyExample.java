import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;
public class OneToManyExample {
	 EntityManagerFactory emf ;
	 EntityManager em ;
	 EntityTransaction et ;
	 Scanner input ;
	 
	 OneToManyExample(){
		 emf = Persistence.createEntityManagerFactory("onetomany_bidirection");
		 em = emf.createEntityManager();
		 et = em.getTransaction();
		 input = new Scanner(System.in);
	 }
	
	public void createRecords() {
		Person person = new Person();
		System.out.println("Enter the Person name");
		String aName = input.nextLine();		
		person.setName(aName);
		
		System.out.println("How many patents are associate by  the Person");
		int mCount = input.nextInt();
		input.nextLine();
		 List<Patent> patents= addPatents(mCount);
		 person.setPatents(patents);
		 
		 et.begin();
		 em.persist(person);
		 et.commit();
		
	}
	
	

	
	public List<Patent> addPatents(int count){
		List<Patent> patents = new ArrayList<Patent>();
		for(int i = 0; i< count ;i++) {
			Patent patent = new  Patent();
			System.out.println("Enter the patent title");
			String name = input.nextLine();
			patent.setTitle(name);
			patents.add(patent);
			}
		return patents;
	}
	
	public void retrieveData() {
		System.out.println("Enter the person id");
		long id = input.nextLong();
		input.nextLine();
		Person person = em.find(Person.class, id);
		System.out.println("Person Name: " + person.getName());
		for(Patent p : person.getPatents()) {
			System.out.println(p.getTitle());
		}
		
	}
	
	public void updateData() {
		System.out.println("Enter the Person id");
		long id = input.nextLong();
		input.nextLine();
		Person person = em.find(Person.class, id);
		
		System.out.println("Enter the Person  new name");
		String name = input.nextLine();
		person.setName(name);
		
		et.begin();
		em.merge(person);
		et.commit();
		
	}
	
	public void removeData() {
		System.out.println("Enter the person id");
		long id = input.nextLong();
		input.nextLine();
		Person person = em.find(Person.class, id);
		
		et.begin();
		em.remove(person);
		et.commit();
	}
	public static void main(String[] args) {
		OneToManyExample mtm = new OneToManyExample();
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


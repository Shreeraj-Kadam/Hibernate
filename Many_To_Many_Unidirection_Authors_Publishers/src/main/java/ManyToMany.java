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
		Publisher publisher = new Publisher();
		System.out.println("Enter the Publisher name");
		String aName = input.nextLine();		
		publisher.setName(aName);
		
		System.out.println("How may authors associate by  the publisher");
		int mCount = input.nextInt();
		input.nextLine();
		 List<Author> authors= addauthor(mCount);
		 publisher.setAuthors(authors);
		 
		 et.begin();
		 em.persist(publisher);
		 et.commit();
		
	}
	
	

	
	public List<Author> addauthor(int count){
		List<Author> authors = new ArrayList<Author>();
		for(int i = 0; i< count ;i++) {
			Author author = new Author();
			System.out.println("Enter the author name");
			String name = input.nextLine();
			author.setName(name);
			authors.add(author);
			}
		return authors;
	}
	
	public void retrieveData() {
		System.out.println("Enter the actor id");
		long id = input.nextLong();
		input.nextLine();
		Publisher publisher = em.find(Publisher.class, id);
		System.out.println("Publisher Name: " + publisher.getName());
		for(Author m : publisher.getAuthors()) {
			System.out.println(m.getName());
		}
		
	}
	
	public void updateData() {
		System.out.println("Enter the Publisher id");
		long id = input.nextLong();
		input.nextLine();
		Publisher publisher = em.find(Publisher.class, id);
		
		System.out.println("Enter the Publisher new name");
		String name = input.nextLine();
		publisher.setName(name);
		
		et.begin();
		em.merge(publisher);
		et.commit();
		
	}
	
	public void removeData() {
		System.out.println("Enter the actor id");
		long id = input.nextLong();
		input.nextLine();
		Publisher publisher = em.find(Publisher.class, id);
		
		et.begin();
		em.remove(publisher);
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


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
		 emf = Persistence.createEntityManagerFactory("onetomany_unidirection");
		 em = emf.createEntityManager();
		 et = em.getTransaction();
		 input = new Scanner(System.in);
	 }
	
	public void createRecords() {
		Parent parent = new Parent();
		System.out.println("Enter the Parent name");
		String aName = input.nextLine();		
		parent.setName(aName);
		
		System.out.println("How many children are associate with parent");
		int mCount = input.nextInt();
		input.nextLine();
		 List<Child> children= addchild(mCount);
		 parent.setChildren(children);
		 
		 et.begin();
		 em.persist(parent);
		 et.commit();
		
	}
	
	

	
	public List<Child> addchild(int count){
		List<Child> children = new ArrayList<Child>();
		for(int i = 0; i< count ;i++) {
			Child child = new  Child();
			System.out.println("Enter the Child name");
			String name = input.nextLine();
			child.setName(name);
			children.add(child);
			}
		return children;
	}
	
	public void retrieveData() {
		System.out.println("Enter the parent id");
		long id = input.nextLong();
		input.nextLine();
		Parent parent = em.find(Parent.class, id);
		System.out.println("Parent Name: " + parent.getName());
		for(Child c : parent.getChildren()) {
			System.out.println(c.getName());
		}
		
	}
	
	public void updateData() {
		System.out.println("Enter the parent id");
		long id = input.nextLong();
		input.nextLine();
		Parent parent = em.find(Parent.class, id);
		
		System.out.println("Enter the Prent new name");
		String name = input.nextLine();
		parent.setName(name);
		
		et.begin();
		em.merge(parent);
		et.commit();
		
	}
	
	public void removeData() {
		System.out.println("Enter the parent id");
		long id = input.nextLong();
		input.nextLine();
		Parent parent = em.find(Parent.class, id);
		
		et.begin();
		em.remove(parent);
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


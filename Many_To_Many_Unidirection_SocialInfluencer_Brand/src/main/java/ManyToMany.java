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
		SocialInfluencer si = new SocialInfluencer();
		System.out.println("Enter the influiencer name");
		String aName = input.nextLine();		
		si.setName(aName);
		
		System.out.println("How may brands associate by  the influencer");
		int mCount = input.nextInt();
		input.nextLine();
		 List<Brand> brands= addinfluencer(mCount);
		 si.setBrands(brands);
		 
		 et.begin();
		 em.persist(si);
		 et.commit();
		
	}
	
	

	
	public List<Brand> addinfluencer(int count){
		List<Brand> brands = new ArrayList<Brand>();
		for(int i = 0; i< count ;i++) {
			Brand brand = new Brand();
			System.out.println("Enter the Brand name");
			String name = input.nextLine();
			brand.setName(name);
			brands.add(brand);
			}
		return brands;
	}
	
	public void retrieveData() {
		System.out.println("Enter the influencer id");
		long id = input.nextLong();
		input.nextLine();
		SocialInfluencer si = em.find(SocialInfluencer.class, id);
		System.out.println("Influencer Name: " + si.getName());
		for(Brand b : si.getBrands()) {
			System.out.println(b.getName());
		}
		
	}
	
	public void updateData() {
		System.out.println("Enter the Publisher id");
		long id = input.nextLong();
		input.nextLine();
		SocialInfluencer si = em.find(SocialInfluencer.class, id);
		
		System.out.println("Enter the Influencer new name");
		String name = input.nextLine();
		si.setName(name);
		
		et.begin();
		em.merge(si);
		et.commit();
		
	}
	
	public void removeData() {
		System.out.println("Enter the influencer id");
		long id = input.nextLong();
		input.nextLine();
		SocialInfluencer si = em.find(SocialInfluencer.class, id);
		
		et.begin();
		em.remove(si);
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


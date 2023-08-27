import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOne {
	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("onetoone_bidirection");
	 EntityManager em = emf.createEntityManager();
	 EntityTransaction et = em.getTransaction();
	 Scanner input = new Scanner(System.in);
	
	public void createRecords(OneToOne oto) {
		
		Country country = new Country();
		System.out.println("Enter the country name");
		String cname = input.nextLine();
		country.setName(cname);
		
		PrimeMinister pm = new PrimeMinister();
		System.out.println("Enter the Prime Minister name");
		String pname = input.nextLine();
		pm.setName(pname);
		
		System.out.println("Enter the Prime Minister age");
		int page = input.nextInt();
		input.nextLine();
		pm.setAge(page);
		
		country.setPm(pm);
		pm.setCountry(country);
		
		oto.et.begin();
		oto.em.persist(country);
		oto.et.commit();
			
	}
	
	public Country retrieveData(OneToOne oto) {
		System.out.println("Enter the country id");
		int id = input.nextInt();
		input.nextLine();
		Country country = oto.em.find(Country.class, id);
		return country;
	}
	
	public void updateData(OneToOne oto) {
		Country country = oto.retrieveData(oto);
		
	

		PrimeMinister pm = country.getPm();
		System.out.println("Enter the Prime Minister name");
		String pname = input.nextLine();
		pm.setName(pname);
		
		System.out.println("Enter the Prime Minister age");
		int page = input.nextInt();
		input.nextLine();
		pm.setAge(page);
		
		country.setPm(pm);
		
		oto.et.begin();
		oto.em.merge(country);
		
		oto.et.commit();
	}
	
	public void removeData(OneToOne oto) {
		Country country = oto.retrieveData(oto);
		oto.et.begin();
		oto.em.remove(country);
		oto.et.commit();
	}
	public static void main(String[] args) {
		OneToOne oto = new OneToOne();
		while(true) {
			System.out.println("Enter the choice \n 1: Create \n 2: Retrieve \n 3: Update \n 4: Delete");
			int choice = oto.input.nextInt();
			oto.input.nextLine();
			switch (choice) {
			case 1:
			{
				oto.createRecords(oto);
				break;
			}
			case 2:
			{
				Country country= oto.retrieveData(oto);
				System.out.println(country.getId() + " " + country.getName());
				PrimeMinister pm = country.getPm();
				System.out.println(pm.getId() + " " + pm.getName() + " " + pm.getAge());
				
				break;
			}
			case 3:
			{
				oto.updateData(oto);
				break;
			}
			case 4:
			{	oto.removeData(oto);
				break;
			}
				

			default:
				break;
			}
		}
	}
}

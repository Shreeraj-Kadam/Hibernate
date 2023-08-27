import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOne {
	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("onetoone_uni_country_prime");
	 EntityManager em = emf.createEntityManager();
	 EntityTransaction et = em.getTransaction();
	 Scanner input = new Scanner(System.in);
	
	public void createRecords(OneToOne oto) {
		
		Person person = new Person();
		System.out.println("Enter the person name");
		String pname = input.nextLine();
		person.setName(pname);
		System.out.println("Enter the person age");
		int page = input.nextInt();
		input.nextLine();
		person.setAge(page);
		
		
		Aadhar aadhar = new Aadhar();
		System.out.println("Enter the Aadhar Id");
		long aid = input.nextLong();
		input.nextLine();
		aadhar.setAadharId(aid);
		
		System.out.println("Enter the address");
		String address = input.nextLine();		
		aadhar.setAddress(address);
		
		System.out.println("Enter the gender");
		String gender = input.nextLine();		
		aadhar.setGender(gender);
		
		person.setAadhar(aadhar);
		
		oto.et.begin();
		oto.em.persist(person);
		oto.et.commit();
			
	}
	
	public Person retrieveData(OneToOne oto) {
		System.out.println("Enter the person id");
		int id = input.nextInt();
		input.nextLine();
		Person person = oto.em.find(Person.class, id);
		return person;
	}
	
	public void updateData(OneToOne oto) {
		Person person = oto.retrieveData(oto);
		
	

		Aadhar aadhar = person.getAadhar();
		System.out.println("Enter the new  address");
		String address = input.nextLine();
		aadhar.setAddress(address);
		
		System.out.println("Enter the gender");
		String gender = input.nextLine();
		
		aadhar.setGender(gender);
		
		person.setAadhar(aadhar);
		
		oto.et.begin();
		oto.em.merge(person);
		
		oto.et.commit();
	}
	
	public void removeData(OneToOne oto) {
		Person person = oto.retrieveData(oto);
		oto.et.begin();
		oto.em.remove(person);
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
				Person person= oto.retrieveData(oto);
				System.out.println(person.getId() + " " + person.getName() + " " + person.getAge());
				Aadhar aadhar = person.getAadhar();
				System.out.println(aadhar.getAadharId() + " " + aadhar.getAddress() + " " + aadhar.getGender());
				
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

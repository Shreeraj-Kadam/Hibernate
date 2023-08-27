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
		Actor actor = new Actor();
		System.out.println("Enter the Actor name");
		String aName = input.nextLine();		
		actor.setName(aName);
		System.out.println("How may movies done by this Actor");
		int mCount = input.nextInt();
		input.nextLine();
		 List<Movie> movies= addmovie(mCount);
		 actor.setMovies(movies);
		 
		 et.begin();
		 em.persist(actor);
		 et.commit();
		
	}
	
	

	
	public List<Movie> addmovie(int count){
		List<Movie> movies = new ArrayList<Movie>();
		for(int i = 0; i< count ;i++) {
			Movie movie = new Movie();
			System.out.println("Enter the movie Title");
			String title = input.nextLine();
			movie.setTitle(title);
			movies.add(movie);
			}
		return movies;
	}
	
	public void retrieveData() {
		System.out.println("Enter the actor id");
		long id = input.nextLong();
		input.nextLine();
		Actor actor = em.find(Actor.class, id);
		System.out.println("Actor Name: " + actor.getName());
		for(Movie m : actor.getMovies()) {
			System.out.println(m.getTitle());
		}
		
	}
	
	public void updateData() {
		System.out.println("Enter the actor id");
		long id = input.nextLong();
		input.nextLine();
		Actor actor = em.find(Actor.class, id);
		
		System.out.println("Enter the actor new name");
		String name = input.nextLine();
		actor.setName(name);
		
		et.begin();
		em.merge(actor);
		et.commit();
		
	}
	
	public void removeData() {
		System.out.println("Enter the actor id");
		long id = input.nextLong();
		input.nextLine();
		Actor actor = em.find(Actor.class, id);
		
		et.begin();
		em.remove(actor);
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


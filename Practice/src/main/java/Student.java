import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Student {
	@Id
	int id;
	String name;
	
	@ManyToMany
	List<Subject> subjects;
	
	public Student(){
		
	}
	Student(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	
}

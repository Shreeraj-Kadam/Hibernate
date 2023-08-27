import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subject {
@Id
int id;
String name;

Subject(){
	
}
Subject(int id, String name){
	this.id = id; 
	this.name = name;
}

}

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class SocialInfluencer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	List<Brand> brands ;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Brand> getBrands() {
		return brands;
	}


	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}


	
	
	
	
	
	
	
	
	
}

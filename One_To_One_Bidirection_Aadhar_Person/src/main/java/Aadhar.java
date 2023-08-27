import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Aadhar {
	@Id
	private long aadharId;
	
	private String address;
	
	private String gender;
	
	@OneToOne(cascade = CascadeType.ALL)
	Person person;

	public long getAadharId() {
		return aadharId;
	}

	public void setAadharId(long aadharId) {
		this.aadharId = aadharId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	
	
	
	
}

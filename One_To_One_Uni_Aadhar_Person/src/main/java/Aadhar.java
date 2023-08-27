import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aadhar {
	@Id
	private long aadharId;
	
	private String address;
	
	private String gender;

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
	
	
	
	
}

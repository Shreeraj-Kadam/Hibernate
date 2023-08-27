import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SerialNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;
    
    @OneToOne(mappedBy = "serialNumber")
    ElectronicDevice device;

	public Long getId() {
		return id;
	}

	public ElectronicDevice getDevice() {
		return device;
	}

	public void setDevice(ElectronicDevice device) {
		this.device = device;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

   
}

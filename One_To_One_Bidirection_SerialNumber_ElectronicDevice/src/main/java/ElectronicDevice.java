import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ElectronicDevice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String deviceName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "serial_number_id")
	private SerialNumber serialNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public SerialNumber getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(SerialNumber serialNumber) {
		this.serialNumber = serialNumber;
	}

}

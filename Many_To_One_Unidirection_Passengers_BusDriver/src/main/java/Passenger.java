import javax.persistence.*;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_driver_id")
    private BusDriver busDriver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public BusDriver getBusDriver() {
		return busDriver;
	}

	public void setBusDriver(BusDriver busDriver) {
		this.busDriver = busDriver;
	}


}

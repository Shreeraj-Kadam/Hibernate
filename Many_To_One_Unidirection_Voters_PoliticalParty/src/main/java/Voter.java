import javax.persistence.*;

@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voterName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "political_party_id")
    private PoliticalParty politicalParty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public PoliticalParty getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(PoliticalParty politicalParty) {
		this.politicalParty = politicalParty;
	}


}

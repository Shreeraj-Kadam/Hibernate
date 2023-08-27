import javax.persistence.*;

@Entity
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_captain_id")
    private TeamCaptain teamCaptain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public TeamCaptain getTeamCaptain() {
		return teamCaptain;
	}

	public void setTeamCaptain(TeamCaptain teamCaptain) {
		this.teamCaptain = teamCaptain;
	}


}

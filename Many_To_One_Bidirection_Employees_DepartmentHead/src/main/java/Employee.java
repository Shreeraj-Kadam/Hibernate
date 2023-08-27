import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_head_id")
    private DepartmentHead departmentHead;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public DepartmentHead getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(DepartmentHead departmentHead) {
		this.departmentHead = departmentHead;
	}


}

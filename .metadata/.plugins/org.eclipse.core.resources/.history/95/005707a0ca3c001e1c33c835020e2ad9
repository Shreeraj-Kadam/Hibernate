import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ManyToOneExample {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    private Scanner input;

    public ManyToOneExample() {
        emf = Persistence.createEntityManagerFactory("many_to_one_unidirection");
        em = emf.createEntityManager();
        et = em.getTransaction();
        input = new Scanner(System.in);
    }

    public void closeEntityManager() {
        em.close();
        emf.close();
    }

    public void createEmployee() {
        try {
            et.begin();

            Employee employee = new Employee();
            System.out.println("Enter the employee name:");
            employee.setEmployeeName(input.nextLine());

            DepartmentHead departmentHead = new DepartmentHead();
            System.out.println("Enter the department head name:");
            departmentHead.setHeadName(input.nextLine());

            employee.setDepartmentHead(departmentHead);

            em.persist(employee);

            et.commit();

            System.out.println("Employee and Department Head records created successfully.");

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void retrieveEmployee() {
        System.out.println("Enter the employee ID:");
        Long id = input.nextLong();
        input.nextLine(); // Consume the newline character

        Employee employee = em.find(Employee.class, id);

        if (employee != null) {
            System.out.println("Employee Name: " + employee.getEmployeeName());
            DepartmentHead departmentHead = employee.getDepartmentHead();
            if (departmentHead != null) {
                System.out.println("Department Head Name: " + departmentHead.getHeadName());
            } else {
                System.out.println("No department head associated.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void updateEmployee() {
        try {
            et.begin();

            System.out.println("Enter the employee ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Employee employee = em.find(Employee.class, id);

            if (employee != null) {
                System.out.println("Enter the updated employee name:");
                employee.setEmployeeName(input.nextLine());

                DepartmentHead departmentHead = employee.getDepartmentHead();
                if (departmentHead != null) {
                    System.out.println("Enter the updated department head name:");
                    departmentHead.setHeadName(input.nextLine());
                }

                et.commit();

                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void deleteEmployee() {
        try {
            et.begin();

            System.out.println("Enter the employee ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Employee employee = em.find(Employee.class, id);

            if (employee != null) {
                em.remove(employee);
                et.commit();
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ManyToOneExample example = new ManyToOneExample();

        while (true) {
            System.out.println("Enter the choice:");
            System.out.println("1: Create\n2: Retrieve\n3: Update\n4: Delete\n5: Exit");
            int choice = example.input.nextInt();
            example.input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    example.createEmployee();
                    break;
                case 2:
                    example.retrieveEmployee();
                    break;
                case 3:
                    example.updateEmployee();
                    break;
                case 4:
                    example.deleteEmployee();
                    break;
                case 5:
                    example.closeEntityManager();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

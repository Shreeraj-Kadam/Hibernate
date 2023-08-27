import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class UnidirectionalOneToOneExample {
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    private Scanner input;

    public UnidirectionalOneToOneExample() {
        emf = Persistence.createEntityManagerFactory("onetoone_uni_country_prime");
        em = emf.createEntityManager();
        et = em.getTransaction();
        input = new Scanner(System.in);
    }

    public void closeEntityManager() {
        em.close();
        emf.close();
    }

    public void createStudent() {
        try {
            et.begin();

            Student student = new Student();
            System.out.println("Enter the student name:");
            student.setStudentName(input.nextLine());

            StudentProfile studentProfile = new StudentProfile();
            System.out.println("Enter the profile description:");
            studentProfile.setProfileDescription(input.nextLine());

            student.setStudentProfile(studentProfile);

            em.persist(student);

            et.commit();

            System.out.println("Student and Student Profile records created successfully.");

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void retrieveStudent() {
        System.out.println("Enter the student ID:");
        Long id = input.nextLong();
        input.nextLine(); // Consume the newline character

        Student student = em.find(Student.class, id);

        if (student != null) {
            System.out.println("Student Name: " + student.getStudentName());
            StudentProfile studentProfile = student.getStudentProfile();
            if (studentProfile != null) {
                System.out.println("Profile Description: " + studentProfile.getProfileDescription());
            } else {
                System.out.println("No student profile associated.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateStudent() {
        try {
            et.begin();

            System.out.println("Enter the student ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Student student = em.find(Student.class, id);

            if (student != null) {
                System.out.println("Enter the updated student name:");
                student.setStudentName(input.nextLine());

                StudentProfile studentProfile = student.getStudentProfile();
                if (studentProfile != null) {
                    System.out.println("Enter the updated profile description:");
                    studentProfile.setProfileDescription(input.nextLine());
                }

                et.commit();

                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void deleteStudent() {
        try {
            et.begin();

            System.out.println("Enter the student ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Student student = em.find(Student.class, id);

            if (student != null) {
                em.remove(student);
                et.commit();
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnidirectionalOneToOneExample example = new UnidirectionalOneToOneExample();

        while (true) {
            System.out.println("Enter the choice:");
            System.out.println("1: Create\n2: Retrieve\n3: Update\n4: Delete\n5: Exit");
            int choice = example.input.nextInt();
            example.input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    example.createStudent();
                    break;
                case 2:
                    example.retrieveStudent();
                    break;
                case 3:
                    example.updateStudent();
                    break;
                case 4:
                    example.deleteStudent();
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

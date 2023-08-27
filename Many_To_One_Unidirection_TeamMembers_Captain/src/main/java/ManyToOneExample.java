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

    public void createTeamMember() {
        try {
            et.begin();

            TeamMember teamMember = new TeamMember();
            System.out.println("Enter the team member name:");
            teamMember.setMemberName(input.nextLine());

            TeamCaptain teamCaptain = new TeamCaptain();
            System.out.println("Enter the team captain name:");
            teamCaptain.setCaptainName(input.nextLine());

            teamMember.setTeamCaptain(teamCaptain);

            em.persist(teamMember);

            et.commit();

            System.out.println("Team Member and Team Captain records created successfully.");

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void retrieveTeamMember() {
        System.out.println("Enter the team member ID:");
        Long id = input.nextLong();
        input.nextLine(); // Consume the newline character

        TeamMember teamMember = em.find(TeamMember.class, id);

        if (teamMember != null) {
            System.out.println("Team Member Name: " + teamMember.getMemberName());
            TeamCaptain teamCaptain = teamMember.getTeamCaptain();
            if (teamCaptain != null) {
                System.out.println("Team Captain Name: " + teamCaptain.getCaptainName());
            } else {
                System.out.println("No team captain associated.");
            }
        } else {
            System.out.println("Team Member not found.");
        }
    }

    public void updateTeamMember() {
        try {
            et.begin();

            System.out.println("Enter the team member ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            TeamMember teamMember = em.find(TeamMember.class, id);

            if (teamMember != null) {
                System.out.println("Enter the updated team member name:");
                teamMember.setMemberName(input.nextLine());

                TeamCaptain teamCaptain = teamMember.getTeamCaptain();
                if (teamCaptain != null) {
                    System.out.println("Enter the updated team captain name:");
                    teamCaptain.setCaptainName(input.nextLine());
                }

                et.commit();

                System.out.println("Team Member updated successfully.");
            } else {
                System.out.println("Team Member not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void deleteTeamMember() {
        try {
            et.begin();

            System.out.println("Enter the team member ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            TeamMember teamMember = em.find(TeamMember.class, id);

            if (teamMember != null) {
                em.remove(teamMember);
                et.commit();
                System.out.println("Team Member deleted successfully.");
            } else {
                System.out.println("Team Member not found.");
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
                    example.createTeamMember();
                    break;
                case 2:
                    example.retrieveTeamMember();
                    break;
                case 3:
                    example.updateTeamMember();
                    break;
                case 4:
                    example.deleteTeamMember();
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

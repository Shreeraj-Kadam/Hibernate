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

    public void createVoter() {
        try {
            et.begin();

            Voter voter = new Voter();
            System.out.println("Enter the voter name:");
            voter.setVoterName(input.nextLine());

            PoliticalParty politicalParty = new PoliticalParty();
            System.out.println("Enter the political party name:");
            politicalParty.setPartyName(input.nextLine());

            voter.setPoliticalParty(politicalParty);

            em.persist(voter);

            et.commit();

            System.out.println("Voter and Political Party records created successfully.");

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void retrieveVoter() {
        System.out.println("Enter the voter ID:");
        Long id = input.nextLong();
        input.nextLine(); // Consume the newline character

        Voter voter = em.find(Voter.class, id);

        if (voter != null) {
            System.out.println("Voter Name: " + voter.getVoterName());
            PoliticalParty politicalParty = voter.getPoliticalParty();
            if (politicalParty != null) {
                System.out.println("Political Party Name: " + politicalParty.getPartyName());
            } else {
                System.out.println("No political party associated.");
            }
        } else {
            System.out.println("Voter not found.");
        }
    }

    public void updateVoter() {
        try {
            et.begin();

            System.out.println("Enter the voter ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Voter voter = em.find(Voter.class, id);

            if (voter != null) {
                System.out.println("Enter the updated voter name:");
                voter.setVoterName(input.nextLine());

                PoliticalParty politicalParty = voter.getPoliticalParty();
                if (politicalParty != null) {
                    System.out.println("Enter the updated political party name:");
                    politicalParty.setPartyName(input.nextLine());
                }

                et.commit();

                System.out.println("Voter updated successfully.");
            } else {
                System.out.println("Voter not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void deleteVoter() {
        try {
            et.begin();

            System.out.println("Enter the voter ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Voter voter = em.find(Voter.class, id);

            if (voter != null) {
                em.remove(voter);
                et.commit();
                System.out.println("Voter deleted successfully.");
            } else {
                System.out.println("Voter not found.");
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
                    example.createVoter();
                    break;
                case 2:
                    example.retrieveVoter();
                    break;
                case 3:
                    example.updateVoter();
                    break;
                case 4:
                    example.deleteVoter();
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

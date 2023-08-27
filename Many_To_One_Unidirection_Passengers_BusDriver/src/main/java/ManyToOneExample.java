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

    public void createPassenger() {
        try {
            et.begin();

            Passenger passenger = new Passenger();
            System.out.println("Enter the passenger name:");
            passenger.setPassengerName(input.nextLine());

            BusDriver busDriver = new BusDriver();
            System.out.println("Enter the bus driver name:");
            busDriver.setDriverName(input.nextLine());

            passenger.setBusDriver(busDriver);

            em.persist(passenger);

            et.commit();

            System.out.println("Passenger and Bus Driver records created successfully.");

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void retrievePassenger() {
        System.out.println("Enter the passenger ID:");
        Long id = input.nextLong();
        input.nextLine(); // Consume the newline character

        Passenger passenger = em.find(Passenger.class, id);

        if (passenger != null) {
            System.out.println("Passenger Name: " + passenger.getPassengerName());
            BusDriver busDriver = passenger.getBusDriver();
            if (busDriver != null) {
                System.out.println("Bus Driver Name: " + busDriver.getDriverName());
            } else {
                System.out.println("No bus driver associated.");
            }
        } else {
            System.out.println("Passenger not found.");
        }
    }

    public void updatePassenger() {
        try {
            et.begin();

            System.out.println("Enter the passenger ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Passenger passenger = em.find(Passenger.class, id);

            if (passenger != null) {
                System.out.println("Enter the updated passenger name:");
                passenger.setPassengerName(input.nextLine());

                BusDriver busDriver = passenger.getBusDriver();
                if (busDriver != null) {
                    System.out.println("Enter the updated bus driver name:");
                    busDriver.setDriverName(input.nextLine());
                }

                et.commit();

                System.out.println("Passenger updated successfully.");
            } else {
                System.out.println("Passenger not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void deletePassenger() {
        try {
            et.begin();

            System.out.println("Enter the passenger ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            Passenger passenger = em.find(Passenger.class, id);

            if (passenger != null) {
                em.remove(passenger);
                et.commit();
                System.out.println("Passenger deleted successfully.");
            } else {
                System.out.println("Passenger not found.");
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
                    example.createPassenger();
                    break;
                case 2:
                    example.retrievePassenger();
                    break;
                case 3:
                    example.updatePassenger();
                    break;
                case 4:
                    example.deletePassenger();
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

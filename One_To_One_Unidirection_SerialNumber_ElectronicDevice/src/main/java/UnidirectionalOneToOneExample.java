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

    public void createDevice() {
        try {
            et.begin();

            ElectronicDevice device = new ElectronicDevice();
            System.out.println("Enter the device name:");
            device.setDeviceName(input.nextLine());

            SerialNumber serialNumber = new SerialNumber();
            System.out.println("Enter the serial number:");
            serialNumber.setSerialNumber(input.nextLine());

            device.setSerialNumber(serialNumber);

            em.persist(device);

            et.commit();

            System.out.println("Electronic Device and Serial Number records created successfully.");

        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void retrieveDevice() {
        System.out.println("Enter the device ID:");
        Long id = input.nextLong();
        input.nextLine(); // Consume the newline character

        ElectronicDevice device = em.find(ElectronicDevice.class, id);

        if (device != null) {
            System.out.println("Device Name: " + device.getDeviceName());
            SerialNumber serialNumber = device.getSerialNumber();
            if (serialNumber != null) {
                System.out.println("Serial Number: " + serialNumber.getSerialNumber());
            } else {
                System.out.println("No serial number associated.");
            }
        } else {
            System.out.println("Device not found.");
        }
    }

    public void updateDevice() {
        try {
            et.begin();

            System.out.println("Enter the device ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            ElectronicDevice device = em.find(ElectronicDevice.class, id);

            if (device != null) {
                System.out.println("Enter the updated device name:");
                device.setDeviceName(input.nextLine());

                SerialNumber serialNumber = device.getSerialNumber();
                if (serialNumber != null) {
                    System.out.println("Enter the updated serial number:");
                    serialNumber.setSerialNumber(input.nextLine());
                }

                et.commit();

                System.out.println("Device updated successfully.");
            } else {
                System.out.println("Device not found.");
            }
        } catch (Exception e) {
            et.rollback();
            e.printStackTrace();
        }
    }

    public void deleteDevice() {
        try {
            et.begin();

            System.out.println("Enter the device ID:");
            Long id = input.nextLong();
            input.nextLine(); // Consume the newline character

            ElectronicDevice device = em.find(ElectronicDevice.class, id);

            if (device != null) {
                em.remove(device);
                et.commit();
                System.out.println("Device deleted successfully.");
            } else {
                System.out.println("Device not found.");
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
                    example.createDevice();
                    break;
                case 2:
                    example.retrieveDevice();
                    break;
                case 3:
                    example.updateDevice();
                    break;
                case 4:
                    example.deleteDevice();
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

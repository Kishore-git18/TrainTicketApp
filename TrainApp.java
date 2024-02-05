import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TrainApp {
    private static TrainService trainService = new TrainService();
    private static Map<String, String> userMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        do {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    purchaseTicket();
                    break;
                case 2:
                    viewUsersAndSeatsBySection();
                    break;
                case 3:
                    removeUser();
                    break;
                case 4:
                    modifyUserSeat();
                    break;
                case 5:
                    viewReceiptDetails();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!exit);

        // Close the scanner
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Choose an action:");
        System.out.println("1. Purchase a ticket");
        System.out.println("2. View users and seats by section");
        System.out.println("3. Remove a user from the train");
        System.out.println("4. Modify a user's seat");
        System.out.println("5. View receipt details for a user");
        System.out.println("6. Exit");
    }

    private static void purchaseTicket() {
        if (!userMap.containsKey("email")) {
            System.out.println("Enter your first name: ");
            String firstName = scanner.nextLine();

            System.out.println("Enter your last name: ");
            String lastName = scanner.nextLine();

            System.out.println("Enter your email address: ");
            String email = scanner.nextLine();

            userMap.put("firstName", firstName);
            userMap.put("lastName", lastName);
            userMap.put("email", email);
        }

        // Hardcoded values for the journey
        String from = "London";
        String to = "France";
        double price = 20.0;

        // Purchase a ticket
        Ticket ticket = trainService.purchaseTicket(from, to, userMap.get("firstName") + " " + userMap.get("lastName"), userMap.get("email"), price);
        System.out.println("Ticket purchased. Receipt Details: " + ticket);
    }

    private static void viewUsersAndSeatsBySection() {
        System.out.println("Enter section (A or B): ");
        String sectionToView = scanner.next();
        System.out.println("Users and Seats in Section " + sectionToView + ": " +
                trainService.getUsersAndSeatsBySection(sectionToView));
    }

    private static void removeUser() {
        System.out.println("Enter user email to remove: ");
        String userToRemove = scanner.next();
        trainService.removeUser(userToRemove);
        System.out.println("User removed. Users and Seats in Section A: " +
                trainService.getUsersAndSeatsBySection("A"));
    }

    private static void modifyUserSeat() {
        System.out.println("Enter user email to modify: ");
        String userToModify = scanner.next();
        System.out.println("Enter new seat section (A or B): ");
        String newSeatSection = scanner.next();
        trainService.modifyUserSeat(userToModify, newSeatSection);
        System.out.println("User's seat modified. Users and Seats in Section " + newSeatSection + ": " +
                trainService.getUsersAndSeatsBySection(newSeatSection));
    }

    private static void viewReceiptDetails() {
        System.out.println("Enter user email to view receipt details: ");
        String userToView = scanner.next();
        Ticket userTicket = trainService.getTicketDetails(userToView);
        if (userTicket != null) {
            System.out.println("Receipt Details for User: " + userTicket);
        } else {
            System.out.println("User not found or no ticket purchased for the user.");
        }
    }
}

import java.util.HashMap;
import java.util.Map;

public class TrainService {
    private static Map<String, Ticket> tickets = new HashMap<>();

    public Ticket purchaseTicket(String from, String to, String userName, String userEmail, double price) {
        Ticket ticket = new Ticket();
        ticket.setFrom(from);
        ticket.setTo(to);
        ticket.setUserName(userName);
        ticket.setUserEmail(userEmail);
        ticket.setPrice(price);
        ticket.setSeatSection("A"); // Assuming everyone starts in section A

        tickets.put(userEmail, ticket);
        return ticket;
    }

    public Map<String, String> getUsersAndSeatsBySection(String section) {
        Map<String, String> userSeats = new HashMap<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getSeatSection().equals(section)) {
                userSeats.put(ticket.getUserName(), ticket.getSeatSection());
            }
        }
        return userSeats;
    }

    public void removeUser(String userEmail) {
        tickets.remove(userEmail);
    }

    public void modifyUserSeat(String userEmail, String newSeatSection) {
        Ticket ticket = tickets.get(userEmail);
        if (ticket != null) {
            ticket.setSeatSection(newSeatSection);
        }
    }

    public Ticket getTicketDetails(String userEmail) {
        return tickets.get(userEmail);
    }

    // Other methods remain the same...
}

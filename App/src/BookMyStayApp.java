/*
 * Book My Stay App
 * Use Case 8: Booking History & Reporting
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program tracks all confirmed bookings in a history list
 * and generates simple reports, enabling administrative visibility.
 */

import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId);
    }
}

// Booking History Service
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
        System.out.println("Added to history: " + reservation.getReservationId());
    }

    // Retrieve all reservations
    public List<Reservation> getHistory() {
        return new ArrayList<>(history); // return copy for safety
    }
}

// Booking Report Service
class BookingReportService {

    public void displayAllReservations(List<Reservation> reservations) {
        System.out.println("\n=== All Confirmed Reservations ===");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        for (Reservation r : reservations) {
            r.display();
        }
    }

    public void displaySummary(List<Reservation> reservations) {
        System.out.println("\n=== Booking Summary ===");

        if (reservations.isEmpty()) {
            System.out.println("No reservations to summarize.");
            return;
        }

        Map<String, Integer> roomTypeCount = new HashMap<>();
        for (Reservation r : reservations) {
            roomTypeCount.put(r.getRoomType(),
                    roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("Reservations per Room Type:");
        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + " -> " + roomTypeCount.get(type));
        }

        System.out.println("Total Reservations: " + reservations.size());
    }
}

// Main Application
public class BookMyStayApp {
    public static void main(String[] args) {

        // Sample confirmed reservations
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        history.addReservation(new Reservation("RES101", "Alice", "Single", "SI101"));
        history.addReservation(new Reservation("RES102", "Bob", "Double", "DO102"));
        history.addReservation(new Reservation("RES103", "Charlie", "Suite", "SU103"));
        history.addReservation(new Reservation("RES104", "David", "Single", "SI104"));

        // Retrieve history
        List<Reservation> allReservations = history.getHistory();

        // Display all reservations
        reportService.displayAllReservations(allReservations);

        // Display summary report
        reportService.displaySummary(allReservations);
    }
}
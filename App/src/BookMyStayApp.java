/*
 * Book My Stay App
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program allows guests to cancel confirmed bookings,
 * safely restoring inventory and booking history using rollback logic.
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

    public String getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
    public String getRoomId() { return roomId; }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType +
                " | Room ID: " + roomId);
    }
}

// Inventory Service
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    public void addRoom(String type, int count) {
        availability.put(type, count);
    }

    public void increaseRoom(String type) {
        availability.put(type, availability.getOrDefault(type, 0) + 1);
    }

    public void decreaseRoom(String type) {
        availability.put(type, availability.getOrDefault(type, 0) - 1);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void displayInventory() {
        System.out.println("\nInventory Status:");
        for (String type : availability.keySet()) {
            System.out.println(type + " -> " + availability.get(type));
        }
    }
}

// Booking Service
class BookingService {
    private Inventory inventory;
    private Map<String, Reservation> confirmedBookings = new HashMap<>();
    private Stack<String> releasedRoomIds = new Stack<>();

    public BookingService(Inventory inventory) {
        this.inventory = inventory;
    }

    // Book a room
    public Reservation book(String reservationId, String guestName, String roomType) {
        if (inventory.getAvailability(roomType) <= 0) {
            System.out.println("Booking Failed: No rooms available for " + roomType);
            return null;
        }

        inventory.decreaseRoom(roomType);

        // Generate room ID
        String roomId = roomType.substring(0, 2).toUpperCase() + (int)(Math.random() * 1000);

        Reservation reservation = new Reservation(reservationId, guestName, roomType, roomId);
        confirmedBookings.put(reservationId, reservation);

        System.out.println("Booking successful: " + reservationId);
        return reservation;
    }

    // Cancel a booking
    public void cancel(String reservationId) {
        Reservation res = confirmedBookings.get(reservationId);
        if (res == null) {
            System.out.println("Cancellation Failed: Reservation ID not found or already cancelled.");
            return;
        }

        // Rollback inventory
        inventory.increaseRoom(res.getRoomType());

        // Track released room ID
        releasedRoomIds.push(res.getRoomId());

        // Remove from confirmed bookings
        confirmedBookings.remove(reservationId);

        System.out.println("Cancellation successful: " + reservationId);
    }

    // Display confirmed bookings
    public void displayConfirmedBookings() {
        System.out.println("\nConfirmed Reservations:");
        if (confirmedBookings.isEmpty()) {
            System.out.println("No confirmed bookings.");
            return;
        }
        for (Reservation r : confirmedBookings.values()) {
            r.display();
        }
    }

    // Display recently released rooms
    public void displayReleasedRooms() {
        System.out.println("\nRecently Released Room IDs (Rollback Stack):");
        if (releasedRoomIds.isEmpty()) {
            System.out.println("No rooms released yet.");
            return;
        }
        for (String id : releasedRoomIds) {
            System.out.println(id);
        }
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Double", 1);

        BookingService bookingService = new BookingService(inventory);

        // Bookings
        bookingService.book("RES101", "Alice", "Single");
        bookingService.book("RES102", "Bob", "Double");
        bookingService.book("RES103", "Charlie", "Single");

        // Display confirmed bookings
        bookingService.displayConfirmedBookings();
        inventory.displayInventory();

        // Cancellations
        bookingService.cancel("RES102"); // valid cancellation
        bookingService.cancel("RES999"); // invalid cancellation
        bookingService.cancel("RES102"); // already cancelled

        // Display updated bookings and released rooms
        bookingService.displayConfirmedBookings();
        bookingService.displayReleasedRooms();
        inventory.displayInventory();
    }
}
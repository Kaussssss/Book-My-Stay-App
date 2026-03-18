/*
 * Book My Stay App
 * Use Case 9: Error Handling & Validation
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program introduces validation and error handling
 * for bookings, preventing invalid inputs and inconsistent states.
 */

import java.util.*;

// Custom Exception for invalid booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

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

// Inventory Service with validation
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    public void addRoom(String type, int count) throws InvalidBookingException {
        if (count < 0) throw new InvalidBookingException("Cannot add negative rooms!");
        availability.put(type, count);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) throws InvalidBookingException {
        int available = availability.getOrDefault(type, 0);
        if (available <= 0) throw new InvalidBookingException("No rooms available for: " + type);
        availability.put(type, available - 1);
    }

    public void displayInventory() {
        System.out.println("\nInventory Status:");
        for (String type : availability.keySet()) {
            System.out.println(type + " -> " + availability.get(type));
        }
    }
}

// Booking Service with validation
class BookingService {
    private Inventory inventory;
    private Set<String> allocatedRoomIds = new HashSet<>();

    public BookingService(Inventory inventory) {
        this.inventory = inventory;
    }

    public Reservation processBooking(String reservationId, String guestName, String roomType) {
        try {
            if (guestName == null || guestName.isEmpty())
                throw new InvalidBookingException("Guest name cannot be empty.");

            if (!inventoryHasRoomType(roomType))
                throw new InvalidBookingException("Invalid room type: " + roomType);

            inventory.reduceRoom(roomType);

            // Generate unique room ID
            String roomId;
            do {
                roomId = roomType.substring(0, 2).toUpperCase() + (int)(Math.random() * 1000);
            } while (allocatedRoomIds.contains(roomId));

            allocatedRoomIds.add(roomId);

            Reservation reservation = new Reservation(reservationId, guestName, roomType, roomId);
            System.out.println("Booking successful for " + guestName);
            return reservation;

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
            return null;
        }
    }

    private boolean inventoryHasRoomType(String type) {
        return inventory.getAvailability(type) > 0;
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) throws InvalidBookingException {

        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Double", 1);

        BookingService bookingService = new BookingService(inventory);
        List<Reservation> confirmedReservations = new ArrayList<>();

        // Valid booking
        confirmedReservations.add(bookingService.processBooking("RES101", "Alice", "Single"));

        // Invalid guest name
        confirmedReservations.add(bookingService.processBooking("RES102", "", "Single"));

        // Invalid room type
        confirmedReservations.add(bookingService.processBooking("RES103", "Bob", "Suite"));

        // Overbooking scenario
        confirmedReservations.add(bookingService.processBooking("RES104", "Charlie", "Double"));
        confirmedReservations.add(bookingService.processBooking("RES105", "David", "Double"));

        // Display confirmed bookings
        System.out.println("\nConfirmed Reservations:");
        for (Reservation r : confirmedReservations) {
            if (r != null) r.display();
        }

        inventory.displayInventory();
    }
}
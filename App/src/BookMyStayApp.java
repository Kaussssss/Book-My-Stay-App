/*
 * Book My Stay App
 * Use Case 12: Data Persistence & System Recovery
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program demonstrates persistence of booking and inventory state.
 * The system saves state to a file before shutdown and restores it on startup.
 */

import java.io.*;
import java.util.*;

// Reservation class implements Serializable
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
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

// Inventory class implements Serializable
class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Integer> availability = new HashMap<>();

    public Inventory() {}

    public void addRoom(String type, int count) {
        availability.put(type, count);
    }

    public boolean bookRoom(String type) {
        int available = availability.getOrDefault(type, 0);
        if (available > 0) {
            availability.put(type, available - 1);
            return true;
        }
        return false;
    }

    public void cancelRoom(String type) {
        availability.put(type, availability.getOrDefault(type, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\nInventory Status:");
        for (String type : availability.keySet()) {
            System.out.println(type + " -> " + availability.get(type));
        }
    }
}

// BookingService class implements Serializable
class BookingService implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Reservation> confirmedBookings = new HashMap<>();
    private transient Inventory inventory; // transient, restored manually

    public BookingService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setInventory(Inventory inventory) { this.inventory = inventory; }

    public Reservation book(String reservationId, String guestName, String roomType) {
        if (!inventory.bookRoom(roomType)) {
            System.out.println("Booking Failed for " + guestName + ": No rooms available for " + roomType);
            return null;
        }
        String roomId = roomType.substring(0,2).toUpperCase() + (int)(Math.random()*1000);
        Reservation res = new Reservation(reservationId, guestName, roomType, roomId);
        confirmedBookings.put(reservationId, res);
        System.out.println("Booking Successful for " + guestName + " | Room ID: " + roomId);
        return res;
    }

    public void cancel(String reservationId) {
        Reservation res = confirmedBookings.get(reservationId);
        if (res != null) {
            inventory.cancelRoom(res.getRoomType());
            confirmedBookings.remove(reservationId);
            System.out.println("Cancellation Successful for " + reservationId);
        } else {
            System.out.println("Cancellation Failed: Reservation not found.");
        }
    }

    public void displayConfirmedBookings() {
        System.out.println("\nConfirmed Reservations:");
        if (confirmedBookings.isEmpty()) {
            System.out.println("No confirmed bookings.");
        } else {
            for (Reservation r : confirmedBookings.values()) {
                r.display();
            }
        }
    }
}

// Persistence Service
class PersistenceService {
    private static final String FILE_PATH = "bookMyStayAppState.ser";

    public static void saveState(Inventory inventory, BookingService bookingService) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(inventory);
            oos.writeObject(bookingService);
            System.out.println("\nSystem state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    public static Object[] loadState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Inventory inv = (Inventory) ois.readObject();
            BookingService bs = (BookingService) ois.readObject();
            bs.setInventory(inv);
            System.out.println("System state restored successfully.");
            return new Object[]{inv, bs};
        } catch (FileNotFoundException e) {
            System.out.println("No saved state found. Starting fresh.");
            return null;
        } catch (Exception e) {
            System.out.println("Error restoring state: " + e.getMessage());
            return null;
        }
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) {

        Object[] state = PersistenceService.loadState();
        Inventory inventory;
        BookingService bookingService;

        if (state != null) {
            inventory = (Inventory) state[0];
            bookingService = (BookingService) state[1];
        } else {
            inventory = new Inventory();
            inventory.addRoom("Single", 2);
            inventory.addRoom("Double", 1);
            bookingService = new BookingService(inventory);
        }

        // Sample bookings
        bookingService.book("RES101", "Alice", "Single");
        bookingService.book("RES102", "Bob", "Double");

        bookingService.displayConfirmedBookings();
        inventory.displayInventory();

        // Save state
        PersistenceService.saveState(inventory, bookingService);
    }
}
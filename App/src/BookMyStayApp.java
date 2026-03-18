/*
 * Book My Stay App
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program demonstrates thread-safe booking operations,
 * simulating multiple guests booking simultaneously.
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

// Inventory class
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    public Inventory(Map<String, Integer> initialInventory) {
        availability.putAll(initialInventory);
    }

    // Thread-safe decrement
    public synchronized boolean bookRoom(String type) {
        int available = availability.getOrDefault(type, 0);
        if (available > 0) {
            availability.put(type, available - 1);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void displayInventory() {
        System.out.println("\nInventory Status:");
        for (String type : availability.keySet()) {
            System.out.println(type + " -> " + availability.get(type));
        }
    }
}

// Thread-safe Booking Service
class BookingService {
    private Inventory inventory;
    private Map<String, Reservation> confirmedBookings = Collections.synchronizedMap(new HashMap<>());

    public BookingService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void book(String reservationId, String guestName, String roomType) {
        synchronized (inventory) { // critical section
            if (!inventory.bookRoom(roomType)) {
                System.out.println("Booking Failed for " + guestName + ": No rooms available for " + roomType);
                return;
            }

            String roomId = roomType.substring(0, 2).toUpperCase() + (int)(Math.random() * 1000);
            Reservation res = new Reservation(reservationId, guestName, roomType, roomId);
            confirmedBookings.put(reservationId, res);
            System.out.println("Booking Successful for " + guestName + " | Room ID: " + roomId);
        }
    }

    public void displayConfirmedBookings() {
        System.out.println("\nConfirmed Reservations:");
        synchronized (confirmedBookings) {
            for (Reservation r : confirmedBookings.values()) {
                r.display();
            }
        }
    }
}

// Thread representing a guest booking
class GuestThread extends Thread {
    private BookingService bookingService;
    private String reservationId;
    private String guestName;
    private String roomType;

    public GuestThread(BookingService bookingService, String reservationId, String guestName, String roomType) {
        this.bookingService = bookingService;
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        bookingService.book(reservationId, guestName, roomType);
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) throws InterruptedException {
        Map<String, Integer> initialInventory = new HashMap<>();
        initialInventory.put("Single", 2);
        initialInventory.put("Double", 1);

        Inventory inventory = new Inventory(initialInventory);
        BookingService bookingService = new BookingService(inventory);

        // Create guest threads simulating concurrent bookings
        Thread t1 = new GuestThread(bookingService, "RES101", "Alice", "Single");
        Thread t2 = new GuestThread(bookingService, "RES102", "Bob", "Single");
        Thread t3 = new GuestThread(bookingService, "RES103", "Charlie", "Single");
        Thread t4 = new GuestThread(bookingService, "RES104", "David", "Double");
        Thread t5 = new GuestThread(bookingService, "RES105", "Eve", "Double");

        // Start threads
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start();

        // Wait for all threads to finish
        t1.join(); t2.join(); t3.join(); t4.join(); t5.join();

        // Display results
        bookingService.displayConfirmedBookings();
        inventory.displayInventory();
    }
}
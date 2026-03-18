/*
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program processes booking requests from a queue,
 * allocates rooms with unique IDs, updates inventory,
 * and prevents double-booking using Set.
 */

import java.util.*;

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Inventory Service (State Management)
class Inventory {
    private Map<String, Integer> availability;

    public Inventory() {
        availability = new HashMap<>();
    }

    public void addRoom(String type, int count) {
        availability.put(type, count);
    }

    public int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        availability.put(type, availability.get(type) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : availability.keySet()) {
            System.out.println(type + " -> " + availability.get(type));
        }
    }
}

// Booking Request Queue (FIFO)
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNext() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Booking Service (Core Logic)
class BookingService {

    private Inventory inventory;

    // Track all allocated room IDs (global uniqueness)
    private Set<String> allocatedRoomIds = new HashSet<>();

    // Map room type -> allocated room IDs
    private Map<String, Set<String>> roomAllocations = new HashMap<>();

    public BookingService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(Reservation reservation) {
        String type = reservation.getRoomType();

        System.out.println("\nProcessing booking for " + reservation.getGuestName());

        // Check availability
        if (inventory.getAvailability(type) <= 0) {
            System.out.println("Booking Failed: No rooms available for " + type);
            return;
        }

        // Generate unique room ID
        String roomId;
        do {
            roomId = type.substring(0, 2).toUpperCase() + (int)(Math.random() * 1000);
        } while (allocatedRoomIds.contains(roomId));

        // Allocate room
        allocatedRoomIds.add(roomId);

        roomAllocations.putIfAbsent(type, new HashSet<>());
        roomAllocations.get(type).add(roomId);

        // Update inventory (atomic step)
        inventory.reduceRoom(type);

        // Confirm booking
        System.out.println("Booking Confirmed!");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + type);
        System.out.println("Room ID: " + roomId);
    }

    public void displayAllocations() {
        System.out.println("\nRoom Allocations:");
        for (String type : roomAllocations.keySet()) {
            System.out.println(type + " -> " + roomAllocations.get(type));
        }
    }
}

// Main Application
public class BookMyStayApp {
    public static void main(String[] args) {

        // Setup inventory
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Double", 1);
        inventory.addRoom("Suite", 1);

        // Setup booking queue
        BookingQueue queue = new BookingQueue();
        queue.addRequest(new Reservation("Alice", "Single"));
        queue.addRequest(new Reservation("Bob", "Single"));
        queue.addRequest(new Reservation("Charlie", "Single")); // Should fail
        queue.addRequest(new Reservation("David", "Suite"));

        // Booking service
        BookingService service = new BookingService(inventory);

        // Process all bookings (FIFO)
        while (!queue.isEmpty()) {
            service.processBooking(queue.getNext());
        }

        // Show results
        service.displayAllocations();
        inventory.displayInventory();
    }
}
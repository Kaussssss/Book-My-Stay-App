/*
 * Book My Stay App
 * Use Case 4: Room Search & Availability Check
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program demonstrates a read-only room search system
 * where available rooms are displayed without modifying inventory state.
 */

import java.util.*;

// Room class (Domain Model)
class Room {
    private String type;
    private double price;
    private List<String> amenities;

    public Room(String type, double price, List<String> amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: $" + price);
        System.out.println("Amenities: " + amenities);
        System.out.println("----------------------------");
    }
}

// Inventory class (State Holder)
class Inventory {
    private Map<String, Integer> roomAvailability;

    public Inventory() {
        roomAvailability = new HashMap<>();
    }

    public void addRoom(String type, int count) {
        roomAvailability.put(type, count);
    }

    // Read-only access
    public int getAvailability(String type) {
        return roomAvailability.getOrDefault(type, 0);
    }

    public Set<String> getAllRoomTypes() {
        return roomAvailability.keySet();
    }
}

// Search Service (Read-only logic)
class SearchService {
    private Inventory inventory;
    private Map<String, Room> roomDetails;

    public SearchService(Inventory inventory, Map<String, Room> roomDetails) {
        this.inventory = inventory;
        this.roomDetails = roomDetails;
    }

    public void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:\n");

        for (String type : inventory.getAllRoomTypes()) {
            int available = inventory.getAvailability(type);

            // Validation: show only available rooms
            if (available > 0) {
                Room room = roomDetails.get(type);

                if (room != null) { // Defensive Programming
                    room.displayDetails();
                    System.out.println("Available Count: " + available);
                    System.out.println("============================");
                }
            }
        }
    }
}

// Main Application
public class BookMyStayApp {
    public static void main(String[] args) {

        // Initialize inventory
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 5);
        inventory.addRoom("Double", 0); // Unavailable
        inventory.addRoom("Suite", 2);

        // Create room details
        Map<String, Room> roomDetails = new HashMap<>();

        roomDetails.put("Single", new Room(
                "Single", 100,
                Arrays.asList("WiFi", "TV", "AC")));

        roomDetails.put("Double", new Room(
                "Double", 180,
                Arrays.asList("WiFi", "TV", "AC", "Mini Bar")));

        roomDetails.put("Suite", new Room(
                "Suite", 300,
                Arrays.asList("WiFi", "TV", "AC", "Pool", "Jacuzzi")));

        // Search Service
        SearchService searchService = new SearchService(inventory, roomDetails);

        // Guest initiates search
        searchService.searchAvailableRooms();
    }
}
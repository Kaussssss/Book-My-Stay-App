import java.util.HashMap;
import java.util.Map;

/**
 * RoomInventory
 *
 * Manages centralized room availability using a HashMap.
 * This class acts as the single source of truth for inventory state.
 *
 * @author Kaustubh Chauhan
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes the room inventory
     */
    public RoomInventory() {

        inventory = new HashMap<>();

        // Register room types with their availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    /**
     * Retrieves availability for a given room type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability for a specific room type
     */
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    /**
     * Displays the current inventory state
     */
    public void displayInventory() {

        System.out.println("---- Current Room Inventory ----");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }

        System.out.println();
    }
}


/**
 * UseCase3InventorySetup
 *
 * Demonstrates centralized inventory management using HashMap.
 * Replaces scattered availability variables with a single source of truth.
 *
 * Application: Book My Stay App
 * Version: 3.1
 *
 * @author Niranjan
 * @version 3.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App");
        System.out.println("Application Version: 3.1\n");

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        // Retrieve availability
        int singleAvailability = inventory.getAvailability("Single Room");
        System.out.println("Single Room Availability: " + singleAvailability);

        // Update availability example
        System.out.println("\nUpdating Single Room availability...");
        inventory.updateAvailability("Single Room", singleAvailability - 1);

        // Display updated inventory
        System.out.println();
        inventory.displayInventory();

        System.out.println("Application terminated.");
    }
}
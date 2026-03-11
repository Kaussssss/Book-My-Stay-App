/**
 * UseCase2RoomInitialization
 *
 * Demonstrates object modeling using abstraction and inheritance
 * for the Book My Stay App.
 *
 * Version: 2.1
 *
 * @author Kaustubh Chauhan
 * @version 2.1
 */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : $" + price);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 80.0);
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 120.0);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 250.0);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay App");
        System.out.println("Application Version: 2.1\n");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailability = 10;
        int doubleAvailability = 6;
        int suiteAvailability = 3;

        System.out.println("---- Room Details ----\n");

        single.displayRoomDetails();
        System.out.println("Available : " + singleAvailability + "\n");

        dbl.displayRoomDetails();
        System.out.println("Available : " + doubleAvailability + "\n");

        suite.displayRoomDetails();
        System.out.println("Available : " + suiteAvailability + "\n");

        System.out.println("Application terminated.");
    }
}
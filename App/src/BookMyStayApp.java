/*
 * Book My Stay App
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Author: Kaustubh Chauhan
 * Registration No: RA2411030010032
 *
 * Description:
 * This program demonstrates handling booking requests using a Queue
 * to ensure First-Come-First-Served (FIFO) processing.
 * No inventory updates or room allocation is done at this stage.
 */

import java.util.*;

// Reservation class (represents booking request)
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

    public void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

// Booking Request Queue (FIFO handling)
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    // View all requests (without removing)
    public void viewRequests() {
        System.out.println("\nCurrent Booking Queue:");
        for (Reservation r : queue) {
            r.display();
        }
    }

    // Process next request (FIFO)
    public Reservation processNextRequest() {
        return queue.poll(); // removes first request
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Main Application
public class BookMyStayApp {
    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulating guest booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single"));
        bookingQueue.addRequest(new Reservation("Bob", "Suite"));
        bookingQueue.addRequest(new Reservation("Charlie", "Double"));

        // Display queue (arrival order preserved)
        bookingQueue.viewRequests();

        // Demonstrate FIFO processing (no allocation, just processing)
        System.out.println("\nProcessing Requests (FIFO Order):");

        while (!bookingQueue.isEmpty()) {
            Reservation r = bookingQueue.processNextRequest();
            System.out.print("Processing -> ");
            r.display();
        }
    }
}
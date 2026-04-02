# Book My Stay App
## Use Case 11: Concurrent Booking Simulation (Thread Safety)


---

## 📌 Description
This use case demonstrates safe multi-user booking using Java threads.
It simulates concurrent guests booking rooms simultaneously and ensures
thread-safe access to inventory and confirmed bookings.

---

## 🎯 Goal
Prevent race conditions and double-booking under concurrent execution.

---

## 👤 Actors
- Multiple Guests → Submit bookings concurrently
- Concurrent Booking Processor → Processes bookings in threads

---

## 🔄 Flow

1. Multiple guests submit booking requests simultaneously.
2. Threads access shared inventory using synchronized blocks.
3. Booking service allocates rooms safely inside critical sections.
4. Final confirmed bookings and inventory remain consistent.

---

## 🧠 Concepts Used

- Race conditions and thread safety
- Shared mutable state
- Critical sections
- Synchronized access to inventory and booking map

---

## ✅ Features

- Simulates multiple simultaneous booking requests
- Thread-safe inventory updates
- Prevents double-booking
- Consistent system state under concurrency

---

## ⚠️ Previous Drawback

- Earlier use cases assumed single-threaded execution
- Unsafe under multi-user real-world conditions

---

## ▶️ How to Run

### Compile:
javac UseCase11ConcurrentBookingSimulation.java


### Run:

java UseCase11ConcurrentBookingSimulation


---

## 📌 Sample Output (Order may vary due to threads)

Booking Successful for Alice | Room ID: SI123  
Booking Successful for Bob | Room ID: SI456  
Booking Failed for Charlie: No rooms available for Single  
Booking Successful for David | Room ID: DO789  
Booking Failed for Eve: No rooms available for Double

Confirmed Reservations:  
Reservation ID: RES101 | Guest: Alice | Room Type: Single | Room ID: SI123  
Reservation ID: RES102 | Guest: Bob | Room Type: Single | Room ID: SI456  
Reservation ID: RES104 | Guest: David | Room Type: Double | Room ID: DO789

Inventory Status:  
Single -> 0  
Double -> 0

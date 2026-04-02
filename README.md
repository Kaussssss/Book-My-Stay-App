# Book My Stay App
## Use Case 10: Booking Cancellation & Inventory Rollback


---

## 📌 Description
This use case allows guests to safely cancel confirmed bookings.
It restores inventory and booking state using rollback logic with a stack.

---

## 🎯 Goal
Ensure predictable recovery and inventory consistency after cancellations.

---

## 👤 Actors
- Guest → Requests cancellation
- Cancellation Service → Validates and rolls back state

---

## 🔄 Flow

1. Guest requests cancellation.
2. System checks reservation existence.
3. Inventory count for room type is incremented.
4. Released room ID is pushed to rollback stack.
5. Booking removed from confirmed bookings.
6. System state remains consistent.

---

## 🧠 Concepts Used

- Stack<String> for LIFO rollback of room IDs
- Controlled mutation of system state
- Inventory restoration after cancellation
- Validation of reservation existence

---

## ✅ Features

- Safe booking cancellation
- Inventory rollback
- Prevention of cancelling non-existent or already cancelled bookings
- Tracking recently released rooms
- Consistent system state

---

## ⚠️ Previous Drawback

- UC9 validated input but did not allow reversing valid bookings.
- Without rollback, cancellations could corrupt inventory.

---

## ▶️ How to Run

### Compile:

javac UseCase10BookingCancellation.java


### Run:

java UseCase10BookingCancellation


---

## 📌 Sample Output

Booking successful: RES101  
Booking successful: RES102  
Booking successful: RES103

Confirmed Reservations:  
Reservation ID: RES101 | Guest: Alice | Room Type: Single | Room ID: SI123  
Reservation ID: RES102 | Guest: Bob | Room Type: Double | Room ID: DO456  
Reservation ID: RES103 | Guest: Charlie | Room Type: Single | Room ID: SI789

Inventory Status:  
Single -> 0  
Double -> 0

Cancellation successful: RES102  
Cancellation Failed: Reservation ID not found or already cancelled.  
Cancellation Failed: Reservation ID not found or already cancelled.

Confirmed Reservations:  
Reservation ID: RES101 | Guest: Alice | Room Type: Single | Room ID: SI123  
Reservation ID: RES103 | Guest: Charlie | Room Type: Single | Room ID: SI789

Recently Released Room IDs (Rollback Stack):  
DO456

Inventory Status:  
Single -> 0  
Double -> 1

# Book My Stay App
## Use Case 12: Data Persistence & System Recovery


---

## 📌 Description
This use case ensures that the booking system persists its state.
Confirmed bookings and inventory are saved to a file and restored on startup.

---

## 🎯 Goal
Enable system recovery after restarts or crashes.

---

## 👤 Actors
- System → Saves and restores state
- Persistence Service → Handles serialization and deserialization

---

## 🔄 Flow

1. System prepares for shutdown.
2. Inventory and bookings are serialized to a file.
3. System restarts.
4. Persisted data is deserialized.
5. Inventory and booking state are restored into memory.
6. System resumes operation with recovered state.

---

## 🧠 Concepts Used

- Stateful application
- Persistence
- Serialization & Deserialization
- Recovery of inventory snapshot
- Handling missing/corrupted files

---

## ✅ Features

- Save confirmed bookings and inventory
- Restore system state after restart
- Prevents data loss
- Handles missing or corrupted files gracefully

---

## ▶️ How to Run

### Compile:

javac UseCase12DataPersistenceRecovery.java


### Run:

java UseCase12DataPersistenceRecovery


---

## 📌 Sample Output

No saved state found. Starting fresh.  
Booking Successful for Alice | Room ID: SI123  
Booking Successful for Bob | Room ID: DO456

Confirmed Reservations:  
Reservation ID: RES101 | Guest: Alice | Room Type: Single | Room ID: SI123  
Reservation ID: RES102 | Guest: Bob | Room Type: Double | Room ID: DO456

Inventory Status:  
Single -> 1  
Double -> 0

System state saved successfully.

# Book My Stay App
## Use Case 6: Reservation Confirmation & Room Allocation



## 📌 Project Description
This use case processes booking requests and allocates rooms safely
while preventing double-booking and maintaining inventory consistency.

---

## 🎯 Goal
Confirm bookings by assigning unique room IDs and updating inventory.

---

## 👤 Actors
- Booking Service → Handles allocation
- Inventory Service → Maintains availability

---

## 🔄 Flow of Program

1. Booking request is taken from queue (FIFO).
2. System checks room availability.
3. Unique room ID is generated.
4. Room ID is checked for duplication using Set.
5. Room is allocated.
6. Inventory is updated immediately.
7. Booking is confirmed.

---

## 🧠 Concepts Used

### 1. Set Data Structure
Ensures unique room IDs (no duplicates).

### 2. HashMap
Maps room type → allocated room IDs.

### 3. FIFO Queue
Processes requests in order.

### 4. Atomic Operations
Allocation + inventory update done together.

### 5. Inventory Synchronization
Real-time update after booking.

---

## ✅ Key Features
- Prevents double-booking
- Guarantees unique room IDs
- Maintains consistent inventory
- Fair booking processing

---

## ⚠️ Drawback (Previous Use Case)
- Requests were queued but not processed
- No room assignment or validation

---

## ▶️ How to Run

### Compile:
javac BookMyStayApp.java


### Run:

java BookMyStayApp


---

## 📌 Sample Output

Processing booking for Alice  
Booking Confirmed!  
Room ID: SI123

Processing booking for Bob  
Booking Confirmed!

Processing booking for Charlie  
Booking Failed: No rooms available

---

## 🚀 Summary
This use case ensures safe and consistent room allocation
using Set for uniqueness and immediate inventory updates.

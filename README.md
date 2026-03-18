# Book My Stay App
## Use Case 5: Booking Request (First-Come-First-Served)

### Author:
Kaustubh Chauhan  
RA2411030010032

---

## 📌 Project Description
This use case introduces a booking request mechanism using a Queue
to handle multiple booking requests fairly.

---

## 🎯 Goal
Ensure fair handling of booking requests by preserving arrival order
using FIFO (First-Come-First-Served).

---

## 👤 Actors
- Reservation → Represents a booking request
- Booking Request Queue → Stores and manages requests

---

## 🔄 Flow of Program

1. Guest submits booking request.
2. Request is added to queue.
3. Queue maintains insertion order.
4. Requests wait for processing.
5. Processing happens in FIFO order.
6. No room allocation or inventory update occurs.

---

## 🧠 Concepts Used

### 1. Queue Data Structure
Used to store booking requests.

### 2. FIFO Principle
Ensures first request is processed first.

### 3. Fairness
All users are treated equally based on arrival time.

### 4. Request Ordering
Queue maintains order automatically.

### 5. Decoupling
Request intake is separated from allocation logic.

---

## ✅ Key Features
- Fair booking request handling
- Maintains strict order of requests
- No risk of request skipping
- Clean separation from inventory logic

---

## ⚠️ Drawback (Previous Use Case)
- No booking request handling
- No fairness in simultaneous bookings

---

## ▶️ How to Run

### Compile:
javac BookMyStayApp.java


### Run:

java BookMyStayApp


---

## 📌 Sample Output

Request added for Alice  
Request added for Bob  
Request added for Charlie

Current Booking Queue:  
Guest: Alice | Room Type: Single  
Guest: Bob | Room Type: Suite  
Guest: Charlie | Room Type: Double

Processing Requests (FIFO Order):  
Processing -> Guest: Alice | Room Type: Single  
Processing -> Guest: Bob | Room Type: Suite  
Processing -> Guest: Charlie | Room Type: Double

---

## 🚀 Summary
This use case ensures fair handling of booking requests using FIFO queues,
preparing the system for controlled allocation in future use cases.
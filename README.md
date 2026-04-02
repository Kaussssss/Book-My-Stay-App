# Book My Stay App
## Use Case 4: Room Search & Availability Check


---

## 📌 Project Description
This project demonstrates a Hotel Booking Management System using Core Java.
Use Case 4 focuses on searching available rooms without modifying system state.

---

## 🎯 Goal
Allow users (guests) to view available rooms and their details safely using read-only operations.

---

## 👤 Actors
- Guest → Initiates search
- Search Service → Handles read-only logic

---

## 🔄 Flow of Program

1. System initializes room inventory.
2. Room details (price, amenities) are stored separately.
3. Guest initiates room search.
4. Search service:
    - Retrieves room availability from inventory
    - Filters out unavailable rooms
    - Fetches room details
5. Displays only available rooms.
6. No changes are made to inventory.

---

## 🧠 Concepts Used

### 1. Read-Only Access
Search operation does not modify inventory.

### 2. Separation of Concerns
- Inventory → Stores availability
- Room → Stores details
- SearchService → Handles logic

### 3. Defensive Programming
Checks for null and valid data before display.

### 4. Validation Logic
Only rooms with availability > 0 are shown.

### 5. Domain Model
Room class encapsulates room-related information.

---

## ✅ Key Features
- Displays only available rooms
- Clean separation between logic and data
- Prevents accidental inventory updates

---

## ⚠️ Drawback (Previous Use Case)
- No separation between read/write operations
- Risk of modifying inventory during search

---

## ▶️ How to Run

### Compile:
javac BookMyStayApp.java


### Run:

java BookMyStayApp


---

## 📌 Sample Output

Available Rooms:

Room Type: Single  
Price: $100  
Amenities: [WiFi, TV, AC]  
Available Count: 5

Room Type: Suite  
Price: $300  
Amenities: [WiFi, TV, AC, Pool, Jacuzzi]  
Available Count: 2

---

## 🚀 Summary
This use case ensures safe data access using read-only operations while maintaining system stability an

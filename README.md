# Book My Stay App
## Use Case 7: Add-On Service Selection



---

## 📌 Project Description
This use case extends the booking system to support optional services
like WiFi, breakfast, and spa without modifying core booking logic.

---

## 🎯 Goal
Allow guests to attach multiple add-on services to a reservation.

---

## 👤 Actors
- Guest → Selects services
- Add-On Service → Represents optional feature
- Add-On Service Manager → Manages mapping

---

## 🔄 Flow of Program

1. Reservation already exists (from previous use case).
2. Guest selects add-on services.
3. Services are stored in a list.
4. List is mapped to reservation ID.
5. Total cost is calculated.
6. Booking and inventory remain unchanged.

---

## 🧠 Concepts Used

### 1. One-to-Many Relationship
One reservation → multiple services

### 2. Map + List
Map<String, List<Service>> used for mapping

### 3. Composition
Services are attached, not inherited

### 4. Separation of Concerns
Add-ons do not affect booking logic

### 5. Cost Aggregation
Costs calculated independently

---

## ✅ Key Features
- Multiple services per reservation
- Flexible system design
- Easy to add new services
- No impact on booking system

---

## ⚠️ Drawback (Previous Use Case)
- Booking was static
- No support for additional services

---

## ▶️ How to Run

### Compile:

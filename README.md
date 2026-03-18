# Book My Stay App
## Use Case 9: Error Handling & Validation

### Author:
Kaustubh Chauhan  
RA2411030010032

---

## 📌 Description
This use case introduces structured validation and custom error handling
for booking requests, preventing invalid inputs and inconsistent states.

---

## 🎯 Goal
Ensure reliability by detecting and handling errors early.

---

## 👤 Actors
- Guest → Provides booking input
- Invalid Booking Validator → Validates input and system state

---

## 🔄 Flow

1. Guest provides booking details.
2. Input and system state are validated.
3. If invalid, a meaningful error message is displayed.
4. Inventory and system state remain consistent.
5. Valid bookings are processed normally.

---

## 🧠 Concepts Used

- Input validation (guest name, room type)
- Custom exceptions (`InvalidBookingException`)
- Fail-fast design
- Guarding system state
- Graceful failure handling

---

## ✅ Features

- Prevents invalid guest names
- Prevents invalid or overbooked room allocations
- Clear error messages for failures
- System continues safely after errors

---

## ⚠️ Previous Drawback

- UC8 assumed all inputs were valid
- Incorrect data could corrupt inventory or booking reports

---

## ▶️ How to Run

### Compile:

javac UseCase9ErrorHandlingValidation.java


### Run:

java UseCase9ErrorHandlingValidation


---

## 📌 Sample Output

Booking successful for Alice  
Booking Failed: Guest name cannot be empty.  
Booking Failed: Invalid room type: Suite  
Booking successful for Charlie  
Booking Failed: No rooms available for: Double

Confirmed Reservations:  
Reservation ID: RES101 | Guest: Alice | Room Type: Single | Room ID: SI123  
Reservation ID: RES104 | Guest: Charlie | Room Type: Double | Room ID: DO456

Inventory Status:  
Single -> 1  
Double -> 0

---

## 🚀 Summary
This use case strengthens system reliability by validating inputs,
handling errors gracefully, and preventing invalid state changes.
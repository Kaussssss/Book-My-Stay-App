# Book My Stay App
## Use Case 8: Booking History & Reporting



## 📌 Project Description
This use case introduces booking history tracking and reporting,
allowing administrative visibility without persistent storage.

---

## 🎯 Goal
Maintain chronological booking history and generate simple summary reports.

---

## 👤 Actors
- Admin → Views booking history and reports
- Booking History → Stores confirmed reservations
- Booking Report Service → Generates reports from history

---

## 🔄 Flow

1. Reservation is confirmed.
2. Confirmed reservation is added to history.
3. History maintains insertion order.
4. Admin retrieves all reservations or summary reports.
5. Data in history remains unmodified during reporting.

---

## 🧠 Concepts Used

- List<Reservation> for ordered storage
- Separation of storage and reporting
- Audit trail simulation
- Reporting readiness without persistence

---

## ✅ Key Features

- Chronological storage of confirmed reservations
- Safe retrieval for reporting
- Summary reports per room type
- Total reservation count
- Non-destructive reporting

---

## ⚠️ Previous Drawback

- Add-On services (UC7) did not store historical booking data
- No audit trail for confirmed reservations

---

## ▶️ How to Run

### Compile:

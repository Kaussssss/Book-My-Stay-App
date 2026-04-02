# Book My Stay App

## Use Case 3 – Centralized Room Inventory Management

### Overview

This use case introduces **centralized room inventory management** within the *Book My Stay* Hotel Booking Management System. Instead of storing room availability using scattered variables (as done in the previous use case), the system now maintains availability in a **single centralized data structure using a HashMap**.

This approach ensures that the application maintains a **single source of truth** for room availability, preventing inconsistencies and improving scalability as the system grows. The inventory component encapsulates all logic related to availability management, allowing other parts of the system to interact with it through controlled methods.

---

### Application Information

* **Application Name:** Book My Stay App
* **Use Case:** UC3 – Centralized Room Inventory Management
* **Version:** 3.1 (Refactored Version)
* **Language:** Java
* **Focus:** Core Java & Data Structures

---

### Goal

To replace scattered availability variables with a **centralized inventory system** using a `HashMap`, demonstrating how data structures help maintain consistent system state.

---

### Actor

**RoomInventory** – Responsible for managing and exposing room availability across the system.

---

### Application Flow

1. The application starts execution.
2. The inventory component is initialized.
3. Room types are registered along with their available counts.
4. Availability information is stored in a centralized `HashMap`.
5. The application retrieves room availability when required.
6. Updates to room counts are performed using controlled methods.
7. The current inventory state is displayed to the console.

---

### Key Concepts Demonstrated

#### Problem of Scattered State

In **Use Case 2**, room availability was stored using individual variables. This approach leads to:

* Code duplication
* Inconsistent updates
* Poor scalability

Centralizing the state eliminates these issues.

---

#### HashMap

A **HashMap** is used to map room types to their availability.

Example structure:

```java
HashMap<String, Integer> inventory;
```

Example data stored in the map:

| Room Type   | Available Rooms |
| ----------- | --------------- |
| Single Room | 10              |
| Double Room | 6               |
| Suite Room  | 3               |

---

#### O(1) Lookup

HashMap provides **average constant-time complexity** for retrieving values.

Operations such as:

```java
inventory.get("Single Room");
inventory.put("Single Room", 9);
```

are typically performed in **O(1) time**, making the structure efficient for systems with frequent inventory checks.

---

#### Single Source of Truth

All room availability information is stored inside a **single data structure**, ensuring:

* Consistent updates
* Reliable availability checks
* Simplified system maintenance

---

#### Encapsulation of Inventory Logic

The `RoomInventory` class encapsulates all inventory-related logic, including:

* Initializing room availability
* Retrieving availability
* Updating room counts
* Displaying inventory state

Other parts of the system do not directly manipulate the map.

---

#### Separation of Concerns

The system separates two responsibilities:

| Component     | Responsibility                                  |
| ------------- | ----------------------------------------------- |
| Room classes  | Define room characteristics (price, beds, type) |
| RoomInventory | Manage how many rooms are available             |

This improves maintainability and system design.

---

#### Scalability

Adding a new room type becomes simple:

```java
inventory.put("Deluxe Room", 5);
```

No other application logic needs modification.

---

### Key Requirements

* Initialize room availability using a constructor.
* Store availability using a `HashMap<String, Integer>`.
* Provide methods to retrieve availability.
* Allow controlled updates to inventory.
* Maintain a consistent inventory state.

---

### Project Structure

The use case is implemented in a **single Java file**:

```
UseCase3InventorySetup.java
```

The file contains:

* `RoomInventory` class (inventory management)
* `UseCase3InventorySetup` class (application entry point)

---

### Compilation and Execution

#### Compile the Program

```bash
javac UseCase3InventorySetup.java
```

#### Run the Program

```bash
java UseCase3InventorySetup
```

---

### Example Output

```
Welcome to Book My Stay App
Application Version: 3.1

---- Current Room Inventory ----
Single Room : 10 rooms available
Double Room : 6 rooms available
Suite Room : 3 rooms available

Single Room Availability: 10

Updating Single Room availability...

---- Current Room Inventory ----
Single Room : 9 rooms available
Double Room : 6 rooms available
Suite Room : 3 rooms available

Application terminated.
```

---

### Benefits of This Use Case

* Establishes a **single source of truth for room availability**
* Enables **constant-time inventory access**
* Improves **system scalability**
* Demonstrates practical use of **HashMap in real-world applications**

---

### Limitations

* Inventory is still **initialized with fixed values**
* No booking workflow exists yet
* No concurrency control is implemented

Future use cases will introduce **booking logic, request handling, and prevention of double booking**.

---

### Improvements Over Use Case 2

| Use Case 2                       | Use Case 3                         |
| -------------------------------- | ---------------------------------- |
| Availability stored in variables | Availability stored in HashMap     |
| Hard to scale                    | Easy to extend                     |
| Risk of inconsistent updates     | Centralized and controlled updates |

---



### Version

**3.1**

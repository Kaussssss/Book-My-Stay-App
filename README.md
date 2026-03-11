# Book My Stay App

## Use Case 2 – Basic Room Types & Static Availability

### Overview

This use case demonstrates the **object-oriented design of room types** within the *Book My Stay* Hotel Booking Management System. The goal is to introduce **domain modeling using core OOP principles** such as **abstraction, inheritance, polymorphism, and encapsulation** before introducing complex data structures.

The application initializes different room types and displays their details along with their **static availability** using simple variables. This approach intentionally highlights the limitations of hardcoded availability, which will be improved in later use cases using proper inventory management techniques.

---

### Application Information

* **Application Name:** Book My Stay App
* **Use Case:** UC2 – Basic Room Types & Static Availability
* **Version:** 2.1 (Refactored Version)
* **Language:** Java
* **Focus:** Core Java & Object-Oriented Programming

---

### Goal

To model hotel room types using object-oriented concepts and display their details and availability when the application is executed.

---

### Actor

**User** – Runs the application from the command line or an IDE.

---

### Application Flow

1. The user runs the application.
2. The Java Virtual Machine (JVM) invokes the `main()` method.
3. Room objects representing different room types are created.
4. Availability for each room type is stored using simple variables.
5. The application prints room details and availability to the console.
6. The application terminates after displaying the information.

---

### Key Concepts Demonstrated

#### Abstract Class

The `Room` class is defined as an **abstract class** representing a generalized room.
It contains common attributes such as room type, number of beds, and price.

#### Inheritance

Concrete classes extend the `Room` class:

* `SingleRoom`
* `DoubleRoom`
* `SuiteRoom`

Each subclass inherits shared properties and represents a specific type of hotel room.

#### Polymorphism

Room objects are referenced using the **base type `Room`**, allowing different room implementations to be handled uniformly.

Example:

```java
Room single = new SingleRoom();
Room dbl = new DoubleRoom();
Room suite = new SuiteRoom();
```

#### Encapsulation

Room attributes are stored inside the `Room` class and accessed through defined methods such as `displayRoomDetails()`.

#### Static Availability Representation

Room availability is stored using simple variables:

```java
int singleAvailability = 10;
int doubleAvailability = 6;
int suiteAvailability = 3;
```

This demonstrates the limitations of static state management before introducing data structures.

#### Separation of Domain and State

* **Room objects** describe what a room is.
* **Availability variables** represent the current system state.

This separation prepares the system for future inventory management improvements.

---

### Room Types Implemented

| Room Type   | Beds | Price |
| ----------- | ---- | ----- |
| Single Room | 1    | $80   |
| Double Room | 2    | $120  |
| Suite Room  | 3    | $250  |

---

### Project Structure

This use case is implemented in a **single Java file**:

```
UseCase2RoomInitialization.java
```

The file contains:

* Abstract class `Room`
* Class `SingleRoom`
* Class `DoubleRoom`
* Class `SuiteRoom`
* Main class `UseCase2RoomInitialization`

---

### Compilation and Execution

#### Compile the Program

```bash
javac UseCase2RoomInitialization.java
```

#### Run the Program

```bash
java UseCase2RoomInitialization
```

---

### Example Output

```
Welcome to Book My Stay App
Application Version: 2.1

---- Room Details ----

Room Type : Single Room
Beds      : 1
Price     : $80.0
Available : 10

Room Type : Double Room
Beds      : 2
Price     : $120.0
Available : 6

Room Type : Suite Room
Beds      : 3
Price     : $250.0
Available : 3

Application terminated.
```

---

### Benefits of This Use Case

* Introduces **object-oriented domain modeling**
* Demonstrates **abstraction and inheritance**
* Provides a **foundation for scalable system design**
* Helps learners understand the difference between **domain objects and system state**

---

### Limitations

* Room availability is stored using **simple variables**
* The system does **not support dynamic inventory management**
* No booking functionality is implemented yet

These limitations will be addressed in future use cases by introducing **data structures and booking logic**.

---

### Future Enhancements

Later use cases will introduce:

* Room inventory management using **arrays or collections**
* Booking requests
* Prevention of **double booking**
* Queue-based request handling
* Improved data structure usage

---

### Author

**Kaustubh Chauhan**

### Version

**2.1**

# README for Enterprise Order Management System (Java Project)

---

## 📝 Project Overview

**Enterprise Order Management System (EOMS)** is a fully-featured, object-oriented Java application that simulates a real-world enterprise-level order management process. It allows users to create and manage customer orders with multiple products, manage order lifecycle statuses, generate invoices, and produce detailed reports.

---

## 🖥️ Technologies Used

- Java (Standard Edition)
- Object-Oriented Programming (OOP)
- Enums
- Collections Framework (List, Map)
- Java Streams API
- Command-line Interface (CLI)

---

## 📂 Project Structure

```
EnterpriseOrderManagementSystem.java   # Complete Java source code
```

---

## 🔧 Prerequisites

- Java Development Kit (JDK 8 or higher)
- Verify Java installation:

```bash
java -version
javac -version
```

---

## 🚀 How to Compile and Run

### 1️⃣ Extract the ZIP File

Unzip `EnterpriseOrderManagementSystem_Project.zip` to your desired location.

### 2️⃣ Compile the Java code

Open terminal/command prompt, navigate to the project directory and run:

```bash
javac EnterpriseOrderManagementSystem.java
```

### 3️⃣ Run the application

```bash
java EnterpriseOrderManagementSystem
```

✅ The CLI menu will appear and allow you to interact with the system.

---

## 🔬 Features

- ✅ Create customer orders with multiple products
- ✅ Support for both Physical and Digital product types
- ✅ Order lifecycle management:
  - Pending
  - Processing
  - Shipped
  - Delivered
  - Cancelled
- ✅ Generate detailed invoices
- ✅ Update order statuses interactively
- ✅ Generate business reports:
  - Total revenue
  - Order status counts

---

## 📊 Example Use Case Flow

1️⃣ Start program and choose **Create Order**

2️⃣ Enter customer name, products, type (physical/digital), price, and quantity.

3️⃣ Add multiple products per order.

4️⃣ Automatically generate full invoice with line items and total amount.

5️⃣ View existing orders.

6️⃣ Update order statuses as order progresses through its lifecycle.

7️⃣ Generate summary reports with total revenue and order breakdown by status.

---

## ⚠ Challenges and Limitations

- No persistent data storage (everything is in-memory)
- No GUI (runs on command-line interface)
- Limited error handling for incorrect input formats
- No integration with external databases or APIs (can be added as future enhancement)

---

## 🔮 Possible Future Enhancements

- Add file-based persistence or database storage (JDBC/MySQL)
- Build GUI interface using Java Swing or JavaFX
- Implement RESTful web services using Spring Boot for enterprise-grade deployment
- Add advanced reporting, discounts, taxes, and multi-currency support
- User authentication and role management

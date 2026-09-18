# Banking-ATM-Simulation

## Project Overview

**Banking & ATM Simulation** is a Java-based application designed to simulate basic banking operations through a user-friendly interface. The project allows users to create customer accounts, deposit and withdraw money, check account balances, transfer funds, and view transaction history.

The application demonstrates the practical implementation of Object-Oriented Programming, encapsulation, collections, and exception handling in Java.

## Problem Statement

Traditional banking operations involve multiple processes that require secure and organized management of customer accounts and transactions.

This project aims to develop a simplified banking and ATM simulation system that allows users to perform essential banking operations in a controlled and easy-to-use environment. It helps demonstrate how banking functionalities can be implemented using Java programming concepts.

## Features

* **Create Customer Account:** Register new customers with account details.
* **Deposit Money:** Add funds to a customer's account.
* **Withdraw Money:** Withdraw money after validating the available balance.
* **Check Balance:** Display the current account balance.
* **Transfer Money:** Transfer funds between two valid accounts.
* **Transaction History:** View previous deposits, withdrawals, and transfers.
* **Exception Handling:** Handle invalid inputs, insufficient balance, and invalid account operations.
* **User-Friendly Interface:** Provide a simple interface for performing banking operations.

## Technologies Used

| Technology            | Purpose                                |
| --------------------- | -------------------------------------- |
| Java                  | Core application development           |
| OOP                   | Designing banking classes and objects  |
| Encapsulation         | Protecting account data                |
| ArrayList             | Storing transaction records            |
| HashMap               | Managing customer accounts             |
| Exception Handling    | Managing errors and invalid operations |
| Java Swing (Optional) | Creating the graphical user interface  |

## Java Concepts Implemented

### 1. Object-Oriented Programming (OOP)

The project uses classes and objects to represent customers, bank accounts, and transactions.

Concepts include:

* Classes and Objects
* Encapsulation
* Constructors
* Methods
* Inheritance (if implemented)
* Polymorphism (if implemented)

### 2. Encapsulation

Account details such as balance and account number are kept private and accessed through appropriate methods.

**Example:**

```java
private double balance;

public double getBalance() {
    return balance;
}
```

### 3. Collections Framework

* **ArrayList:** Stores transaction history.
* **HashMap:** Maps account numbers to customer accounts.
* **List:** Helps manage and retrieve transaction records.

### 4. Exception Handling

Exception handling is used to manage:

* Invalid account numbers
* Insufficient balance
* Invalid deposit or withdrawal amounts
* Duplicate account creation
* Invalid user inputs

### 5. Arithmetic and Banking Logic

The application performs:

* Balance calculations
* Deposit and withdrawal operations
* Fund transfers
* Transaction amount validation

## System Requirements

### Hardware Requirements

* Processor: Intel Core i3 or equivalent
* RAM: 4 GB or more
* Storage: Minimum 100 MB free space

### Software Requirements

* Operating System: Windows / Linux / macOS
* Java JDK 8 or higher
* IDE: IntelliJ IDEA / Eclipse / NetBeans / VS Code
* Java Swing (if GUI is implemented)

## Application Workflow

1. Launch the Banking & ATM Simulation application.
2. Create a new customer account.
3. Enter the required account details.
4. Select a banking operation.
5. Deposit, withdraw, or transfer money.
6. Check the updated account balance.
7. View transaction history.
8. Exit the application.

## Banking Operations

### 1. Account Creation

The user provides customer information, and the system generates a unique account number.

### 2. Deposit

The user enters an amount to deposit. The system validates the amount and updates the account balance.

### 3. Withdrawal

The user enters a withdrawal amount. The system checks whether sufficient funds are available before processing the transaction.

### 4. Fund Transfer

The user selects a source account and destination account. The system validates both accounts and transfers the specified amount.

### 5. Transaction History

The system records banking operations with details such as:

* Transaction type
* Amount
* Date and time (if implemented)
* Updated balance (if implemented)

## Example

**Customer Account:**

* Account Number: 1001
* Initial Balance: ₹5,000

| Operation       | Amount | Balance |
| --------------- | -----: | ------: |
| Initial Balance |      — |  ₹5,000 |
| Deposit         | ₹2,000 |  ₹7,000 |
| Withdraw        | ₹1,000 |  ₹6,000 |
| Transfer        | ₹2,000 |  ₹4,000 |

**Final Balance: ₹4,000**

The system records each transaction in the customer's transaction history.

## Suggested Project Structure

```text
BankingATM/
│
├── src/
│   ├── Main.java
│   ├── Customer.java
│   ├── BankAccount.java
│   ├── Transaction.java
│   ├── Bank.java
│   ├── BankingException.java
│   └── BankingGUI.java
│
├── data/
│   └── accounts.txt
│
└── README.md
```

## Security and Validation

For this educational simulation, the application can implement:

* Unique account numbers
* Input validation
* Balance verification before withdrawals
* Validation of source and destination accounts
* Prevention of negative transaction amounts

**Note:** This project is a simulation and is not intended for handling real financial transactions or sensitive banking data.

## Future Enhancements

* ATM PIN authentication
* Login and logout functionality
* Persistent data storage using files or databases
* Mini statement generation
* Interest calculation
* Admin dashboard
* GUI with Java Swing
* Export transaction history to PDF
* Database integration using JDBC
* Improved account security

## Educational Purpose

This project is developed as a college-level Java application to demonstrate the practical implementation of Object-Oriented Programming, encapsulation, collections, and exception handling.

It helps students understand how real-world banking concepts can be modeled and implemented using Java.

## Project Information

| Category             | Details                                             |
| -------------------- | --------------------------------------------------- |
| Project Name         | Banking & ATM Simulation                            |
| Programming Language | Java                                                |
| Project Type         | Banking Simulation                                  |
| Difficulty Level     | Moderate                                            |
| Interface            | Console / Java Swing                                |
| Target Users         | Students and learners                               |
| Primary Concepts     | OOP, Encapsulation, Collections, Exception Handling |

## License

This project is developed for educational purposes as part of a college project.

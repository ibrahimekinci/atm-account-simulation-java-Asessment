# 💳 ATM Banking Simulation System

A Java-based ATM simulation that implements **Abstract Data Types (ADTs)** and a hand-coded **Java Swing GUI**. It supports multiple account types, transaction processing, interest calculations, strict input validation, and custom exceptions—built with **clean code** and **OOP** principles.

---

## 📑 Table of Contents

- [Project Overview](#-project-overview)
- [Key Features](#-key-features)
- [Account Types & Rules](#-account-types--rules)
- [Architecture & Project Structure](#-architecture--project-structure)
- [Getting Started](#-getting-started)
- [Running the Project](#-running-the-project)
- [Sample Console Output](#-sample-console-output)
- [GUI Walkthrough](#-gui-walkthrough)
- [Custom Exceptions](#-custom-exceptions)
- [Design Assumptions](#-design-assumptions)
- [Roadmap](#-roadmap)
- [Contributing](#-contributing)
- [Author](#-author)
- [License](#-license)

---

## 📑 Project Overview

This project contains two main layers:

1. **Core Logic (Domain / ADTs):** Banking accounts, validations, and transaction rules implemented with a base `Account` class and concrete subclasses.
2. **GUI (Swing):** A hand-coded Java Swing interface that simulates a real-world ATM workflow—**no GUI Builder** is used. All components are manually written in Java for maximum control and clarity.

The system is designed to be **modular, reusable, and well-documented**, mirroring real ATM behaviour while remaining simple enough for learning and extension.

---

## 🚀 Key Features

**Core Logic**

- Strong **ADT design**: `Account` base + `SavingsAccount`, `ChequeAccount`, `NetSaverAccount`, `FixedAccount`.
- **Deposits & withdrawals** with strict validation:
  - Only ATM denominations **\$20, \$50, \$100**.
  - Daily withdrawal limits where applicable.
  - Early withdrawal penalty rules for Fixed Term accounts.
- **Interest calculation hooks** (daily/monthly/maturity) designed to be triggered by an external scheduler.
- Console-based tests in `Main.java` to validate domain behaviour.

**GUI (Swing)**

- ATM-style UI with account selection, amount inputs, action buttons, and transaction history panel.
- Clear success/error messages and stateful session flow (multiple transactions per session).
- Hand-coded layout and components (no form designer).

**Code Quality**

- Clean code, JavaDoc comments, and separation of concerns (domain ↔ exceptions ↔ UI).
- Robust input validation and defensive programming.

---

## 💼 Account Types & Rules

| Account Type   | Interest Calculation                         | Daily Limit              | Withdrawal Rules                                             |
| -------------- | -------------------------------------------- | ------------------------ | ------------------------------------------------------------ |
| **Savings**    | Calculated **daily** (external trigger)      | **Configurable by user** | Subject to daily cap; ATM notes only (\$20, \$50, \$100)     |
| **Cheque**     | **No interest**                              | **No limit**             | Unlimited withdrawals; ATM notes only (\$20, \$50, \$100)    |
| **NetSaver**   | Calculated **monthly** (higher than Savings) | **Fixed \$1000/day**     | Must use ATM notes; blocks over-limit requests               |
| **Fixed Term** | **On maturity only**                         | **No daily limit**       | Any early withdrawal **forfeits interest** (tracked by flag) |

> **Notes**
>
> - Savings allows a user-defined daily limit.
> - NetSaver enforces a strict \$1000 daily cap.
> - Fixed Term applies interest only at maturity; early withdrawals are permitted but lose interest.

---

## 🏗️ Architecture & Project Structure

```
📦 ATM-Banking-Simulation
 ┣ 📁 src
 ┃ ┣ 📁 domain              # Core logic, account classes, transaction handling
 ┃ ┣ 📁 exception           # Custom exception classes and interfaces
 ┃ ┣ 📁 gui                 # GUI components and ATM interface logic (Swing)
 ┃ ┣ Main.java              # Console-based tests for core logic
 ┃ ┣ ATMApp.java            # Main GUI application entrypoint
 ┣ 📄 README.md             # Project documentation
 ┣ 📄 .gitignore            # Git ignore file
 ┣ 📄 LICENSE               # License file
```

**Tech Stack**

- **Java:** 17+
- **GUI:** Java Swing (hand-coded)
- **IDE:** Apache NetBeans (recommended)
- **Dependencies:** None (standard JDK only)

---

## 🧰 Getting Started

### Requirements

- **Java 17 or higher** installed
- **Apache NetBeans** (or any Java IDE)
- No external libraries required

### Installation

1. Clone or download the repository.
2. Open the project in your IDE (NetBeans recommended).
3. Ensure the compiler is set to **Java 17+**.

---

## ▶️ Running the Project

### Core Logic Tests

- Run `Main.java` to execute console-based scenarios that cover:
  - Deposits & withdrawals
  - Interest application
  - Daily limits
  - Exception handling

### GUI Application

- Run `ATMApp.java` to launch the ATM UI.
- Select an account, enter an amount, and perform **Deposit**/**Withdraw** operations.
- Review results and messages in the UI panels.

---

## 🧪 Sample Console Output

```text
=== Savings Account - Daily Limit Tests ===
Daily Limit: $1000.00 | Balance: $5400.00
-> Trying to withdraw $1200...
Domain Error: Daily limit exceeded.
-> Withdrawing $400 + $300...
Withdraw successful. New balance: $4700.00

=== Fixed Account - Early Withdrawal Test ===
Balance: $10000.00 | Early Withdrawal: true
-> Withdrawing $5000...
Withdraw successful. No interest applied due to early withdrawal.
New balance: $5000.00
```

---

## 🖥️ GUI Walkthrough

1. Choose an **Account Type** from the dropdown.
2. Enter an **Amount** to deposit or withdraw.
3. Click **Deposit** or **Withdraw**.
4. Read the **feedback panel** for success or error messages (e.g., *"Withdrawal successful. New balance: \$4700.00"*, *"Error: Insufficient balance"*).

---

## ⚠️ Custom Exceptions

- `InsufficientBalanceException` – Attempt to withdraw more than the available balance.
- `NegativeValueException` – Negative amounts for deposit/withdraw are invalid.
- (Optionally) `InvalidDenominationException` – Amount not compatible with ATM notes (\$20, \$50, \$100).

---

## 🛠️ Design Assumptions

- **Scheduling:** Interest calculations are **externally triggered** (e.g., cron/scheduler); no date/time engine inside the domain.
- **Fixed Term:** All withdrawals are treated as **early** unless stated otherwise; early withdrawals **forfeit interest**.
- **ATM Constraints:** Withdrawals must be in **\$20/\$50/\$100** denominations.
- **GUI Philosophy:** Prefer **simplicity and clarity** over visual complexity; layout and components are fully hand-coded.

---

## 🗺️ Roadmap

-

---

## 🤝 Contributing

Contributions are welcome! Please open an issue or submit a PR with:

- A clear description of the change
- Test coverage (where applicable)
- Updates to docs if behaviour changes

---

## 👨‍💻 Author

Developed by **İbrahim Ekinci** — Software Developer (Melbourne, AU)

- Email: `ibrahimekinci36@gmail.com`
- LinkedIn/GitHub: *(add links if desired)*

---

## 📜 License

This project is licensed under the **MIT License**. See the `LICENSE` file for details.


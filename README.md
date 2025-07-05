# 💳 ATM Banking Simulation System

A Java-based banking system that simulates ATM operations, featuring multiple account types, interest mechanisms, withdrawal constraints, and exception handling. Designed using clean code principles, object-oriented programming (OOP), and modular architecture.

---

## 🏗️ Project Structure

```
📦 ATM-Banking-Simulation
 ┣ 📁 src
 ┃ ┣ 📁 domain              → Core logic & account classes
 ┃ ┣ 📁 exception           → Exceptions, interfaces, constants
 ┃ ┣ Main.java              → Manuel Tests
 ┣ 📄 README.md
 ┣ 📄 .gitignore
 ┣ 📄 LICENSE
```

---

## 💼 Account Types & Rules

| Account Type     | Interest     | Custom Daily Limit | Withdrawal Rules             |
|------------------|----------    |--------------------|------------------------------|
| Savings          | ✅           | ✅ (user-defined)  | ATM note validation & limit  |
| Cheque           | ❌           | ❌                 | No limits, no interest       |
| NetSaver         | ✅ (monthly) | ❌                 | Fixed limit ($1000/day)      |
| Fixed Term       | ✅ (matured) | ❌                 | No withdrawals before term   |

---

## ⚙️ Key Features

- 💰 **Deposit/Withdraw** operations with input validations  
- 📈 **Interest calculation** (monthly / conditional)  
- ⛔ **Daily withdrawal limits** and ATM note validation  
- ⚠️ **Custom exceptions** with domain-specific error messages  
- 🧱 **Modular & layered architecture**  
- 🧪 Fully tested from `Main.java` with console outputs  

---

## 🚀 Running the Project

### 🧰 Requirements
- Java 17+
- Apache NetBeans

### ▶️ Run

1. Clone the repository
2. Open the project in NetBeans
3. Navigate to `ATM-Banking-Simulation`
4. Run the project as a console application

---

## 🧪 Sample Output

```
=== Savings - Daily Limit Tests ===
Daily Limit: $1000.00 | Balance: $5400.00
-> Trying to withdraw $1200...
Domain Error: Daily limit exceeded.
-> Withdrawing $400 + $300...
Withdraw successful. New balance: $4700.00
...
```

---

## 👨‍💻 Author

Developed by [İbrahim Ekinci](https://github.com/ibrahimekinci) — Software Developer (Melbourne, AU)

---
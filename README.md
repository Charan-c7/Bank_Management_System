💳 Bank Management System

A Java-based Bank Management System project using Core and Advanced Java concepts. This project is structured using packages like DAO, DTO, Service, Exception handling, and a main class for execution.

📁 Project Structure
Bank_Management_System/
├── com.bank.DAO/
│   └── Data access layer (JDBC logic)
├── com.bank.DTO/
│   └── Data Transfer Objects (CustomerDetails, TransactionDetails)
├── com.bank.Service/
│   └── Business logic and validations
├── com.bank.Exception/
│   └── Custom exceptions
├── com.bank.Util/
│   └── Database connection setup
└── com.bank.Main
🚀 Features
- 🔐 Admin and Customer functionalities
- 📄 Manage and view customer details
- 💰 Perform banking transactions (deposits, withdrawals, statement,etc.)
- 🛠 Layered architecture using DAO, DTO, Service
- 🧩 Exception handling for robustness
- 💾 JDBC-based database connection (configurable)

🧑‍💻 How to Run
1. Clone the Repository
   git clone https://github.com/Charan-c7/Bank_Management_System.git

2. Open in an IDE (Recommended: Eclipse or IntelliJ IDEA)
   - Import as a Java project.
   - Make sure src/ is marked as the source folder.

3. Run the Application
   - Navigate to:
     src/com/bank/main/BankMainClass.java
   - Right-click → Run As → Java Application

✅ Prerequisites
- Java JDK 8 or higher
- Eclipse / IntelliJ / VS Code (with Java extension)
- Optional: MySQL or any RDBMS for JDBC (if you're connecting it to a database)

⚙️ Database Configuration (Optional)
If using JDBC:

1. Set up a database (e.g., MySQL)
2. Create necessary tables (e.g., customers, transactions)
3. Update DB credentials in:

src/com/bank/util/DataBaseConnection.java

String url = "jdbc:mysql://localhost:3306/your_database_name";
String user = "your_username";
String password = "your_password";

4. Add MySQL JDBC driver to your project libraries

📌 Notes
- .class files in bin/ are auto-generated when you compile
- Modify code in src/ directory only
- You can also add user input functionality for more interaction

👨‍💻 Author
Charan Kumar
📧 veerlacharankumar2004@gmail.com
🔗 https://www.linkedin.com/in/charan-kumar-56388a28b/

⭐️ GitHub Repo
https://github.com/Charan-c7/Bank_Management_System

Feel free to star ⭐ the repo if you found it helpful!


# **Store Database Program**

## **Project Overview**

The Store Database Program is a software application developed using Java and SQL to manage retail store operations effectively.\
This program integrates a SQL database with Java backend logic to handle the storage, retrieval, and management of essential data, such as product information, customer details, sales transactions, and inventory levels.\
It is designed to streamline store management and provide a reliable system for handling everyday retail operations.

## **Features**

- **Product Management:** Add, update, and delete product details, including name, price, and stock levels.
- **Customer Management:** Store and manage customer information, such as names, contact details, and purchase history.
- **Sales Transactions:** Record sales transactions, track purchases, and generate sales reports.
- **Inventory Management:** Monitor inventory levels, update stock based on sales, and alert for low-stock items.
- **Data Persistence:** Store and retrieve data using a SQL database to ensure data integrity and availability.
- **Search Functionality:** Quickly search for products, customers, or transactions based on various criteria.

## **Technologies Used**

- **Java:** Handles the backend logic and user interface, ensuring a smooth and responsive application.
- **SQL Database:** Stores and manages all the critical data related to products, customers, sales, and inventory.
- **JDBC (Java Database Connectivity):** Facilitates communication between the Java application and the SQL database.
- **Object-Oriented Design:** The application is structured using OOP principles, making it modular and maintainable.

## **How to Run the Program**

1. **Clone the Repository:**  
   ```bash
   git clone https://github.com/yourusername/storeSQL.git
   cd store-database-program
   ```

2. **Set Up the SQL Database:**  
   - Create a SQL database and import the provided schema.
   - Update the database connection settings in the Java application (e.g., `DBConnection.java`).

3. **Compile and Run the Program:**  
   Use your IDE or the command line to compile and run the application:  
   ```bash
   javac -d bin src/*.java
   java -cp bin Main
   ```

4. **Interact with the Application:**  
   Use the application interface to manage products, customers, sales, and inventory.

## **Future Enhancements**

- **User Interface (UI) Improvements:** Develop a more intuitive and visually appealing user interface.
- **Advanced Reporting:** Implement advanced sales and inventory reporting features with graphical representations.
- **Security Features:** Add user authentication and role-based access control for enhanced security.

## **Contributing**

Contributions are welcome! Please fork the repository and create a pull request with your proposed changes.

## **License**

This project is licensed under the MIT License. See the `LICENSE` file for more details.

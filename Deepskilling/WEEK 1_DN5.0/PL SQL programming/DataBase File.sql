CREATE DATABASE sam
USE sam
CREATE TABLE Customers (
    CustomerID int PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance int,
    LastModified DATE
);
ALTER TABLE Customers
ADD COLUMN IsVIP BOOLEAN DEFAULT FALSE;

CREATE TABLE Accounts (
    AccountID int PRIMARY KEY,
    CustomerID int,
    AccountType VARCHAR(20),
    Balance int,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID int PRIMARY KEY,
    AccountID int,
    TransactionDate DATE,
    Amount int,
    TransactionType VARCHAR(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);


CREATE TABLE Loans (
    LoanID int PRIMARY KEY,
    CustomerID int,
    LoanAmount int,
    InterestRate int,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID int PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary int,
    Department VARCHAR(50),
    HireDate DATE
); 

CREATE TABLE AuditLog (
    AuditID INT AUTO_INCREMENT PRIMARY KEY,
    TransactionID INT,
    AccountID INT,
    TransactionDate DATE,
    Amount DECIMAL(10,2),
    TransactionType VARCHAR(10),
    LogDate DATETIME
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', '1985-05-15', 1000, CURDATE());

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', '1990-07-20', 1500, CURDATE());

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, CURDATE());

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, CURDATE());

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, CURDATE(), 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, CURDATE(), 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (
    1,
    1,
    5000,
    5,
    CURDATE(),
    DATE_ADD(CURDATE(), INTERVAL 60 MONTH)
);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', '2015-06-15');

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', '2017-03-20');



-- Exercise 7: Packages

-- Scenario 1: Group all customer-related procedures and functions into a package.

-- Procedure: UpdateCustomerDetails
DELIMITER $$

CREATE PROCEDURE UpdateCustomerDetails(
    IN CustID INT,
    IN CustName VARCHAR(100),
    IN CustDOB DATE,
    IN CustBalance DECIMAL(10,2)
)
BEGIN
    UPDATE Customers
    SET Name = CustName,
        DOB = CustDOB,
        Balance = CustBalance,
        LastModified = CURDATE()
    WHERE CustomerID = CustID;
END$$

DELIMITER ;

-- Function: GetCustomerBalance

DELIMITER $$

CREATE FUNCTION GetCustomerBalance(
    CustID INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE CustBalance DECIMAL(10,2);

    SELECT Balance
    INTO CustBalance
    FROM Customers
    WHERE CustomerID = CustID;

    RETURN CustBalance;
END$$

DELIMITER ;

-- Test Them: 
CALL UpdateCustomerDetails(1, 'John Doe', '1985-05-15', 5000);

SELECT GetCustomerBalance(1);

-- Scenario 2: Create a package to manage employee data.

-- Procedure 1: HireNewEmployee

DELIMITER $$

CREATE PROCEDURE HireNewEmployee(
    IN EmpID INT,
    IN EmpName VARCHAR(100),
    IN EmpPosition VARCHAR(50),
    IN EmpSalary DECIMAL(10,2),
    IN EmpDepartment VARCHAR(50),
    IN EmpHireDate DATE
)
BEGIN
    INSERT INTO Employees(
        EmployeeID,
        Name,
        Position,
        Salary,
        Department,
        HireDate
    )
    VALUES(
        EmpID,
        EmpName,
        EmpPosition,
        EmpSalary,
        EmpDepartment,
        EmpHireDate
    );
END$$

DELIMITER ;

-- Procedure 2: UpdateEmployeeDetails

DELIMITER $$

CREATE PROCEDURE UpdateEmployeeDetails(
    IN EmpID INT,
    IN EmpName VARCHAR(100),
    IN EmpPosition VARCHAR(50),
    IN EmpSalary DECIMAL(10,2),
    IN EmpDepartment VARCHAR(50)
)
BEGIN
    UPDATE Employees
    SET Name = EmpName,
        Position = EmpPosition,
        Salary = EmpSalary,
        Department = EmpDepartment
    WHERE EmployeeID = EmpID;
END$$

DELIMITER ;

-- Function: CalculateAnnualSalary

DELIMITER $$

CREATE FUNCTION CalculateAnnualSalary(
    EmpID INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE AnnualSalary DECIMAL(10,2);

    SELECT Salary * 12
    INTO AnnualSalary
    FROM Employees
    WHERE EmployeeID = EmpID;

    RETURN AnnualSalary;
END$$

DELIMITER ;

-- Test the procedures and function:
-- Hire a new employee
CALL HireNewEmployee(
    3,
    'Rahul Sharma',
    'Analyst',
    50000,
    'Finance',
    '2026-07-20'
);

-- Update employee details
CALL UpdateEmployeeDetails(
    3,
    'Rahul Verma',
    'Senior Analyst',
    60000,
    'Finance'
);

-- Calculate annual salary
SELECT CalculateAnnualSalary(3) AS AnnualSalary;


-- Scenario 3: Group all account-related operations into a package.

-- Procedure 1: OpenNewAccount:
DELIMITER $$

CREATE PROCEDURE OpenNewAccount(
    IN AccID INT,
    IN CustID INT,
    IN AccType VARCHAR(20),
    IN AccBalance DECIMAL(10,2)
)
BEGIN
    INSERT INTO Accounts(
        AccountID,
        CustomerID,
        AccountType,
        Balance,
        LastModified
    )
    VALUES(
        AccID,
        CustID,
        AccType,
        AccBalance,
        CURDATE()
    );
END$$

DELIMITER ;

-- Procedure 2: CloseAccount:
DELIMITER $$

CREATE PROCEDURE CloseAccount(
    IN AccID INT
)
BEGIN
    DELETE FROM Accounts
    WHERE AccountID = AccID;
END$$

DELIMITER ;

-- Function: GetTotalBalance:
DELIMITER $$

CREATE FUNCTION GetTotalBalance(
    CustID INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE TotalBal DECIMAL(10,2);

    SELECT SUM(Balance)
    INTO TotalBal
    FROM Accounts
    WHERE CustomerID = CustID;

    RETURN IFNULL(TotalBal, 0);
END$$

DELIMITER ;

-- Test the procedures and function:
-- Open a new account
CALL OpenNewAccount(
    3,
    1,
    'Savings',
    5000
);

-- Close an account
CALL CloseAccount(3);

-- Get the total balance of a customer
SELECT GetTotalBalance(1) AS TotalBalance;
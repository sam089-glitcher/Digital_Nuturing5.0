-- Exercise 2: Error Handling

-- Scenario 1: Handle exceptions during fund transfers between accounts.

DELIMITER $$

CREATE PROCEDURE SafeTransferFunds(
    IN FromAccount INT,
    IN ToAccount INT,
    IN TransferAmount DECIMAL(10,2)
)
BEGIN
    DECLARE SourceBalance DECIMAL(10,2);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Error occurred. Transaction rolled back.' AS Message;
    END;

    START TRANSACTION;

    SELECT Balance
    INTO SourceBalance
    FROM Accounts
    WHERE AccountID = FromAccount;

    IF SourceBalance >= TransferAmount THEN

        UPDATE Accounts
        SET Balance = Balance - TransferAmount
        WHERE AccountID = FromAccount;

        UPDATE Accounts
        SET Balance = Balance + TransferAmount
        WHERE AccountID = ToAccount;

        COMMIT;
        SELECT 'Funds transferred successfully.' AS Message;

    ELSE
        ROLLBACK;
        SELECT 'Insufficient funds. Transaction cancelled.' AS Message;
    END IF;

END$$

DELIMITER ;

CALL SafeTransferFunds(1, 2, 500);

-- Scenario 2: Manage errors when updating employee salaries.

DELIMITER $$

CREATE PROCEDURE UpdateSalary(
    IN EmpID INT,
    IN Percentage DECIMAL(5,2)
)
BEGIN
    DECLARE EmpCount INT;

    SELECT COUNT(*)
    INTO EmpCount
    FROM Employees
    WHERE EmployeeID = EmpID;

    IF EmpCount = 0 THEN
        SELECT 'Error: Employee ID does not exist.' AS Message;
    ELSE
        UPDATE Employees
        SET Salary = Salary + (Salary * Percentage / 100)
        WHERE EmployeeID = EmpID;

        SELECT 'Salary updated successfully.' AS Message;
    END IF;

END$$

DELIMITER ;

CALL UpdateSalary(1, 10);

-- Scenario 3: Ensure data integrity when adding a new customer.

DELIMITER $$

CREATE PROCEDURE AddNewCustomer(
    IN CustID INT,
    IN CustName VARCHAR(100),
    IN CustDOB DATE,
    IN CustBalance DECIMAL(10,2)
)
BEGIN
    DECLARE CustCount INT;

    SELECT COUNT(*)
    INTO CustCount
    FROM Customers
    WHERE CustomerID = CustID;

    IF CustCount > 0 THEN
        SELECT 'Error: Customer ID already exists. Insertion cancelled.' AS Message;
    ELSE
        INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
        VALUES(CustID, CustName, CustDOB, CustBalance, CURDATE());

        SELECT 'Customer added successfully.' AS Message;
    END IF;

END$$

DELIMITER ;

CALL AddNewCustomer(3, 'Rahul Sharma', '1998-04-15', 12000);

-- checking duplicate.
CALL AddNewCustomer(1, 'Rahul Sharma', '1998-04-15', 12000);
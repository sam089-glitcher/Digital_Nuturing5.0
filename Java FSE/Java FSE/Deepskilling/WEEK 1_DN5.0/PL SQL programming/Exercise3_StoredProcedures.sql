-- Exercise 3: Stored Procedures.

-- Scenario 1: The bank needs to process monthly interest for all savings accounts.

DELIMITER $$

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';

    SELECT 'Monthly interest applied successfully.' AS Message;
END$$

DELIMITER ;

CALL ProcessMonthlyInterest();

SELECT AccountID, CustomerID, AccountType, Balance
FROM Accounts
WHERE AccountType = 'Savings';

-- Scenario 2: The bank wants to implement a bonus scheme for employees based on their performance.

DELIMITER $$

CREATE PROCEDURE UpdateEmployeeBonus(
    IN DeptName VARCHAR(50),
    IN BonusPercentage DECIMAL(5,2)
)
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * BonusPercentage / 100)
    WHERE Department = DeptName;

    SELECT 'Employee bonus updated successfully.' AS Message;
END$$

DELIMITER ;

CALL UpdateEmployeeBonus('IT', 10);

SELECT EmployeeID,
       Name,
       Department,
       Salary
FROM Employees
WHERE Department = 'IT';

-- Scenario 3: Customers should be able to transfer funds between their accounts.

DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN FromAccount INT,
    IN ToAccount INT,
    IN TransferAmount DECIMAL(10,2)
)
BEGIN
    DECLARE SourceBalance DECIMAL(10,2);

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

        SELECT 'Funds transferred successfully.' AS Message;

    ELSE
        SELECT 'Insufficient balance. Transfer failed.' AS Message;
    END IF;

END$$

DELIMITER ;

CALL TransferFunds(1, 2, 500);

SELECT AccountID, Balance
FROM Accounts
WHERE AccountID IN (1, 2);
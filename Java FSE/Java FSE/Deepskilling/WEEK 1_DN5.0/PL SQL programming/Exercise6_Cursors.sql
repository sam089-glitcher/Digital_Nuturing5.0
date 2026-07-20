-- Exercise 6: Cursors

-- Scenario 1: Generate monthly statements for all customers.

DELIMITER $$

CREATE PROCEDURE GenerateMonthlyStatements()
BEGIN
    DECLARE Done INT DEFAULT FALSE;
    DECLARE CustID INT;
    DECLARE CustName VARCHAR(100);
    DECLARE TransID INT;
    DECLARE TransDate DATE;
    DECLARE TransAmount DECIMAL(10,2);
    DECLARE TransType VARCHAR(20);

    DECLARE StatementCursor CURSOR FOR
    SELECT c.CustomerID,
           c.Name,
           t.TransactionID,
           t.TransactionDate,
           t.Amount,
           t.TransactionType
    FROM Customers c
    JOIN Accounts a ON c.CustomerID = a.CustomerID
    JOIN Transactions t ON a.AccountID = t.AccountID
    WHERE MONTH(t.TransactionDate) = MONTH(CURDATE())
      AND YEAR(t.TransactionDate) = YEAR(CURDATE());

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET Done = TRUE;

    OPEN StatementCursor;

    ReadLoop: LOOP
        FETCH StatementCursor
        INTO CustID, CustName, TransID, TransDate, TransAmount, TransType;

        IF Done THEN
            LEAVE ReadLoop;
        END IF;

        SELECT CONCAT(
            'Customer ID: ', CustID,
            ', Name: ', CustName,
            ', Transaction ID: ', TransID,
            ', Date: ', TransDate,
            ', Amount: ', TransAmount,
            ', Type: ', TransType
        ) AS MonthlyStatement;
    END LOOP;

    CLOSE StatementCursor;
END$$

DELIMITER ;

CALL GenerateMonthlyStatements();

-- Scenario 2: Apply annual fee to all accounts.


DELIMITER $$

CREATE PROCEDURE ApplyAnnualFee()
BEGIN
    DECLARE Done INT DEFAULT FALSE;
    DECLARE AccID INT;
    DECLARE Fee DECIMAL(10,2) DEFAULT 100.00;

    DECLARE AccountCursor CURSOR FOR
    SELECT AccountID
    FROM Accounts;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET Done = TRUE;

    OPEN AccountCursor;

    ReadLoop: LOOP
        FETCH AccountCursor INTO AccID;

        IF Done THEN
            LEAVE ReadLoop;
        END IF;

        UPDATE Accounts
        SET Balance = Balance - Fee
        WHERE AccountID = AccID;

    END LOOP;

    CLOSE AccountCursor;

    SELECT 'Annual maintenance fee applied successfully.' AS Message;
END$$

DELIMITER ;

CALL ApplyAnnualFee();

SELECT AccountID,
       CustomerID,
       AccountType,
       Balance
FROM Accounts;

-- Scenario 3: Update the interest rate for all loans based on a new policy.

DELIMITER $$

CREATE PROCEDURE UpdateLoanInterestRates()
BEGIN
    DECLARE Done INT DEFAULT FALSE;
    DECLARE LoanIDVar INT;
    DECLARE CurrentRate DECIMAL(5,2);

    DECLARE LoanCursor CURSOR FOR
    SELECT LoanID, InterestRate
    FROM Loans;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET Done = TRUE;

    OPEN LoanCursor;

    ReadLoop: LOOP
        FETCH LoanCursor INTO LoanIDVar, CurrentRate;

        IF Done THEN
            LEAVE ReadLoop;
        END IF;

        UPDATE Loans
        SET InterestRate = CurrentRate + 0.50
        WHERE LoanID = LoanIDVar;

    END LOOP;

    CLOSE LoanCursor;

    SELECT 'Loan interest rates updated successfully.' AS Message;
END$$

DELIMITER ;

CALL UpdateLoanInterestRates();

SELECT LoanID,
       CustomerID,
       InterestRate
FROM Loans;



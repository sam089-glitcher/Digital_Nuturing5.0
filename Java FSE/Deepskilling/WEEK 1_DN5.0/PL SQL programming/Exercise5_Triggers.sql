-- Exercise 5: Triggers

-- Scenario 1: Automatically update the last modified date when a customer's record is updated.

DELIMITER $$

CREATE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    SET NEW.LastModified = CURDATE();
END$$

DELIMITER ;

UPDATE Customers
SET Balance = 2000
WHERE CustomerID = 1;

SELECT CustomerID,
       Name,
       Balance,
       LastModified
FROM Customers
WHERE CustomerID = 1;

-- Scenario 2: Maintain an audit log for all transactions.

DELIMITER $$

CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        TransactionID,
        AccountID,
        TransactionDate,
        Amount,
        TransactionType,
        LogDate
    )
    VALUES (
        NEW.TransactionID,
        NEW.AccountID,
        NEW.TransactionDate,
        NEW.Amount,
        NEW.TransactionType,
        NOW()
    );
END$$

DELIMITER ;

INSERT INTO Transactions (
    TransactionID,
    AccountID,
    TransactionDate,
    Amount,
    TransactionType
)
VALUES (
    3,
    1,
    CURDATE(),
    500,
    'Deposit'
);

SELECT * FROM AuditLog;

-- Scenario 3: Enforce business rules on deposits and withdrawals.

DELIMITER $$

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE CurrentBalance DECIMAL(10,2);

    SELECT Balance
    INTO CurrentBalance
    FROM Accounts
    WHERE AccountID = NEW.AccountID;

    IF NEW.TransactionType = 'Withdrawal' AND NEW.Amount > CurrentBalance THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Withdrawal amount exceeds account balance.';
    END IF;

    IF NEW.TransactionType = 'Deposit' AND NEW.Amount <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Deposit amount must be positive.';
    END IF;
END$$

DELIMITER ;

INSERT INTO Transactions
(TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES
(10, 1, CURDATE(), 500, 'Deposit');

INSERT INTO Transactions
(TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES
(4, 1, CURDATE(), -100, 'Deposit');

INSERT INTO Transactions
(TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES
(5, 1, CURDATE(), 5000, 'Withdrawal');
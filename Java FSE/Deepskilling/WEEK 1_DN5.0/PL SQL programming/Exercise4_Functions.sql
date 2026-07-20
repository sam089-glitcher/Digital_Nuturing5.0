-- Exercise 4: Functions.

-- Scenario 1: Calculate the age of customers for eligibility checks.

DELIMITER $$

CREATE FUNCTION CalculateAge(DateOfBirth DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, DateOfBirth, CURDATE());
END$$

DELIMITER ;

SELECT CustomerID,
       Name,
       DOB,
       CalculateAge(DOB) AS Age
FROM Customers;

SELECT CalculateAge('1985-05-15') AS Age;

-- Scenario 2: The bank needs to compute the monthly installment for a loan.

DELIMITER $$

CREATE FUNCTION CalculateMonthlyInstallment(
    LoanAmount DECIMAL(10,2),
    InterestRate DECIMAL(5,2),
    LoanDuration INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE MonthlyInstallment DECIMAL(10,2);

    SET MonthlyInstallment =
        (LoanAmount + (LoanAmount * InterestRate * LoanDuration / 100))
        / (LoanDuration * 12);

    RETURN MonthlyInstallment;
END$$

DELIMITER ;

SELECT CalculateMonthlyInstallment(5000, 5, 5) AS MonthlyInstallment;

SELECT LoanID,
       LoanAmount,
       InterestRate,
       CalculateMonthlyInstallment(
           LoanAmount,
           InterestRate,
           TIMESTAMPDIFF(YEAR, StartDate, EndDate)
       ) AS MonthlyInstallment
FROM Loans;

-- Scenario 3: Check if a customer has sufficient balance before making a transaction.

DELIMITER $$

CREATE FUNCTION HasSufficientBalance(
    AccID INT,
    Amount DECIMAL(10,2)
)
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE CurrentBalance DECIMAL(10,2);

    SELECT Balance
    INTO CurrentBalance
    FROM Accounts
    WHERE AccountID = AccID;

    RETURN CurrentBalance >= Amount;
END$$

DELIMITER ;

SELECT HasSufficientBalance(1, 500) AS Result;

SELECT AccountID,
       Balance,
       HasSufficientBalance(AccountID, 500) AS HasBalance
FROM Accounts;
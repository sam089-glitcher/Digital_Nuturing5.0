-- Exercise 1: Control Structures
-- Scenario 1: The bank wants to apply a discount to loan interest rates for customers above 60 years old.

SELECT c.CustomerID,
       c.Name,
       TIMESTAMPDIFF(YEAR, c.DOB, CURDATE()) AS Age,
       l.InterestRate
FROM Customers c
JOIN Loans l
ON c.CustomerID = l.CustomerID
WHERE TIMESTAMPDIFF(YEAR, c.DOB, CURDATE()) > 60;

SET SQL_SAFE_UPDATES = 0;

UPDATE Loans l
JOIN Customers c
ON l.CustomerID = c.CustomerID
SET l.InterestRate = GREATEST(l.InterestRate - 1, 0)
WHERE TIMESTAMPDIFF(YEAR, c.DOB, CURDATE()) > 60;


-- Scenario 2: A customer can be promoted to VIP status based on their balance.

SELECT CustomerID, Name, Balance
FROM Customers
WHERE Balance > 10000;

UPDATE Customers
SET IsVIP = TRUE
WHERE Balance > 10000;

-- Scenario 3: The bank wants to send reminders to customers whose loans are due within the next 30 days.

SELECT c.CustomerID,
       c.Name,
       l.LoanID,
       l.EndDate
FROM Customers c
JOIN Loans l
ON c.CustomerID = l.CustomerID
WHERE l.EndDate BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);

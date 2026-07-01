-- Exercise 6 : Cursors

------------------------------------------------------------
-- Scenario 1 : Generate Monthly Statements
------------------------------------------------------------

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID,
               c.CustomerName,
               t.TransactionID,
               t.TransactionDate,
               t.Amount,
               t.TransactionType
        FROM Customers c
        JOIN Transactions t
        ON c.CustomerID = t.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);

    v_record GenerateMonthlyStatements%ROWTYPE;

BEGIN
    OPEN GenerateMonthlyStatements;

    LOOP
        FETCH GenerateMonthlyStatements INTO v_record;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'Customer: ' || v_record.CustomerName ||
            ' | Transaction ID: ' || v_record.TransactionID ||
            ' | Type: ' || v_record.TransactionType ||
            ' | Amount: ' || v_record.Amount
        );

    END LOOP;

    CLOSE GenerateMonthlyStatements;
END;
/

------------------------------------------------------------
-- Scenario 2 : Apply Annual Maintenance Fee
------------------------------------------------------------

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;

    v_account ApplyAnnualFee%ROWTYPE;
    v_fee NUMBER := 500;

BEGIN
    OPEN ApplyAnnualFee;

    LOOP
        FETCH ApplyAnnualFee INTO v_account;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;

        UPDATE Accounts
        SET Balance = Balance - v_fee
        WHERE CURRENT OF ApplyAnnualFee;

    END LOOP;

    CLOSE ApplyAnnualFee;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Annual maintenance fee applied successfully.');
END;
/

------------------------------------------------------------
-- Scenario 3 : Update Loan Interest Rates
------------------------------------------------------------

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE;

    v_loan UpdateLoanInterestRates%ROWTYPE;

BEGIN
    OPEN UpdateLoanInterestRates;

    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE CURRENT OF UpdateLoanInterestRates;

    END LOOP;

    CLOSE UpdateLoanInterestRates;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Loan interest rates updated successfully.');
END;
/
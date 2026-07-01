-- Exercise 2 : Error Handling

------------------------------------------------------------
-- Scenario 1 : Safe Transfer of Funds
------------------------------------------------------------

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_fromAccount IN NUMBER,
    p_toAccount   IN NUMBER,
    p_amount      IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    -- Check sender's balance
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_fromAccount;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds.');
    END IF;

    -- Deduct amount from sender
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_fromAccount;

    -- Add amount to receiver
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_toAccount;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Fund transfer completed successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END;
/

------------------------------------------------------------
-- Scenario 2 : Update Employee Salary
------------------------------------------------------------

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employeeId IN NUMBER,
    p_percentage IN NUMBER
)
IS
    v_count NUMBER;
BEGIN
    -- Check whether employee exists
    SELECT COUNT(*)
    INTO v_count
    FROM Employees
    WHERE EmployeeID = p_employeeId;

    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID does not exist.');
    END IF;

    -- Update salary
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employeeId;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary updated successfully.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

------------------------------------------------------------
-- Scenario 3 : Add New Customer
------------------------------------------------------------

CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customerId   IN NUMBER,
    p_customerName IN VARCHAR2,
    p_balance      IN NUMBER
)
IS
BEGIN
    INSERT INTO Customers (CustomerID, CustomerName, Balance)
    VALUES (p_customerId, p_customerName, p_balance);

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Customer added successfully.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID already exists.');

    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Unexpected Error: ' || SQLERRM);
END;
/

------------------------------------------------------------
-- Test Procedure Calls (Optional)
------------------------------------------------------------

BEGIN
    SafeTransferFunds(101, 102, 5000);
END;
/

BEGIN
    UpdateSalary(1001, 10);
END;
/

BEGIN
    AddNewCustomer(201, 'Rahul Sharma', 15000);
END;
/
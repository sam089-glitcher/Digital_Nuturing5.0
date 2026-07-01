-- Exercise 7 : Packages

------------------------------------------------------------
-- Scenario 1 : CustomerManagement Package
------------------------------------------------------------

-- Package Specification
CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_Balance NUMBER
    );

    FUNCTION GetCustomerBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER;

END CustomerManagement;
/

-- Package Body
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_DOB DATE,
        p_Balance NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers
        VALUES (
            p_CustomerID,
            p_Name,
            p_DOB,
            p_Balance,
            SYSDATE
        );
    END;

    PROCEDURE UpdateCustomer(
        p_CustomerID NUMBER,
        p_Name VARCHAR2,
        p_Balance NUMBER
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name,
            Balance = p_Balance,
            LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;
    END;

    FUNCTION GetCustomerBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER
    IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;

        RETURN v_balance;
    END;

END CustomerManagement;
/

------------------------------------------------------------
-- Scenario 2 : EmployeeManagement Package
------------------------------------------------------------

-- Package Specification
CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    );

    PROCEDURE UpdateEmployee(
        p_EmployeeID NUMBER,
        p_Position VARCHAR2,
        p_Salary NUMBER
    );

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/

-- Package Body
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    ) IS
    BEGIN
        INSERT INTO Employees
        VALUES (
            p_EmployeeID,
            p_Name,
            p_Position,
            p_Salary,
            p_Department,
            p_HireDate
        );
    END;

    PROCEDURE UpdateEmployee(
        p_EmployeeID NUMBER,
        p_Position VARCHAR2,
        p_Salary NUMBER
    ) IS
    BEGIN
        UPDATE Employees
        SET Position = p_Position,
            Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;
    END;

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER
    IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;

        RETURN v_salary * 12;
    END;

END EmployeeManagement;
/

------------------------------------------------------------
-- Scenario 3 : AccountOperations Package
------------------------------------------------------------

-- Package Specification
CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount(
        p_AccountID NUMBER,
        p_CustomerID NUMBER,
        p_AccountType VARCHAR2,
        p_Balance NUMBER
    );

    PROCEDURE CloseAccount(
        p_AccountID NUMBER
    );

    FUNCTION GetTotalBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER;

END AccountOperations;
/

-- Package Body
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_AccountID NUMBER,
        p_CustomerID NUMBER,
        p_AccountType VARCHAR2,
        p_Balance NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts
        VALUES (
            p_AccountID,
            p_CustomerID,
            p_AccountType,
            p_Balance,
            SYSDATE
        );
    END;

    PROCEDURE CloseAccount(
        p_AccountID NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_AccountID;
    END;

    FUNCTION GetTotalBalance(
        p_CustomerID NUMBER
    ) RETURN NUMBER
    IS
        v_total NUMBER;
    BEGIN
        SELECT SUM(Balance)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = p_CustomerID;

        RETURN NVL(v_total,0);
    END;

END AccountOperations;
/

------------------------------------------------------------
-- Test Calls
------------------------------------------------------------

BEGIN
    CustomerManagement.AddCustomer(
        3,
        'Rahul Sharma',
        TO_DATE('1998-10-10','YYYY-MM-DD'),
        2500
    );
END;
/

BEGIN
    CustomerManagement.UpdateCustomer(
        3,
        'Rahul Sharma',
        5000
    );
END;
/

DECLARE
    v_balance NUMBER;
BEGIN
    v_balance := CustomerManagement.GetCustomerBalance(1);
    DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || v_balance);
END;
/

BEGIN
    EmployeeManagement.HireEmployee(
        3,
        'David',
        'Analyst',
        50000,
        'Finance',
        SYSDATE
    );
END;
/

DECLARE
    v_salary NUMBER;
BEGIN
    v_salary := EmployeeManagement.CalculateAnnualSalary(1);
    DBMS_OUTPUT.PUT_LINE('Annual Salary: ' || v_salary);
END;
/

BEGIN
    AccountOperations.OpenAccount(
        3,
        1,
        'Savings',
        5000
    );
END;
/

DECLARE
    v_total NUMBER;
BEGIN
    v_total := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Total Balance: ' || v_total);
END;
/ 
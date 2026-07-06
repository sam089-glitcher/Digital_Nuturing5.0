-- Exercise 5 : Triggers

------------------------------------------------------------
-- Scenario 1 : Update Customer Last Modified Date
------------------------------------------------------------

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE
ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

------------------------------------------------------------
-- Scenario 2 : Log Transactions into AuditLog
------------------------------------------------------------

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT
ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog
    (
        TransactionID,
        AccountID,
        Amount,
        TransactionType,
        LogDate
    )
    VALUES
    (
        :NEW.TransactionID,
        :NEW.AccountID,
        :NEW.Amount,
        :NEW.TransactionType,
        SYSDATE
    );
END;
/

------------------------------------------------------------
-- Scenario 3 : Check Deposit and Withdrawal Rules
------------------------------------------------------------

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT
ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Deposit must be positive
    IF :NEW.TransactionType = 'Deposit' THEN

        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(
                -20001,
                'Deposit amount must be greater than zero.'
            );
        END IF;

    END IF;

    -- Withdrawal rules
    IF :NEW.TransactionType = 'Withdrawal' THEN

        SELECT Balance
        INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(
                -20002,
                'Insufficient balance for withdrawal.'
            );
        END IF;

    END IF;

END;
/
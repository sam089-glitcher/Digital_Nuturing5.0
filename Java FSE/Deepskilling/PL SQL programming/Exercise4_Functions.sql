-- Exercise 4 : Functions

------------------------------------------------------------
-- Scenario 1 : Calculate Customer Age
------------------------------------------------------------

CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
)
RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);

    RETURN v_age;
END;
/

------------------------------------------------------------
-- Scenario 2 : Calculate Monthly Loan Installment
------------------------------------------------------------

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loanAmount IN NUMBER,
    p_interestRate IN NUMBER,
    p_years IN NUMBER
)
RETURN NUMBER
IS
    v_monthlyInstallment NUMBER;
    v_monthlyRate NUMBER;
    v_months NUMBER;
BEGIN
    v_monthlyRate := (p_interestRate / 100) / 12;
    v_months := p_years * 12;

    IF v_monthlyRate = 0 THEN
        v_monthlyInstallment := p_loanAmount / v_months;
    ELSE
        v_monthlyInstallment :=
            (p_loanAmount * v_monthlyRate * POWER(1 + v_monthlyRate, v_months))
            /
            (POWER(1 + v_monthlyRate, v_months) - 1);
    END IF;

    RETURN ROUND(v_monthlyInstallment, 2);
END;
/

------------------------------------------------------------
-- Scenario 3 : Check Sufficient Balance
------------------------------------------------------------

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_accountId IN NUMBER,
    p_amount IN NUMBER
)
RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_accountId;

    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

------------------------------------------------------------
-- Test Functions
------------------------------------------------------------

-- Test CalculateAge
BEGIN
    DBMS_OUTPUT.PUT_LINE(
        'Age: ' || CalculateAge(TO_DATE('15-08-1995','DD-MM-YYYY'))
    );
END;
/

-- Test CalculateMonthlyInstallment
BEGIN
    DBMS_OUTPUT.PUT_LINE(
        'Monthly Installment: ' ||
        CalculateMonthlyInstallment(500000, 8.5, 10)
    );
END;
/

-- Test HasSufficientBalance
DECLARE
    v_result BOOLEAN;
BEGIN
    v_result := HasSufficientBalance(101, 5000);

    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Sufficient Balance');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient Balance');
    END IF;
END;
/
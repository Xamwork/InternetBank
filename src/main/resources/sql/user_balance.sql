CREATE TABLE users_balance (
                               user_id SERIAL PRIMARY KEY,
                               balance NUMERIC(10, 2) NOT NULL DEFAULT 0.00
);

INSERT INTO users_balance (balance)
VALUES
    (100.50),
    (250.75),
    (500.00),
    (75.25),
    (0.00);
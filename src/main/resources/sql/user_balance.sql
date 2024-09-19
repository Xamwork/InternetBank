CREATE TABLE users_balance (
                               user_id SERIAL PRIMARY KEY,
                               balance NUMERIC(10, 2) NOT NULL DEFAULT 0.00
);
CREATE TABLE operation (
                           operation_id SERIAL PRIMARY KEY,      -- ID операции с автоинкрементом
                           user_id INT,                          -- ID пользователя
                           type INT,                   -- Тип операции
                           amount NUMERIC(10, 2),                -- Сумма операции с поддержкой дробных значений
                           date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id) REFERENCES users_balance(user_id)  -- Связь с таблицей баланса
);
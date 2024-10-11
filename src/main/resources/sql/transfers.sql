CREATE TABLE transfers (
                           transfer_id SERIAL PRIMARY KEY,      -- ID перевода
                           sender_id INT NOT NULL,              -- ID отправителя
                           receiver_id INT NOT NULL,            -- ID получателя
                           amount NUMERIC(10, 2) NOT NULL,      -- Сумма перевода
                           transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Дата перевода
                           FOREIGN KEY (sender_id) REFERENCES users_balance(user_id),  -- Связь с отправителем
                           FOREIGN KEY (receiver_id) REFERENCES users_balance(user_id),-- Связь с получателем
                           CHECK (sender_id != receiver_id)     -- Запрет на перевод самому себе
);

INSERT INTO transfers (sender_id, receiver_id, amount)
VALUES
    (1, 2, 50.00),    -- Пользователь 1 переводит 50.00 пользователю 2
    (2, 3, 100.00),   -- Пользователь 2 переводит 100.00 пользователю 3
    (3, 4, 75.50),    -- Пользователь 3 переводит 75.50 пользователю 4
    (4, 1, 30.25),    -- Пользователь 4 переводит 30.25 пользователю 1
    (2, 4, 20.00);    -- Пользователь 2 переводит 20.00 пользователю 4
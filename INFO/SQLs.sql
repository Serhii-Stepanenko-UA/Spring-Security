
-- БАЗА ДАНИХ
-- Можливість створення БД з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
-- Можемо створити БД через візуальний інструмент, наприклад,
-- MySQL Workbench.
CREATE DATABASE demo_db;


-- ТАБЛИЦІ
-- Можливість створення таблиць БД, з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
-- Попередньо, необхідно спроектувати таблиці та їх зв'язки,
-- на основі сутностей реального світу.
-- Можемо створити таблиці БД через візуальний інструмент, наприклад,
-- MySQL Workbench.

-- Створюємо дві таблиці
CREATE TABLE IF NOT EXISTS users
( id BIGINT NOT NULL AUTO_INCREMENT,
 email VARCHAR(255),
 phone VARCHAR(255),
 name VARCHAR(255),
 password VARCHAR(255),
 PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles
( id BIGINT NOT NULL AUTO_INCREMENT,
 name VARCHAR(255),
 PRIMARY KEY (id)
);

-- Join table (об’єднана таблиця) — таблиця даних,
-- яка має кілька вихідних з’єднань — з’єднання кількох
-- таблиць даних в одну таблицю даних.
-- Термін об’єднана таблиця може використовуватися
-- для реалізації зв’язку «багато-до-багатьох» між сутностями.
-- Її також називають таблицею з’єднання (junction table),
-- таблицею асоціацій (association table).
-- Буде створюватись програмою.
-- Зазначено в User через @JoinTable.
CREATE TABLE IF NOT EXISTS user_roles
( user_id BIGINT,
  role_id BIGINT
);


-- V1__create_journal.sql

-- Создание таблицы с историей маршрутов пользователей
CREATE TABLE IF NOT EXISTS journal(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(512),
    distance DOUBLE PRECISION NOT NULL,
    route TEXT NOT NULL,
    userId BIGINT NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT jk_user_id FOREIGN KEY (userId) REFERENCES users(id)
);
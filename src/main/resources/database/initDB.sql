-- Создание таблицы Country
CREATE TABLE IF NOT EXISTS Country (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       population INT NOT NULL,
                                       area FLOAT NOT NULL
);

-- Создание таблицы University
CREATE TABLE IF NOT EXISTS University (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
                                          country_id INT NOT NULL,
                                          FOREIGN KEY (country_id) REFERENCES Country (id)
);


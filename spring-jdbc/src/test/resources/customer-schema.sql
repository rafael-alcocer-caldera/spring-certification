DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS employee (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    mobile VARCHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address1 VARCHAR(255) NOT NULL,
    address2 VARCHAR(255),
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_employee_mobile (mobile),
    UNIQUE KEY uk_employee_email (email)
);

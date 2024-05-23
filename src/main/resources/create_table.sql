CREATE TABLE bitcoin_price (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10),
    rate VARCHAR(50),
    description VARCHAR(255),
    rate_float FLOAT
);

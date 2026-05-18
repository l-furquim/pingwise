CREATE TABLE plans (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    retention_days INT,
    max_monitors INT,
    max_users INT,
    api_calls_per_day INT,
    check_interval_seconds INT,
    price_cents INT
);
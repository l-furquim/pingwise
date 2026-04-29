CREATE TABLE plans (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    max_monitors INT,
    max_users INT,
    api_calls_per_day INT,
    check_interval_seconds INT,
    price_cents INT
);
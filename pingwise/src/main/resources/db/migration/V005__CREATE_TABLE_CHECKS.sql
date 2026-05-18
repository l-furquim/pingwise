CREATE TABLE checks (
    id UUID PRIMARY KEY,
    monitor_id UUID REFERENCES monitors(id),
    checked_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    response_ms INT NOT NULL,
    http_status INT NOT NULL,
    is_up BOOLEAN NOT NULL,
    region VARCHAR(100),
    error_message VARCHAR(255)
);
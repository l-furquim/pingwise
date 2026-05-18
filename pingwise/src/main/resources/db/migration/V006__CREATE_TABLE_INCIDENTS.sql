CREATE TABLE incidents (
    id UUID PRIMARY KEY,
    monitor_id UUID REFERENCES monitors(id),
    started_at TIMESTAMP,
    resolved_at TIMESTAMP,
    downtime_seconds INT NOT NULL,
    status VARCHAR(100)
);
CREATE TYPE incidents_status AS ENUM('OPEN', 'RESOLVED');

CREATE TABLE incidents (
    id UUID PRIMARY KEY,
    monitor_id UUID,
    started_at TIMESTAMP,
    resolved_at TIMESTAMP,
    downtime_seconds INT NOT NULL,
    status incidents_status,

    monitor_id REFERENCES monitors(id)
);
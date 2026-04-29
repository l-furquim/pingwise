CREATE TYPE monitors_status AS ENUM('ACTIVE', 'DEGRADATED', 'INCIDENT', 'PAUSED', 'PENDING');

CREATE TABLE monitors (
    id UUID NOT NULL,
    tenant_id UUID NOT NULL,
    name VARCHAR(150) NOT NULL,
    url VARCHAR(255) NOT NULL,
    interval_seconds INT NOT NULL,
    timeout_ms INT NOT NULL,
    consecutive_failures_threshold INT NOT NULL,
    status monitors_status NOT NULL,
    is_public BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    tenant_id REFERENCES tenants(id)
);

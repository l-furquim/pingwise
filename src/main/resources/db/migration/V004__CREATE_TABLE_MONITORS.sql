CREATE TABLE monitors (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES tenants(id),
    name VARCHAR(150) NOT NULL,
    url VARCHAR(255) NOT NULL,
    interval_seconds INT NOT NULL,
    timeout_ms INT NOT NULL,
    consecutive_failures_threshold INT NOT NULL,
    status VARCHAR(100) NOT NULL,
    is_public BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(id, name)

);

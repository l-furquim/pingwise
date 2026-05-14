CREATE TABLE monitors (
    id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL REFERENCES tenants(id),
    name VARCHAR(150) NOT NULL,
    url VARCHAR(255) NOT NULL,
    interval_seconds INT NOT NULL,
    timeout_ms INT NOT NULL,
    consecutive_failures_threshold INT NOT NULL,
    status VARCHAR(100) NOT NULL,
    next_check_at TIMESTAMP,
    dispatched_at TIMESTAMP,
    is_public BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE(id, name)

);

CREATE INDEX idx_monitors_dispatch
    ON monitors (next_check_at ASC)
    WHERE status = 'ACTIVE';

CREATE INDEX idx_monitors_tenant_id
    ON monitors (tenant_id);

CREATE INDEX idx_tenants_status
    ON tenants (status)
    WHERE status = 'ACTIVE';
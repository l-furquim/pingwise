CREATE TABLE alert_channels (
    id UUID PRIMARY KEY,
    tenant_id UUID,
    type VARCHAR(255),
    config JSONB,
    is_active BOOLEAN,

    tenant_id REFERENCES tenants(id)
);
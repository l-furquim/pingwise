CREATE TABLE alert_channels (
    id UUID PRIMARY KEY,
    tenant_id UUID REFERENCES tenants(id),
    type VARCHAR(255),
    config JSONB,
    is_active BOOLEAN
);
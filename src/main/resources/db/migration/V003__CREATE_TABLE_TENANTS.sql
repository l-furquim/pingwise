CREATE TYPE tenants_status as ENUM('ACTIVE', 'PAST_DUE', 'TRAILING', 'CANCELED', 'SUSPEND');

CREATE TABLE tenants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(100) NOT NULL,
    plan_id UUID NOT NULL,
    abacatepay_customer_id VARCHAR(255),
    abacatepay_subscription_id VARCHAR(255),
    status tenants_status,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
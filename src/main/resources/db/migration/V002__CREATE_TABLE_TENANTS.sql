CREATE TYPE tenants_status as ENUM('ACTIVE', 'PAST_DUE', 'TRAILING', 'CANCELED', 'SUSPEND', 'WAITING_PAYMENT');

CREATE TABLE tenants (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE,
    plan_id VARCHAR(100) NOT NULL,
    abacatepay_customer_id VARCHAR(255),
    abacatepay_subscription_id VARCHAR(255),
    status tenants_status,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
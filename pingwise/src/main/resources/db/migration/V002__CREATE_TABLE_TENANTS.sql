CREATE TABLE tenants (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE,
    plan_id VARCHAR(100) NOT NULL,
    abacatepay_customer_id VARCHAR(255) UNIQUE,
    abacatepay_subscription_id VARCHAR(255) UNIQUE,
    status VARCHAR(100),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
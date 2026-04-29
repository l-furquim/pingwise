CREATE TYPE users_roles AS ENUM('ADMIN', 'MEMBER');

CREATE TABLE users (
    id UUID PRIMARY KEY,
    tenant_id UUID,
    email VARCHAR(150),
    password_hash VARCHAR(255),
    role users_roles,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    tenant_id REFERENCES tenants(id)

);
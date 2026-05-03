CREATE TYPE notifications_status AS ENUM('PENDING', 'DELIVERED', 'FAILED', 'RETRYING');

CREATE TABLE notifications (
    id UUID PRIMARY KEY,
    incident_id UUID,
    alert_channel_id UUID,
    status notifications_status,
    attempt_count INT NOT NULL,
    last_attempt_at TIMESTAMP,
    error VARCHAR(500)
);
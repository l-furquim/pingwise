CREATE TABLE notifications (
    id UUID PRIMARY KEY,
    incident_id UUID,
    alert_channel_id UUID,
    status VARCHAR(100),
    attempt_count INT NOT NULL,
    last_attempt_at TIMESTAMP,
    error VARCHAR(500)
);
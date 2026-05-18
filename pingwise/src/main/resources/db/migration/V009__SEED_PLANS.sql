INSERT INTO plans (id, name, max_monitors, check_interval_seconds, max_users, retention_days, api_calls_per_day, price_cents) VALUES
('plan-free',    'free',    3,   300,  1,   7,    100,     0),
('plan-pro',     'pro',     20,   60,  5,  90,  10000,  2900),
('plan-max',     'max',     50,   60, 20, 180,  50000,  7900),
('plan-max-20x', 'max-20x', -1,   30, -1, 365,     -1, 19900);
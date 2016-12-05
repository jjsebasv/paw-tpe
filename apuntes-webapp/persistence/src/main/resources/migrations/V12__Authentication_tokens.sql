CREATE TABLE IF NOT EXISTS auth_tokens (
  token_id  SERIAL PRIMARY KEY,
  token     CHAR(50) UNIQUE,
  client_id INTEGER NOT NULL UNIQUE REFERENCES clients (client_id)
);


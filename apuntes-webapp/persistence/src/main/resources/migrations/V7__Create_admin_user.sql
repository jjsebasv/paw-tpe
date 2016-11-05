INSERT INTO clients (client_id, username, email, password, role)
VALUES (nextval('clients_client_id_seq'), 'admin', 'changeme@example.com', '123456', 'ROLE_ADMIN');
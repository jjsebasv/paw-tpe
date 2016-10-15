-- DROP TABLE IF EXISTS clients CASCADE;
-- DROP TABLE IF EXISTS programs CASCADE;
-- DROP TABLE IF EXISTS courses CASCADE;
-- DROP TABLE IF EXISTS coursesToPrograms CASCADE;
-- DROP TABLE IF EXISTS documents CASCADE;
-- DROP TABLE IF EXISTS reviews CASCADE;

CREATE TABLE IF NOT EXISTS clients (
  client_id SERIAL PRIMARY KEY,
  username  VARCHAR(100),
  password  VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS programs (
  program_id SERIAL PRIMARY KEY,
  name       VARCHAR(100),
  short_name VARCHAR(50),
  "group"    CHAR(1)
);

CREATE TABLE IF NOT EXISTS courses (
  course_id SERIAL PRIMARY KEY,
  code      CHAR(5) UNIQUE NOT NULL,
  name      VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS coursesToPrograms (
  course_id  INTEGER REFERENCES courses (course_id),
  program_id INTEGER REFERENCES programs (program_id),
  semester   SMALLINT,

  PRIMARY KEY (course_id, program_id)
);

CREATE TABLE IF NOT EXISTS documents (
  document_id       SERIAL PRIMARY KEY,
  client_id         INTEGER REFERENCES clients (client_id)     NOT NULL,
  course_id         INTEGER REFERENCES courses (course_id)     NOT NULL,
  subject           VARCHAR(100),
  document_name     VARCHAR(300),
  document_size     INTEGER,
  uploaded_document BYTEA

);

CREATE TABLE IF NOT EXISTS reviews (
  review_id   SERIAL PRIMARY KEY,
  document_id INTEGER REFERENCES documents (document_id) NOT NULL,
  client_id   INTEGER REFERENCES clients (client_id)     NOT NULL,
  ranking     INTEGER                                    NOT NULL CHECK (ranking >= 1 AND ranking <= 5),
  review      VARCHAR(500),

  CONSTRAINT reviews_onePerclient UNIQUE (document_id, client_id)
);

-- INSERT INTO clients (username, password) VALUES
--   ('nlopez', 'password1'),
--   ('jvera', 'password2'),
--   ('skulez', 'password3'),
--   ('aarlanti', 'password4');


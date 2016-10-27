CREATE TABLE IF NOT EXISTS clients (
  client_id SERIAL PRIMARY KEY,
  username  VARCHAR(100),
  password  VARCHAR(100),
  email     VARCHAR(200) UNIQUE
);

CREATE TABLE IF NOT EXISTS programs (
  program_id    SERIAL PRIMARY KEY,
  name          VARCHAR(100),
  short_name    VARCHAR(50),
  program_group CHAR(1)
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
  uploaded_document BYTEA,
<<<<<<< HEAD
  date_uploaded TIMESTAMP WITHOUT TIME ZONE DEFAULT (now() AT TIME ZONE 'utc')
=======
  date_uploaded     TIMESTAMP WITHOUT TIME ZONE
>>>>>>> 098cd41984402fe2a25a275ac45700696b59cbf9

);

CREATE TABLE IF NOT EXISTS reviews (
  review_id     SERIAL PRIMARY KEY,
  document_id   INTEGER REFERENCES documents (document_id) NOT NULL,
  client_id     INTEGER REFERENCES clients (client_id)     NOT NULL,
  ranking       INTEGER                                    NOT NULL CHECK (ranking >= 1 AND ranking <= 5),
  review        VARCHAR(500),
  date_uploaded TIMESTAMP WITHOUT TIME ZONE DEFAULT (now() AT TIME ZONE 'utc'),

  CONSTRAINT reviews_onePerclient UNIQUE (document_id, client_id)
);


CREATE TABLE clients (
  client_id INTEGER IDENTITY PRIMARY KEY,
  username  VARCHAR(100),
  password  VARCHAR(100),
  email     VARCHAR(200),

  UNIQUE (email)
);

CREATE TABLE programs (
  program_id INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(100),
  short_name VARCHAR(50),
  program_group    CHAR(1)
);

CREATE TABLE courses (
  course_id INTEGER IDENTITY PRIMARY KEY,
  code      CHAR(5) NOT NULL,
  name      VARCHAR(200),

  UNIQUE (code)
);

CREATE TABLE coursesToPrograms (
  course_id  INTEGER NOT NULL,
  program_id INTEGER NOT NULL,
  semester   SMALLINT,

  CONSTRAINT coursesToPrograms_course_id_program_id_pk PRIMARY KEY (course_id, program_id),

  FOREIGN KEY (course_id) REFERENCES courses (course_id),
  FOREIGN KEY (program_id) REFERENCES programs (program_id)

);

CREATE TABLE documents (
  document_id       INTEGER IDENTITY PRIMARY KEY,
  client_id         INTEGER NOT NULL,
  course_id         INTEGER NOT NULL,
  subject           VARCHAR(100),
  document_name     VARCHAR(300),
  document_size     INTEGER,
  uploaded_document BLOB,


  FOREIGN KEY (client_id) REFERENCES clients (client_id),
  FOREIGN KEY (course_id) REFERENCES courses (course_id)

);

CREATE TABLE reviews (
  review_id   INTEGER IDENTITY PRIMARY KEY,
  document_id INTEGER NOT NULL,
  client_id   INTEGER NOT NULL,
  ranking     INTEGER NOT NULL CHECK (ranking >= 1 AND ranking <= 5),
  review      VARCHAR(500),

  CONSTRAINT reviews_onePerclient UNIQUE (document_id, client_id),

  FOREIGN KEY (document_id) REFERENCES documents (document_id),
  FOREIGN KEY (client_id) REFERENCES clients (client_id)
);

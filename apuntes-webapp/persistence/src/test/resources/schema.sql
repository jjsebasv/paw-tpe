DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS programs CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS coursesToPrograms CASCADE;
DROP TABLE IF EXISTS documents CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;

CREATE TABLE clients (
  client_id INTEGER IDENTITY PRIMARY KEY,
  username  VARCHAR(100),
  password  VARCHAR(100)
);

CREATE TABLE programs (
  program_id INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(100)
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

-- INSERT INTO clients (client_id, username, password) VALUES
--   (1, 'nlopez', 'password1'),
--   (2, 'jvera', 'password2'),
--   (3, 'skulez', 'password3'),
--   (4, 'aarlanti', 'password4');
--
-- INSERT INTO programs (program_id, name) VALUES
--   (1, 'Informatica'),
--   (2, 'Industrial'),
--   (3, 'Mecanica'),
--   (4, 'Quimica'),
--   (5, 'Naval'),
--   (6, 'Electronica'),
--   (7, 'Electrica'),
--   (8, 'Bioinformatica'),
--   (9, 'Electrica');
--
-- INSERT INTO courses (course_id, code, name) VALUES
--   (1, '72.10', 'Protocolos de comunicacion'),
--   (2, '72.11', 'Proyecto de Aplicaciones Web'),
--   (3, '72.12', 'Redes');
--
-- INSERT INTO coursesToPrograms (course_id, program_id) VALUES
--   (1, 1),
--   (2, 1),
--   (3, 1);
--
-- INSERT INTO documents (client_id, course_id, subject, document_name, document_size, uploaded_document) VALUES
--   (1, 1, '01.-Introduccion.pdf', '01.-Introduccion.pdf', 866539, NULL),
--   (2, 1, '02 HTTP.pdf', '02 HTTP.pdf', 516545, NULL),
--   (3, 2, 'JSTL.pdf', 'JSTL.pdf', 446520, NULL);
--
-- INSERT INTO reviews (document_id, client_id, ranking, review) VALUES
--   (1, 4, 5, 'Parcial resuelto, muy bueno!'),
--   (1, 3, 1, 'Esta mal resuelto! Banda de gilada'),
--   (2, 3, 2, 'Lo recomiendo');

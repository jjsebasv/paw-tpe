DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS programs CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS coursesToPrograms CASCADE;
DROP TABLE IF EXISTS files CASCADE;
DROP TABLE IF EXISTS reviews CASCADE;

CREATE TABLE IF NOT EXISTS users (
  userid   SERIAL PRIMARY KEY,
  username VARCHAR(100),
  password VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS programs (
  programid SERIAL PRIMARY KEY,
  name      VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS courses (
  courseid SERIAL PRIMARY KEY,
  code     CHAR(5) UNIQUE NOT NULL,
  name     VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS coursesToPrograms (
  courseid  INTEGER REFERENCES courses (courseid),
  programid INTEGER REFERENCES programs (programid),

  CONSTRAINT coursesToPrograms_courseid_programid_pk PRIMARY KEY (courseid, programid)
);

CREATE TABLE IF NOT EXISTS files (
  fileid        SERIAL PRIMARY KEY,
  userid        INTEGER REFERENCES users (userid)     NOT NULL,
  courseid      INTEGER REFERENCES courses (courseid) NOT NULL,
  subject       VARCHAR(100),
  fileName      VARCHAR(300),
  filesize      INTEGER,
  uploaded_file BYTEA

);

CREATE TABLE IF NOT EXISTS reviews (
  reviewid SERIAL PRIMARY KEY,
  fileid   INTEGER REFERENCES files (fileid) NOT NULL,
  userid   INTEGER REFERENCES users (userid) NOT NULL,
  ranking  INTEGER                           NOT NULL,
  review   VARCHAR(500),

  CONSTRAINT reviews_onePerUser UNIQUE (fileid, userid)
);

INSERT INTO users (username, password) values 
  ('nlopez', 'password1'),
  ('jvera', 'password2'),
  ('skulez', 'password3'),
  ('aarlanti', 'password4');

INSERT INTO programs (name) values
  ('Informatica'),
  ('Industrial'),
  ('Mecanica'),
  ('Quimica'),
  ('Naval'),
  ('Electronica'),
  ('Electrica'),
  ('Bioinformatica'),
  ('Electrica');

INSERT INTO courses (code, name) values
  ('72.10', 'Protocolos de comunicacion'),
  ('72.11', 'Proyecto de Aplicaciones Web'),
  ('72.12', 'Redes');

INSERT INTO coursesToPrograms (courseid, programid) values
  (1, 1),
  (2, 1),
  (3, 1);

INSERT INTO files (userid, courseid, subject, fileName, fileSize, uploaded_file) values
  (1, 1, '01.-Introduccion.pdf', '01.-Introduccion.pdf', 866539, null),
  (2, 1, '02 HTTP.pdf', '02 HTTP.pdf', 516545, null),
  (3, 2, 'JSTL.pdf', 'JSTL.pdf', 446520, null);

INSERT INTO reviews (fileid, userid, ranking, review) values
  (1, 1, 5, 'Parcial resuelto, muy bueno!'),
  (1, 2, 1, 'Esta mal resuelto! Banda de gilada'),
  (2, 3, 2, 'Lo recomiendo');


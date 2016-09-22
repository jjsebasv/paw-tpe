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

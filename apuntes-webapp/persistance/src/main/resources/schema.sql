CREATE TABLE IF NOT EXISTS users (
  userid   SERIAL PRIMARY KEY,
  username VARCHAR(100),
  password VARCHAR(100)
);

-- CREATE TABLE IF NOT EXISTS programs (
--   programid SERIAL PRIMARY KEY,
--   name      CHAR(100)
-- );

CREATE TABLE IF NOT EXISTS courses (
  courseid SERIAL PRIMARY KEY,
  name     CHAR(200)
);

CREATE TABLE IF NOT EXISTS files (
  fileid        SERIAL PRIMARY KEY,
  userid        INTEGER REFERENCES users (userid),
  courseid      INTEGER REFERENCES courses (courseid),
  subject       VARCHAR(100),
  fileName      CHAR(300),
  filesize      INTEGER,
  uploaded_file BYTEA
);

CREATE TABLE IF NOT EXISTS reviews (
  reviewid SERIAL PRIMARY KEY,
  fileid   INTEGER REFERENCES files (fileid),
  userid   INTEGER REFERENCES users (userid),
  ranking  INTEGER,
  review   VARCHAR(500)
);

CREATE TABLE users (
  userid   INTEGER IDENTITY PRIMARY KEY,
  username VARCHAR(100),
  password VARCHAR(100)
);

CREATE TABLE programs (
  programid INTEGER IDENTITY PRIMARY KEY,
  name      VARCHAR(100)
);

CREATE TABLE courses (
  courseid INTEGER IDENTITY PRIMARY KEY,
  code     CHAR(5) NOT NULL,
  name     VARCHAR(200),

  CONSTRAINT a UNIQUE (code)
);

CREATE TABLE coursesToPrograms (
  courseid  INTEGER,
  programid INTEGER,

  CONSTRAINT coursesToPrograms_courseid_programid_pk PRIMARY KEY (courseid, programid),

  FOREIGN KEY (courseid) REFERENCES courses (courseid),
  FOREIGN KEY (programid) REFERENCES programs (programid)
);

CREATE TABLE files (
  fileid        INTEGER IDENTITY PRIMARY KEY,
  userid        INTEGER,
  courseid      INTEGER,
  subject       VARCHAR(100),
  fileName      VARCHAR(300),
  filesize      INTEGER,
  uploaded_file BLOB,

  FOREIGN KEY (userid) REFERENCES users (userid),
  FOREIGN KEY (courseid) REFERENCES courses (courseid)

);

CREATE TABLE reviews (
  reviewid INTEGER IDENTITY PRIMARY KEY,
  fileid   INTEGER NOT NULL,
  userid   INTEGER NOT NULL,
  ranking  INTEGER NOT NULL,
  review   VARCHAR(500),

  CONSTRAINT reviews_onePerUser UNIQUE (fileid, userid),

  FOREIGN KEY (fileid) REFERENCES files (fileid),
  FOREIGN KEY (userid) REFERENCES users (userid)
);

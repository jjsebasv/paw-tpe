CREATE TABLE IF NOT EXISTS files (
	fileid SERIAL PRIMARY KEY,
	username varchar(100),
	university varchar(100),
	career varchar(100),
	subject varchar(100),
	uploaded_file bytea
);

CREATE TABLE IF NOT EXISTS users (
	userid SERIAL PRIMARY KEY,
	username varchar(100),
	password varchar(100)
);

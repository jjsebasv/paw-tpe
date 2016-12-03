CREATE TABLE IF NOT EXISTS universities (
  university_id SERIAL PRIMARY KEY,
  name          VARCHAR(100)
);

ALTER TABLE programs
  ADD university_id INTEGER REFERENCES universities (university_id);

INSERT INTO universities (university_id, name) VALUES (1, 'ITBA');

UPDATE programs
SET university_id = 1;
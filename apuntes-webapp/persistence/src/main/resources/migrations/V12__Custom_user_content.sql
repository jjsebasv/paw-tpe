ALTER TABLE universities
  ADD domain VARCHAR(100);

ALTER TABLE clients
  ADD university_id INTEGER REFERENCES universities (university_id);
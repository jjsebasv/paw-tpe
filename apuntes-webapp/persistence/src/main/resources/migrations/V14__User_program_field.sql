ALTER TABLE clients
  ADD program_id INTEGER REFERENCES programs (program_id);
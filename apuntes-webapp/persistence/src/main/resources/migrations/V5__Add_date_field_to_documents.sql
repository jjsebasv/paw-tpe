ALTER TABLE document
  ADD date_uploaded TIMESTAMP WITHOUT TIME ZONE DEFAULT (now() AT TIME ZONE 'utc');
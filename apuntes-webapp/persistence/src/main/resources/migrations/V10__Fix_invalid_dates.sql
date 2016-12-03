UPDATE documents
SET date_uploaded = '2016-12-01 11:00:00.000000'
WHERE date_uploaded IS NULL;

UPDATE reviews
SET date_uploaded = '2016-12-01 11:00:00.000000'
WHERE date_uploaded IS NULL;

CREATE TABLE temp
    (_id INTEGER PRIMARY KEY  UNIQUE,
    name TEXT,
    description TEXT,
    picture TEXT);

INSERT INTO 'temp'(name,description) VALUES("name", "This is a description");
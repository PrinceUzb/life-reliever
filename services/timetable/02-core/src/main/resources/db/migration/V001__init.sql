CREATE SCHEMA IF NOT EXISTS timetable;
CREATE TABLE IF NOT EXISTS timetable.academy_kind (
  name VARCHAR NOT NULL PRIMARY KEY,
  deleted BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO
  timetable.academy_kind
VALUES ('School'), ('Collage'), ('University');

CREATE TABLE IF NOT EXISTS timetable.academy (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE,
    kind VARCHAR NOT NULL CONSTRAINT fk_academy_kind REFERENCES timetable.academy_kind (name) ON UPDATE CASCADE ON DELETE NO ACTION,
    created_at timestamp NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
  );
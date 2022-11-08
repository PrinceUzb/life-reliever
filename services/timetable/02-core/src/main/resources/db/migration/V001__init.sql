CREATE SCHEMA IF NOT EXISTS timetable;
CREATE TABLE IF NOT EXISTS timetable.academy_kind
(
    id      UUID PRIMARY KEY,
    name    VARCHAR NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO timetable.academy_kind VALUES
('a6270344-5f65-11ed-9b6a-0242ac120002', 'School'),
('a62707ea-5f65-11ed-9b6a-0242ac120002', 'Collage'),
('a6270a42-5f65-11ed-9b6a-0242ac120002', 'University');

CREATE TABLE IF NOT EXISTS timetable.academy
(
    id      UUID PRIMARY KEY,
    name    VARCHAR NOT NULL,
    kind UUID    NOT NULL
            CONSTRAINT fk_academy_kind REFERENCES timetable.academy_kind (id) ON UPDATE CASCADE ON DELETE NO ACTION,
    deleted BOOLEAN NOT NULL DEFAULT false
);
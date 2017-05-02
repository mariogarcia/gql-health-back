CREATE SCHEMA IF NOT EXISTS gql;

CREATE TABLE gql.meal_type(
  id UUID PRIMARY KEY,
  name varchar(20)
);

CREATE TABLE IF NOT EXISTS gql.meal(
  id UUID PRIMARY KEY,
  comments text,
  "type" varchar(20) REFERENCES gql.meal_type(name),
  "date" date
);

CREATE TABLE gql.quantity_type(
  id UUID PRIMARY KEY,
  name varchar(20)
);

CREATE TABLE IF NOT EXISTS gql.meal_entry(
  id UUID PRIMARY KEY,
  meal_id UUID REFERENCES gql.meal (id),
  description text,
  quantity decimal,
  "type" varchar(20) REFERENCES gql.quantity_type(name)
);

INSERT INTO gql.meal_type (id, name) VALUES (RANDOM_UUID(), 'BREAKFAST');
INSERT INTO gql.meal_type (id, name) VALUES (RANDOM_UUID(), 'LUNCH');
INSERT INTO gql.meal_type (id, name) VALUES (RANDOM_UUID(), 'DINNER');
INSERT INTO gql.meal_type (id, name) VALUES (RANDOM_UUID(), 'IN_BETWEEN');

INSERT INTO gql.quantity_type (id, name) VALUES (RANDOM_UUID(), 'GRAM');
INSERT INTO gql.quantity_type (id, name) VALUES (RANDOM_UUID(), 'UNIT');

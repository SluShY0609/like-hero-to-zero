-- Länder (IDs werden automatisch vergeben)
INSERT INTO country (iso_code, name) VALUES ('DEU', 'Deutschland');
INSERT INTO country (iso_code, name) VALUES ('FRA', 'Frankreich');
INSERT INTO country (iso_code, name) VALUES ('USA', 'Vereinigte Staaten');

-- Emissionen (wir referenzieren über SELECT auf das Land)
INSERT INTO emission_record (country_id, year_value, co2_kilotons)
VALUES ((SELECT id FROM country WHERE iso_code='DEU'), 2021, 650000.0);

INSERT INTO emission_record (country_id, year_value, co2_kilotons)
VALUES ((SELECT id FROM country WHERE iso_code='DEU'), 2022, 640000.0);

INSERT INTO emission_record (country_id, year_value, co2_kilotons)
VALUES ((SELECT id FROM country WHERE iso_code='FRA'), 2022, 300000.0);

INSERT INTO emission_record (country_id, year_value, co2_kilotons)
VALUES ((SELECT id FROM country WHERE iso_code='USA'), 2021, 5000000.0);

INSERT INTO emission_record (country_id, year_value, co2_kilotons)
VALUES ((SELECT id FROM country WHERE iso_code='USA'), 2022, 5100000.0);

-- Länder (idempotent über KEY(iso_code))
MERGE INTO country (iso_code, name) KEY(iso_code) VALUES ('DEU', 'Deutschland');
MERGE INTO country (iso_code, name) KEY(iso_code) VALUES ('FRA', 'Frankreich');
MERGE INTO country (iso_code, name) KEY(iso_code) VALUES ('USA', 'Vereinigte Staaten');

-- Emissionen (idempotent über zusammengesetzten Key: country_id + year_value)
MERGE INTO emission_record (country_id, year_value, co2_kilotons) KEY(country_id, year_value)
VALUES ((SELECT id FROM country WHERE iso_code='DEU'), 2021, 650000.0);

MERGE INTO emission_record (country_id, year_value, co2_kilotons) KEY(country_id, year_value)
VALUES ((SELECT id FROM country WHERE iso_code='DEU'), 2022, 640000.0);

MERGE INTO emission_record (country_id, year_value, co2_kilotons) KEY(country_id, year_value)
VALUES ((SELECT id FROM country WHERE iso_code='FRA'), 2022, 300000.0);

MERGE INTO emission_record (country_id, year_value, co2_kilotons) KEY(country_id, year_value)
VALUES ((SELECT id FROM country WHERE iso_code='USA'), 2021, 5000000.0);

MERGE INTO emission_record (country_id, year_value, co2_kilotons) KEY(country_id, year_value)
VALUES ((SELECT id FROM country WHERE iso_code='USA'), 2022, 5100000.0);

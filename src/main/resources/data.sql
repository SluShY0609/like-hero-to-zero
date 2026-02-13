-- Länder
INSERT INTO country (id, iso_code, name) VALUES (1, 'DEU', 'Deutschland');
INSERT INTO country (id, iso_code, name) VALUES (2, 'FRA', 'Frankreich');
INSERT INTO country (id, iso_code, name) VALUES (3, 'USA', 'Vereinigte Staaten');

-- Emissionen (kt)
INSERT INTO emission_record (id, country_id, year_value, co2_kilotons) VALUES (1, 1, 2021, 650000.0);
INSERT INTO emission_record (id, country_id, year_value, co2_kilotons) VALUES (2, 1, 2022, 640000.0);

INSERT INTO emission_record (id, country_id, year_value, co2_kilotons) VALUES (3, 2, 2022, 300000.0);

INSERT INTO emission_record (id, country_id, year_value, co2_kilotons) VALUES (4, 3, 2021, 5000000.0);
INSERT INTO emission_record (id, country_id, year_value, co2_kilotons) VALUES (5, 3, 2022, 5100000.0);

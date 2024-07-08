INSERT INTO client (name)
    VALUES
    ('Ievhen Nesterenko'),('Olha Nesterenko'),
    ('Anton Sergienko'),('Victoria Voya'),
    ('Stanislav Vechich'),('Oleg Striy'),
    ('Peter Brosnan'),('Bogdan Stelia'),
    ('Alina Volkovich'),('Simon Petliura');

INSERT INTO planet (id,name)
VALUES
    ('EARTH','Earth'),
    ('MAR','Mars'),
    ('VEN','Venus'),
    ('NEP','Neptune'),
    ('STRN','Saturn');

INSERT INTO ticket (client_id,from_planet_id,to_planet_id)
VALUES
    (1,'EARTH','MAR'),
    (2,'EARTH','VEN'),
    (7,'NEP','MAR'),
    (4,'STRN','VEN'),
    (6,'VEN','NEP')

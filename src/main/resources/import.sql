-- Einfügen von Beispiel-Datensätzen in die Tabelle 'kunde'
INSERT INTO kunde (id, vorname, nachname, strasse, hausnummer, plz, ort) VALUES (1, 'Max', 'Mustermann', 'Musterstrasse', '1', '12345', 'Musterstadt');
INSERT INTO kunde (id, vorname, nachname, strasse, hausnummer, plz, ort) VALUES (2, 'Erika', 'Musterfrau', 'Beispielweg', '2', '67890', 'Beispielstadt');

-- Einfügen von Beispiel-Datensätzen in die Tabelle 'pizza'
INSERT INTO pizza (id, name, preis) VALUES (101, 'Margherita', 7.50);
INSERT INTO pizza (id, name, preis) VALUES (102, 'Salami', 8.50);
INSERT INTO pizza (id, name, preis) VALUES (103, 'Hawaii', 9.00);

-- Einfügen von Beispiel-Datensätzen in die Tabelle 'bestellung'
INSERT INTO bestellung (id, kunde_id) VALUES (1, 1);
INSERT INTO bestellung (id, kunde_id) VALUES (2, 2);

-- Einfügen von Beispiel-Datensätzen in die Tabelle 'bestellposten'
INSERT INTO bestellung_bestellposten (bestellung_id, pizza_id, anzahl) VALUES (1, 101, 2);
INSERT INTO bestellung_bestellposten (bestellung_id, pizza_id, anzahl) VALUES (2, 102, 1);
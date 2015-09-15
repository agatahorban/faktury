INSERT INTO role_( version, name) VALUES (1, 'USER');
INSERT INTO role_( version, name) VALUES (1, 'ADMIN');

INSERT INTO invoices_user(version, login, pass) VALUES (1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

INSERT INTO user_role( version, role_, username) VALUES (1, 'USER', 'admin');
INSERT INTO user_role( version, role_, username) VALUES (1, 'ADMIN', 'admin');

INSERT INTO role_( version, name) VALUES (1, 'PERM_GOODS');
INSERT INTO role_( version, name) VALUES (1, 'PERM_COLLECTIVE_PACKAGE');
INSERT INTO role_( version, name) VALUES (1, 'PERM_GROUP');
INSERT INTO role_( version, name) VALUES (1, 'PERM_VAT_RATE');

INSERT INTO role_( version, name) VALUES (1, 'PERM_CONTR');
INSERT INTO role_( version, name) VALUES (1, 'PERM_PAYMENT_DATE');
INSERT INTO role_( version, name) VALUES (1, 'PERM_PAYMENT_BANK');

INSERT INTO group_(version, name) VALUES (1, 'Łódź');
INSERT INTO collective_package(version, capacity, full_name, cut_name, depth, height, weight, width)
			VALUES (1, 45, 'Podstawowe opakowanie', 'PO',1,1,1,1);
INSERT INTO vat_rate(version, _value, name, description) VALUES (1, 23, '23%', 'Standard');
INSERT INTO unit_of_measure(version, shortcut, name) VALUES (1, 'szt', 'Szutka');
INSERT INTO address(version, apartment_number, borough, country, county, house_number, postal_code, province, street, town)
			VALUES (1, 12, 'łódź', 'Polska', 'łódzki', 34, '56-123', 'łódzkie', 'Maratońska', 'Łódź');
INSERT INTO bank(version, bank_name, currency, "number") VALUES (1, 'ING', 'PLN', '78105014611000009092788901');
INSERT INTO payment_date(version, amountofdays, description) VALUES (1, 25, 'Płatność w ciągu 25 dni.');
INSERT INTO contractor(version, nip, is_supplier, is_addressee, is_vat_payer, cut_name, full_name, address_id, bank_id, payment_date_id) 
VALUES (
	1, 
	'1234567891111',
	TRUE, 
	TRUE, 
	TRUE, 
	'Mecalit', 
	'Mecalit', 
	(SELECT id FROM address WHERE street = 'Maratońska'),
	(SELECT id FROM bank WHERE "number" = '78105014611000009092788901'),
	(SELECT id FROM payment_date WHERE amountofdays = 25)
);

INSERT INTO goods(version, index1, index2, name, price, is_price_higher, quantity, 
            collective_package_id, group_id, vat_rate_id, supplier_id, unit_of_measure_id)
VALUES (
    1, 
    '10.23-75', 
    '23.24.567',
	'Towar 1',
    25, 
    FALSE, 
    500, 
    (SELECT id FROM collective_package WHERE cut_name = 'PO'), 
    (SELECT id FROM group_ WHERE name = 'Łódź'), 
    (SELECT id FROM vat_rate WHERE _value = 23), 
    (SELECT id FROM contractor WHERE cut_name = 'Mecalit'), 
    (SELECT id FROM unit_of_measure WHERE shortcut = 'szt')
);

CREATE OR REPLACE FUNCTION f_trig_cont() RETURNS trigger AS
$$
BEGIN
delete from goods where supplier_id = OLD.id;
RETURN OLD;
END
$$
language plpgsql VOLATILE; 

CREATE TRIGGER trig_cont
BEFORE DELETE 
ON contractor
FOR EACH ROW
EXECUTE PROCEDURE f_trig_cont();
RETURN OLD;
USE train_station;

-- 10 statements for insertion

INSERT INTO train_stations
VALUES (
DEFAULT,
'Grand Central Terminal',
'New York, NY'
);

INSERT INTO trains
VALUES (
DEFAULT,
'20th Century Limited',
'passenger train'
);

INSERT INTO train_schedules
VALUES (
DEFAULT,
'2023-01-01',
30,
26
);

INSERT INTO train_maintenance
VALUES (
DEFAULT,
'2023-06-01',
'Corrective Railway Maintenance',
26
);

INSERT INTO platform_types
VALUES (
DEFAULT,
'passenger platform'
);

INSERT INTO platforms
VALUES (
DEFAULT,
1,
1,
30
);

INSERT INTO employees
VALUES (
DEFAULT,
'John',
'Doe',
'Maintenance guy',
30
);

INSERT INTO maintenance_employees
VALUES (
27,
33
);

INSERT INTO employee_shifts
VALUES (
DEFAULT,
'2023-09-01',
'2023-09-07',
33
);

INSERT INTO passengers
VALUES (
DEFAULT,
'Jane',
'Doe',
26
);

INSERT INTO tickets
VALUES (
DEFAULT,
3,
83,
53
);

INSERT INTO ticket_types (type)
VALUES 
('one-way'),
('peak'),
('off-peak');

-- multiple rows insertion

INSERT INTO ticket_prices (price, ticket_id)
VALUES 
(18.5, 42),
(34.2, 43),
(40, 44);

INSERT INTO tickets
VALUES (
DEFAULT,
4,
83,
LAST_INSERT_ID()
);

-- 10 statements for updating.

UPDATE train_stations
SET name = 'Pennsylvania Station'
WHERE id = 1;

UPDATE trains
SET name = 'Orient Express', type = 'luxury train'
WHERE id = 1;

UPDATE train_schedules
SET arrival_date = '2023-01-03'
WHERE id = 1;

UPDATE train_maintenance
SET type = 'Routine Maintenance'
WHERE id = 1;

UPDATE platforms
SET tracks_count = 1
WHERE id = 1;

UPDATE employees
SET first_name = 'Jack', last_name = 'Daniels', position = 'Maintenance guy\'s best friend'
WHERE id = 1;

UPDATE maintenance_employees
SET grade = 'Novice'
WHERE id = 1;

UPDATE employee_shifts
SET start_date = '2023-09-02', end_date = '2023-09-08'
WHERE id = 1;

UPDATE passengers
SET last_name = 'Austen'
WHERE id = 1;

UPDATE ticket_types
SET type = 'round trip'
WHERE id = 1;

-- subqueries with update

UPDATE ticket_prices
SET price = 99.99
WHERE id IN 
		(SELECT price_id
		FROM tickets
		WHERE seat_number = 1);

UPDATE ticket_types
SET type = 'first-class'
WHERE id IN 
		(SELECT type_id
		FROM tickets
		WHERE seat_number BETWEEN 3 AND 6);

-- 10 statements for deletions.

DELETE FROM tickets
WHERE id = 40;

DELETE FROM platforms
WHERE id = 2;

DELETE FROM employee_shifts
WHERE id = 1;

DELETE FROM maintenance_employees
WHERE id = 1;

DELETE FROM train_schedules
WHERE id = 1;

DELETE FROM train_stations
WHERE id = 2;

DELETE FROM trains
WHERE id = 2;

DELETE FROM employees
WHERE id = 40;

DELETE FROM ticket_prices
WHERE id = 2;

DELETE FROM ticket_types
WHERE id = 2;

-- subqueries with delete

DELETE FROM ticket_types
WHERE id IN
		(SELECT price_id
		FROM tickets
		WHERE seat_number > 50);

DELETE FROM ticket_prices
WHERE id IN 
		(SELECT price_id
		FROM tickets
		WHERE seat_number >= 10 AND seat_number <= 15);

-- 5 alter table.

ALTER TABLE train_stations
ADD max_capacity INT;

ALTER TABLE train_stations
MODIFY COLUMN max_capacity DEC;

ALTER TABLE train_stations
DROP COLUMN max_capacity;

ALTER TABLE trains
ADD year_built INT;

ALTER TABLE trains
DROP COLUMN year_built;

-- 1 big statement to join all tables in the database.

SELECT
    ts.name AS 'train station\'s name',
    ts.location,
    pl.number AS 'platform number',
    pt.type AS 'platform type',
    e.first_name AS 'employee\'s first name',
    e.last_name AS 'employee\'s last name',
    e.position,
    esh.start_date,
    esh.end_date,
    tm.date AS 'maintenance date',
    tm.type AS 'maintenance type',
    t.name AS 'train\'s name',
    t.type AS 'train\'s type',
    tsch.date,
    p.first_name AS 'passenger\'s first name',
    p.last_name AS 'passenger\'s last name',
    tkts.seat_number,
    tp.price,
    tt.type AS 'ticket type'
FROM train_stations ts
LEFT JOIN platforms pl
	ON ts.id = pl.station_id
LEFT JOIN platform_types pt
	ON pt.id = pl.type_id
LEFT JOIN employees e
	ON ts.id = e.station_id
LEFT JOIN employee_shifts esh
	ON e.id = esh.employee_id
LEFT JOIN maintenance_employees me
	ON e.id = me.employee_id
LEFT JOIN train_maintenance tm
	ON tm.id = me.maintenance_id
LEFT JOIN trains t
	ON t.id = tm.train_id
LEFT JOIN train_schedules tsch
	ON t.id = tsch.train_id
LEFT JOIN passengers p
	ON t.id = p.train_id
LEFT JOIN tickets tkts
	ON p.id = tkts.passenger_id
LEFT JOIN ticket_types tt
	ON tt.id = tkts.type_id
LEFT JOIN ticket_prices tp
	ON tp.ticket_id = tkts.id;

-- 5 statements with left, right, inner, outer joins.

SELECT
	p.first_name,
	p.last_name,
	t.seat_number,
	tp.price
FROM tickets t
JOIN ticket_prices tp
	ON t.price_id = tp.id
JOIN passengers p
	ON t.passenger_id = p.id;

SELECT 
    t.name AS 'train name',
    t.type AS 'train type',
    tm.date AS 'maintenance date',
    tm.type AS 'maintenance type',
    e.first_name,
    e.last_name,
    e.position,
    me.grade
FROM trains t
JOIN train_maintenance tm
	ON t.id = tm.train_id
JOIN maintenance_employees me
	ON tm.id = me.train_maintenance_id
JOIN employees e
	ON e.id = me.employee_id;

SELECT
    tst.name AS "train station name",
    tst.location,
    ts.train_id,
    ts.departure_date,
    ts.arrival_date
FROM train_stations tst
LEFT JOIN train_schedules ts
	ON tst.id = ts.train_station_id;

SELECT 
	e.first_name,
    e.last_name,
    e.position,
    es.start_date,
    end_date
FROM employee_shifts es
RIGHT JOIN employees e
	ON e.id = es.employee_id;

SELECT
    ts.name AS 'train station name',
    ts.location,
    t.name AS 'train name',
    t.type
FROM train_stations ts
LEFT JOIN trains t
	ON ts.id = t.train_station_id;

-- 7 statements with aggregate functions and group by and without having.

SELECT 
	first_name,
    last_name,
    COUNT(first_name) AS 'number of passengers'
FROM passengers
GROUP BY first_name, last_name
ORDER BY first_name;

SELECT 
	departure_date,
    COUNT(departure_date) AS 'number of departure dates'
FROM train_schedules
GROUP BY departure_date;

SELECT 
	seat_number,
    MIN(price) AS 'lowest ticket price'
FROM ticket_prices tp
JOIN tickets t
	ON tp.id = t.price_id
GROUP BY seat_number;

SELECT 
	seat_number,
    MAX(price) AS 'highest ticket price'
FROM ticket_prices tp
JOIN tickets t
	ON tp.id = t.price_id
GROUP BY seat_number;

SELECT 
	seat_number,
    COUNT(seat_number)  AS 'number of tickets',
    AVG(price) AS 'average ticket price'
FROM ticket_prices tp
JOIN tickets t
	ON tp.id = t.price_id
GROUP BY seat_number;

SELECT
	first_name,
    last_name,
    COUNT(first_name) AS 'number of employees'
FROM employees
GROUP BY first_name, last_name;

SELECT
	seat_number,
    SUM(seat_number) AS 'number of tickets'
FROM tickets
GROUP BY seat_number;

-- 7 statements with aggregate functions and group by and with having.

SELECT 
	first_name,
    last_name,
    COUNT(first_name) AS 'number of passengers'
FROM passengers
GROUP BY first_name, last_name
HAVING COUNT(first_name) BETWEEN 1 AND 5
ORDER BY first_name;

SELECT 
	departure_date,
    COUNT(departure_date) AS 'number of departure dates'
FROM train_schedules
GROUP BY departure_date
HAVING COUNT(departure_date) < 2;

SELECT 
	seat_number,
    MIN(price) AS 'lowest ticket price'
FROM ticket_prices tp
JOIN tickets t
	ON tp.id = t.price_id
GROUP BY seat_number
HAVING MIN(price) = 99.99;

SELECT 
	seat_number,
    MAX(price) AS 'highest ticket price'
FROM ticket_prices tp
JOIN tickets t
	ON tp.id = t.price_id
GROUP BY seat_number
HAVING MAX(price) >= 40;

SELECT 
	seat_number,
    COUNT(seat_number)  AS 'number of tickets',
    AVG(price) AS 'average ticket price'
FROM ticket_prices tp
JOIN tickets t
	ON tp.id = t.price_id
GROUP BY seat_number
HAVING COUNT(seat_number) > 1 AND AVG(price) < 40;

SELECT
	first_name,
    last_name,
    COUNT(first_name) AS 'number of employees'
FROM employees
GROUP BY first_name, last_name
HAVING COUNT(first_name) < 3
ORDER BY first_name DESC;

SELECT
	seat_number,
    SUM(seat_number) AS 'number of tickets'
FROM tickets
GROUP BY seat_number
HAVING SUM(seat_number) != 10

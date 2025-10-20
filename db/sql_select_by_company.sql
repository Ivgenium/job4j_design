CREATE TABLE companies
(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
);

CREATE TABLE people
(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    company_id INTEGER REFERENCES companies(id),
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

TRUNCATE TABLE people CASCADE;
TRUNCATE TABLE companies CASCADE;

INSERT INTO companies (id, name) VALUES (1, 'Company_1');
INSERT INTO companies (id, name) VALUES (2, 'Company_2');
INSERT INTO companies (id, name) VALUES (3, 'Company_3');
INSERT INTO companies (id, name) VALUES (4, 'Company_4');
INSERT INTO companies (id, name) VALUES (5, 'Company_5');
INSERT INTO companies (id, name) VALUES (6, 'Company_6');

INSERT INTO people (id, name, company_id) VALUES (1, 'Employee_1_1', 1);
INSERT INTO people (id, name, company_id) VALUES (2, 'Employee_2_1', 2);
INSERT INTO people (id, name, company_id) VALUES (3, 'Employee_2_2', 2);
INSERT INTO people (id, name, company_id) VALUES (4, 'Employee_3_1', 3);
INSERT INTO people (id, name, company_id) VALUES (5, 'Employee_3_2', 3);
INSERT INTO people (id, name, company_id) VALUES (6, 'Employee_3_3', 3);
INSERT INTO people (id, name, company_id) VALUES (7, 'Employee_4_1', 4);
INSERT INTO people (id, name, company_id) VALUES (8, 'Employee_4_2', 4);
INSERT INTO people (id, name, company_id) VALUES (9, 'Employee_4_3', 4);
INSERT INTO people (id, name, company_id) VALUES (10, 'Employee_5_1', 5);
INSERT INTO people (id, name, company_id) VALUES (11, 'Employee_5_2', 5);
INSERT INTO people (id, name, company_id) VALUES (12, 'Employee_5_3', 5);
INSERT INTO people (id, name, company_id) VALUES (13, 'Employee_6_1', 6);
INSERT INTO people (id, name, company_id) VALUES (14, 'Employee_6_2', 6);
INSERT INTO people (id, name, company_id) VALUES (15, 'Employee_6_3', 6);


SELECT name
FROM people WHERE company_id != 5;


SELECT person.name AS pn, company.name AS cn
FROM person
JOIN company ON person.company_id = company.id;


SELECT rsl.name,
	rsl.count_people
FROM
(SELECT subquery.name,
	subquery.count_people,
	DENSE_RANK() OVER(
		ORDER BY subquery.count_people DESC
	)
FROM 
(SELECT c.name,
	COUNT(c.id) count_people
FROM companies c JOIN people p ON c.id = p.company_id GROUP BY c.name) subquery) rsl
WHERE DENSE_RANK = 1
ORDER BY NAME
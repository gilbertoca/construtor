DROP TABLE STAY CASCADE;
DROP TABLE VEHICLE CASCADE;
DROP TABLE PARKING CASCADE;
DROP TABLE CUSTOMER CASCADE;
DROP TABLE EMPLOYEE CASCADE;
DROP TABLE NATURAL_PERSON CASCADE;
DROP TABLE LEGAL_ENTITY CASCADE;
DROP TABLE PERSON CASCADE;
DROP TABLE PRICE_TABLE CASCADE;
DROP TABLE VEHICLE_TYPE CASCADE;

CREATE TABLE vehicle_type (
                v_type VARCHAR(20) NOT NULL,
                manufacturer VARCHAR(50),
                model VARCHAR(20),
                CONSTRAINT vehicle_v_type_pkey PRIMARY KEY (v_type)
);


CREATE TABLE price_table (
                id_price_table INTEGER NOT NULL,
                item VARCHAR(50) NOT NULL,
                price NUMERIC(1000,0) NOT NULL,
                CONSTRAINT price_table_pkey PRIMARY KEY (id_price_table)
);


CREATE TABLE person (
                id_person INTEGER NOT NULL,
                address VARCHAR(100),
                name VARCHAR(100) NOT NULL,
                p_type CHAR(1) NOT NULL,
                CONSTRAINT person_pkey PRIMARY KEY (id_person)
);


CREATE TABLE natural_person (
                id_person INTEGER NOT NULL,
                dt_birth DATE,
                legal_document VARCHAR(20),
                CONSTRAINT natural_person_pkey PRIMARY KEY (id_person)
);


CREATE TABLE legal_entity (
                id_person INTEGER NOT NULL,
                taxpayers_id VARCHAR(20) NOT NULL,
                dt_foundation DATE NOT NULL,
                CONSTRAINT legal_entity_pkey PRIMARY KEY (id_person)
);


CREATE TABLE parking (
                id_parking INTEGER NOT NULL,
                id_person INTEGER NOT NULL,
                parking_spaces INTEGER,
                CONSTRAINT parking_pkey PRIMARY KEY (id_parking)
);


CREATE UNIQUE INDEX parking_idx
 ON parking
 ( id_parking, id_person );

CREATE TABLE employee (
                id_employee INTEGER NOT NULL,
                id_person INTEGER NOT NULL,
                dt_admission DATE NOT NULL,
                id_parking INTEGER NOT NULL,
                CONSTRAINT employee_pkey PRIMARY KEY (id_employee)
);


CREATE UNIQUE INDEX employee_idx
 ON employee
 ( id_person );

CREATE TABLE customer (
                id_customer INTEGER NOT NULL,
                id_person INTEGER NOT NULL,
                payment_day INTEGER,
                CONSTRAINT customer_pkey PRIMARY KEY (id_customer)
);


CREATE UNIQUE INDEX customer_idx
 ON customer
 ( id_person );

CREATE TABLE vehicle (
                license_plate VARCHAR(20) NOT NULL,
                v_type VARCHAR(20) NOT NULL,
                id_customer INTEGER NOT NULL,
                id_price_table INTEGER NOT NULL,
                color VARCHAR(20),
                CONSTRAINT vehicle_pkey PRIMARY KEY (license_plate)
);


CREATE TABLE stay (
                id_stay INTEGER NOT NULL,
                id_parking INTEGER NOT NULL,
                dt_entrance DATE NOT NULL,
                dt_outgoing DATE,
                hr_entrance TIME NOT NULL,
                hr_outgoing TIME,
                id_employee_entrance INTEGER NOT NULL,
                id_employee_outgoing INTEGER NOT NULL,
                license_plate VARCHAR(20) NOT NULL,
                status SMALLINT,
                price NUMERIC(1000,0),
                CONSTRAINT stay_pkey PRIMARY KEY (id_stay)
);


CREATE UNIQUE INDEX stay_idx
 ON stay
 ( id_stay, id_parking, dt_entrance, hr_entrance, id_employee_entrance, license_plate );

ALTER TABLE vehicle ADD CONSTRAINT vehicle_v_type_fkey
FOREIGN KEY (v_type)
REFERENCES vehicle_type (v_type)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE vehicle ADD CONSTRAINT vehicle_id_price_table_fkey
FOREIGN KEY (id_price_table)
REFERENCES price_table (id_price_table)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE customer ADD CONSTRAINT customer_id_person_fk
FOREIGN KEY (id_person)
REFERENCES person (id_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE legal_entity ADD CONSTRAINT legalentity_id_person_fkey
FOREIGN KEY (id_person)
REFERENCES person (id_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE natural_person ADD CONSTRAINT naturalperson_id_person_fkey
FOREIGN KEY (id_person)
REFERENCES person (id_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE employee ADD CONSTRAINT employee_id_person_fk
FOREIGN KEY (id_person)
REFERENCES natural_person (id_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE parking ADD CONSTRAINT parking_id_person_fk
FOREIGN KEY (id_person)
REFERENCES legal_entity (id_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE employee ADD CONSTRAINT parking_id_employee_fkey
FOREIGN KEY (id_parking)
REFERENCES parking (id_parking)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_id_parking_fkey
FOREIGN KEY (id_parking)
REFERENCES parking (id_parking)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_id_employee_outgoing_fkey
FOREIGN KEY (id_employee_outgoing)
REFERENCES employee (id_employee)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_id_employee_entrance_fkey
FOREIGN KEY (id_employee_entrance)
REFERENCES employee (id_employee)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE vehicle ADD CONSTRAINT vehicle_id_customer_fkey
FOREIGN KEY (id_customer)
REFERENCES customer (id_customer)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_license_plate_fkey
FOREIGN KEY (license_plate)
REFERENCES vehicle (license_plate)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
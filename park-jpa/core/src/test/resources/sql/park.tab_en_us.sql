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

CREATE TABLE park.vehicle_type (
                type VARCHAR(20) NOT NULL,
                manufacturer VARCHAR(50),
                model VARCHAR(20),
                CONSTRAINT vehicle_type_pkey PRIMARY KEY (type)
);


CREATE TABLE park.price_table (
                cd_price_table INTEGER NOT NULL,
                item VARCHAR(50) NOT NULL,
                price NUMERIC(1000,0) NOT NULL,
                CONSTRAINT price_table_pkey PRIMARY KEY (cd_price_table)
);


CREATE TABLE park.person (
                cd_person INTEGER NOT NULL,
                address VARCHAR(100),
                name VARCHAR(100) NOT NULL,
                TYPE CHAR(1) NOT NULL,
                CONSTRAINT person_pkey PRIMARY KEY (cd_person)
);


CREATE TABLE park.natural_person (
                cd_person INTEGER NOT NULL,
                dt_birth DATE,
                rg VARCHAR(20),
                CONSTRAINT natural_person_pkey PRIMARY KEY (cd_person)
);


CREATE TABLE park.legal_entity (
                cd_person INTEGER NOT NULL,
                taxpayers_id VARCHAR(20) NOT NULL,
                dt_foundation DATE NOT NULL,
                CONSTRAINT legal_entity_pkey PRIMARY KEY (cd_person)
);


CREATE TABLE park.parking (
                cd_parking INTEGER NOT NULL,
                cd_person INTEGER NOT NULL,
                parking_spaces INTEGER,
                CONSTRAINT parking_pkey PRIMARY KEY (cd_parking)
);


CREATE UNIQUE INDEX parking_idx
 ON park.parking
 ( cd_parking, cd_person );

CREATE TABLE park.employee (
                cd_employee INTEGER NOT NULL,
                cd_person INTEGER NOT NULL,
                dt_admission DATE NOT NULL,
                cd_parking INTEGER NOT NULL,
                username VARCHAR(20),
                password VARCHAR(20),
                CONSTRAINT employee_pkey PRIMARY KEY (cd_employee)
);


CREATE UNIQUE INDEX employee_idx
 ON park.employee
 ( cd_person );

CREATE TABLE park.customer (
                cd_customer INTEGER NOT NULL,
                cd_person INTEGER NOT NULL,
                payment_day INTEGER,
                CONSTRAINT customer_pkey PRIMARY KEY (cd_customer)
);


CREATE UNIQUE INDEX customer_idx
 ON park.customer
 ( cd_person );

CREATE TABLE park.vehicle (
                license_plate VARCHAR(20) NOT NULL,
                TYPE VARCHAR(20) NOT NULL,
                cd_customer INTEGER NOT NULL,
                cd_price_table INTEGER NOT NULL,
                color VARCHAR(20),
                CONSTRAINT vehicle_pkey PRIMARY KEY (license_plate)
);


CREATE TABLE park.stay (
                cd_stay INTEGER NOT NULL,
                cd_parking INTEGER NOT NULL,
                dt_entrance DATE NOT NULL,
                dt_outgoing DATE,
                hr_entrance TIME NOT NULL,
                hr_outgoing TIME,
                cd_employee_entrance INTEGER NOT NULL,
                cd_employee_outgoing INTEGER NOT NULL,
                license_plate VARCHAR(20) NOT NULL,
                status SMALLINT,
                price NUMERIC(1000,0),
                CONSTRAINT stay_pkey PRIMARY KEY (cd_stay)
);


CREATE UNIQUE INDEX stay_idx
 ON park.stay
 ( cd_stay, cd_parking, dt_entrance, hr_entrance, cd_employee_entrance, license_plate );

ALTER TABLE park.vehicle ADD CONSTRAINT vehicle_type_fkey
FOREIGN KEY (TYPE)
REFERENCES park.vehicle_type (type)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE park.vehicle ADD CONSTRAINT vehicle_cd_price_table_fkey
FOREIGN KEY (cd_price_table)
REFERENCES park.price_table (cd_price_table)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE park.customer ADD CONSTRAINT customer_cd_person_fk
FOREIGN KEY (cd_person)
REFERENCES park.person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE park.legal_entity ADD CONSTRAINT legalentity_cd_person_fkey
FOREIGN KEY (cd_person)
REFERENCES park.person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE park.natural_person ADD CONSTRAINT naturalperson_cd_person_fkey
FOREIGN KEY (cd_person)
REFERENCES park.person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE park.employee ADD CONSTRAINT employee_cd_person_fk
FOREIGN KEY (cd_person)
REFERENCES park.natural_person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE park.parking ADD CONSTRAINT parking_cd_person_fk
FOREIGN KEY (cd_person)
REFERENCES park.legal_entity (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE park.employee ADD CONSTRAINT parking_cd_employee_fkey
FOREIGN KEY (cd_parking)
REFERENCES park.parking (cd_parking)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE park.stay ADD CONSTRAINT stay_cd_parking_fkey
FOREIGN KEY (cd_parking)
REFERENCES park.parking (cd_parking)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE park.stay ADD CONSTRAINT stay_cd_employee_outgoing_fkey
FOREIGN KEY (cd_employee_outgoing)
REFERENCES park.employee (cd_employee)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE park.stay ADD CONSTRAINT stay_cd_employee_entrance_fkey
FOREIGN KEY (cd_employee_entrance)
REFERENCES park.employee (cd_employee)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE park.vehicle ADD CONSTRAINT vehicle_cd_customer_fkey
FOREIGN KEY (cd_customer)
REFERENCES park.customer (cd_customer)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE park.stay ADD CONSTRAINT stay_license_plate_fkey
FOREIGN KEY (license_plate)
REFERENCES park.vehicle (license_plate)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
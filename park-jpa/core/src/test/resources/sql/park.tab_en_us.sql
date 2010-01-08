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
                cd_price_table INTEGER NOT NULL,
                item VARCHAR(50) NOT NULL,
                price NUMERIC(1000,0) NOT NULL,
                CONSTRAINT price_table_pkey PRIMARY KEY (cd_price_table)
);


CREATE TABLE person (
                cd_person INTEGER NOT NULL,
                address VARCHAR(100),
                name VARCHAR(100) NOT NULL,
                p_type CHAR(1) NOT NULL,
                CONSTRAINT person_pkey PRIMARY KEY (cd_person)
);


CREATE TABLE natural_person (
                cd_person INTEGER NOT NULL,
                dt_birth DATE,
                legal_document VARCHAR(20),
                CONSTRAINT natural_person_pkey PRIMARY KEY (cd_person)
);


CREATE TABLE legal_entity (
                cd_person INTEGER NOT NULL,
                taxpayers_id VARCHAR(20) NOT NULL,
                dt_foundation DATE NOT NULL,
                CONSTRAINT legal_entity_pkey PRIMARY KEY (cd_person)
);


CREATE TABLE parking (
                cd_parking INTEGER NOT NULL,
                cd_person INTEGER NOT NULL,
                parking_spaces INTEGER,
                CONSTRAINT parking_pkey PRIMARY KEY (cd_parking)
);


CREATE UNIQUE INDEX parking_idx
 ON parking
 ( cd_parking, cd_person );

CREATE TABLE employee (
                cd_employee INTEGER NOT NULL,
                cd_person INTEGER NOT NULL,
                dt_admission DATE NOT NULL,
                cd_parking INTEGER NOT NULL,
                username VARCHAR(20),
                password VARCHAR(20),
                CONSTRAINT employee_pkey PRIMARY KEY (cd_employee)
);


CREATE UNIQUE INDEX employee_idx
 ON employee
 ( cd_person );

CREATE TABLE customer (
                cd_customer INTEGER NOT NULL,
                cd_person INTEGER NOT NULL,
                payment_day INTEGER,
                CONSTRAINT customer_pkey PRIMARY KEY (cd_customer)
);


CREATE UNIQUE INDEX customer_idx
 ON customer
 ( cd_person );

CREATE TABLE vehicle (
                license_plate VARCHAR(20) NOT NULL,
                v_type VARCHAR(20) NOT NULL,
                cd_customer INTEGER NOT NULL,
                cd_price_table INTEGER NOT NULL,
                color VARCHAR(20),
                CONSTRAINT vehicle_pkey PRIMARY KEY (license_plate)
);


CREATE TABLE stay (
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
 ON stay
 ( cd_stay, cd_parking, dt_entrance, hr_entrance, cd_employee_entrance, license_plate );

ALTER TABLE vehicle ADD CONSTRAINT vehicle_v_type_fkey
FOREIGN KEY (v_type)
REFERENCES vehicle_type (v_type)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE vehicle ADD CONSTRAINT vehicle_cd_price_table_fkey
FOREIGN KEY (cd_price_table)
REFERENCES price_table (cd_price_table)
ON DELETE RESTRICT
ON UPDATE RESTRICT
NOT DEFERRABLE;

ALTER TABLE customer ADD CONSTRAINT customer_cd_person_fk
FOREIGN KEY (cd_person)
REFERENCES person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE legal_entity ADD CONSTRAINT legalentity_cd_person_fkey
FOREIGN KEY (cd_person)
REFERENCES person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE natural_person ADD CONSTRAINT naturalperson_cd_person_fkey
FOREIGN KEY (cd_person)
REFERENCES person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE employee ADD CONSTRAINT employee_cd_person_fk
FOREIGN KEY (cd_person)
REFERENCES natural_person (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE parking ADD CONSTRAINT parking_cd_person_fk
FOREIGN KEY (cd_person)
REFERENCES legal_entity (cd_person)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE employee ADD CONSTRAINT parking_cd_employee_fkey
FOREIGN KEY (cd_parking)
REFERENCES parking (cd_parking)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_cd_parking_fkey
FOREIGN KEY (cd_parking)
REFERENCES parking (cd_parking)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_cd_employee_outgoing_fkey
FOREIGN KEY (cd_employee_outgoing)
REFERENCES employee (cd_employee)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_cd_employee_entrance_fkey
FOREIGN KEY (cd_employee_entrance)
REFERENCES employee (cd_employee)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE vehicle ADD CONSTRAINT vehicle_cd_customer_fkey
FOREIGN KEY (cd_customer)
REFERENCES customer (cd_customer)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stay ADD CONSTRAINT stay_license_plate_fkey
FOREIGN KEY (license_plate)
REFERENCES vehicle (license_plate)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
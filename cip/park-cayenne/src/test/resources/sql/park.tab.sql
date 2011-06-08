CREATE TABLE TABLE_GENERATOR (
                ENTITY_NAME VARCHAR(50) NOT NULL,
                SEQUENCE_VALUE NUMERIC(38),
                CONSTRAINT TABLE_GENERATOR_PK PRIMARY KEY (ENTITY_NAME)
);

INSERT INTO TABLE_GENERATOR (ENTITY_NAME, SEQUENCE_VALUE) VALUES ('PRICE_TABLE', 1);
INSERT INTO TABLE_GENERATOR (ENTITY_NAME, SEQUENCE_VALUE) VALUES ('PARKING', 1);
INSERT INTO TABLE_GENERATOR (ENTITY_NAME, SEQUENCE_VALUE) VALUES ('PERSON', 1);
INSERT INTO TABLE_GENERATOR (ENTITY_NAME, SEQUENCE_VALUE) VALUES ('STAY', 1);


CREATE TABLE PARKING (
                ID BIGINT NOT NULL,
                ADDRESS VARCHAR(100),
                PARKING_SPACE INTEGER,
                CONSTRAINT parking_pk PRIMARY KEY (ID)
);

CREATE TABLE VEHICLE_TYPE (
                V_TYPE VARCHAR(20) NOT NULL,
                MANUFACTURER VARCHAR(50),
                MODEL VARCHAR(20),
                CONSTRAINT vehicle_type_pk PRIMARY KEY (V_TYPE)
);

CREATE TABLE PRICE_TABLE (
                ID INTEGER NOT NULL,
                ITEM VARCHAR(50) NOT NULL,
                PRICE NUMERIC(12,2),
                CONSTRAINT price_table_pk PRIMARY KEY (ID)
);

CREATE TABLE PERSON (
                ID BIGINT NOT NULL,
                ADDRESS VARCHAR(100),
                NAME VARCHAR(100),
                P_TYPE CHAR(2),
                VERSION INTEGER,
                CONSTRAINT person_pk PRIMARY KEY (ID)
);


CREATE TABLE CUSTOMER (
                ID BIGINT NOT NULL,
                PAYMENT_DAY INTEGER,
                CONSTRAINT customer_pk PRIMARY KEY (ID)
);


CREATE TABLE VEHICLE (
                LICENSE_PLATE VARCHAR(20) NOT NULL,
                COLOR VARCHAR(20),
                CUSTOMER_ID BIGINT,
                PRICE_TABLE_ID INTEGER,
                V_TYPE VARCHAR(20),
                CONSTRAINT vehicle_pk PRIMARY KEY (LICENSE_PLATE)
);


CREATE TABLE NATURAL_PERSON (
                ID BIGINT NOT NULL,
                DT_BIRTH DATE,
                LEGAL_DOCUMENT VARCHAR(20),
                CONSTRAINT natural_person_pk PRIMARY KEY (ID)
);

CREATE TABLE EMPLOYEE (
                ID BIGINT NOT NULL,
                DT_ADMISSION DATE,
                PARKING_ID BIGINT,
                CONSTRAINT employee_pk PRIMARY KEY (ID)
);


CREATE TABLE STAY (
                ID INTEGER NOT NULL,
                DT_ENTRANCE DATE,
                DT_OUTGOING DATE,
                HR_ENTRANCE TIME,
                HR_OUTGOING TIME,
                STATUS VARCHAR(255),
                TOTAL_PRICE NUMERIC(12,2),
                LICENSE_PLATE VARCHAR(20),
                EMPLOYEE_ID_ENTRANCE BIGINT,
                EMPLOYEE_ID_OUTGOING BIGINT,
                PARKING_ID BIGINT,
                VERSION INTEGER,
                CONSTRAINT stay_pk PRIMARY KEY (ID)
);

CREATE TABLE LEGAL_ENTITY (
                ID BIGINT NOT NULL,
                DT_FOUNDATION DATE,
                TAXPAYERS_ID VARCHAR(20),
                CONSTRAINT legal_entity_pk PRIMARY KEY (ID)
);


ALTER TABLE EMPLOYEE ADD CONSTRAINT fk_employee_parking_id
FOREIGN KEY (PARKING_ID)
REFERENCES PARKING (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT fk_stay_parking_id
FOREIGN KEY (PARKING_ID)
REFERENCES PARKING (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE VEHICLE ADD CONSTRAINT fk_vehicle_v_type
FOREIGN KEY (V_TYPE)
REFERENCES VEHICLE_TYPE (V_TYPE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE VEHICLE ADD CONSTRAINT fk_vehicle_price_table_id
FOREIGN KEY (PRICE_TABLE_ID)
REFERENCES PRICE_TABLE (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE LEGAL_ENTITY ADD CONSTRAINT fk_legal_entity_person_id
FOREIGN KEY (ID)
REFERENCES PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE NATURAL_PERSON ADD CONSTRAINT fk_natural_person_person_id
FOREIGN KEY (ID)
REFERENCES PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE CUSTOMER ADD CONSTRAINT fk_customer_person_id
FOREIGN KEY (ID)
REFERENCES PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE VEHICLE ADD CONSTRAINT fk_vehicle_customer_id
FOREIGN KEY (CUSTOMER_ID)
REFERENCES CUSTOMER (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT fk_stay_license_plate
FOREIGN KEY (LICENSE_PLATE)
REFERENCES VEHICLE (LICENSE_PLATE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE EMPLOYEE ADD CONSTRAINT fk_employee_person_id
FOREIGN KEY (ID)
REFERENCES NATURAL_PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT fk_stay_employee_id_entrance
FOREIGN KEY (EMPLOYEE_ID_ENTRANCE)
REFERENCES EMPLOYEE (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT fk_stay_employee_id_outgoing
FOREIGN KEY (EMPLOYEE_ID_OUTGOING)
REFERENCES EMPLOYEE (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

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
                CONSTRAINT PARKING_PK PRIMARY KEY (ID)
);

CREATE TABLE VEHICLE_TYPE (
                V_TYPE VARCHAR(20) NOT NULL,
                MANUFACTURER VARCHAR(50),
                MODEL VARCHAR(20),
                CONSTRAINT VEHICLE_TYPE_PK PRIMARY KEY (V_TYPE)
);

CREATE TABLE PRICE_TABLE (
                ID INTEGER NOT NULL,
                ITEM VARCHAR(50) NOT NULL,
                PRICE NUMERIC(12,2),
                CONSTRAINT PRICE_TABLE_PK PRIMARY KEY (ID)
);

CREATE TABLE PERSON (
                ID BIGINT NOT NULL,
                ADDRESS VARCHAR(100),
                NAME VARCHAR(100),
                P_TYPE CHAR(2),
                VERSION INTEGER,
                CONSTRAINT PERSON_PK PRIMARY KEY (ID)
);


CREATE TABLE CUSTOMER (
                PERSON_ID BIGINT NOT NULL,
                PAYMENT_DAY INTEGER,
                CONSTRAINT CUSTOMER_PK PRIMARY KEY (PERSON_ID)
);


CREATE TABLE VEHICLE (
                LICENSE_PLATE VARCHAR(20) NOT NULL,
                COLOR VARCHAR(20),
                CUSTOMER_ID BIGINT,
                PRICE_TABLE_ID INTEGER,
                V_TYPE VARCHAR(20),
                CONSTRAINT VEHICLE_PK PRIMARY KEY (LICENSE_PLATE)
);


CREATE TABLE NATURAL_PERSON (
                PERSON_ID BIGINT NOT NULL,
                DT_BIRTH DATE,
                LEGAL_DOCUMENT VARCHAR(20),
                CONSTRAINT NATURAL_PERSON_PK PRIMARY KEY (PERSON_ID)
);

CREATE TABLE EMPLOYEE (
                NATURAL_PERSON_ID BIGINT NOT NULL,
                DT_ADMISSION DATE,
                PARKING_ID BIGINT,
                CONSTRAINT EMPLOYEE_PK PRIMARY KEY (NATURAL_PERSON_ID)
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
                CONSTRAINT STAY_PK PRIMARY KEY (ID)
);

CREATE TABLE LEGAL_ENTITY (
                PERSON_ID BIGINT NOT NULL,
                DT_FOUNDATION DATE,
                TAXPAYERS_ID VARCHAR(20),
                CONSTRAINT LEGAL_ENTITY_PK PRIMARY KEY (PERSON_ID)
);


ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_PARKING_ID
FOREIGN KEY (PARKING_ID)
REFERENCES PARKING (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT FK_STAY_PARKING_ID
FOREIGN KEY (PARKING_ID)
REFERENCES PARKING (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE VEHICLE ADD CONSTRAINT FK_VEHICLE_V_TYPE
FOREIGN KEY (V_TYPE)
REFERENCES VEHICLE_TYPE (V_TYPE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE VEHICLE ADD CONSTRAINT FK_VEHICLE_PRICE_TABLE_ID
FOREIGN KEY (PRICE_TABLE_ID)
REFERENCES PRICE_TABLE (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE LEGAL_ENTITY ADD CONSTRAINT FK_LEGAL_ENTITY_PERSON_ID
FOREIGN KEY (PERSON_ID)
REFERENCES PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE NATURAL_PERSON ADD CONSTRAINT FK_NATURAL_PERSON_PERSON_ID
FOREIGN KEY (PERSON_ID)
REFERENCES PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE CUSTOMER ADD CONSTRAINT FK_CUSTOMER_PERSON_ID
FOREIGN KEY (PERSON_ID)
REFERENCES PERSON (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE VEHICLE ADD CONSTRAINT FK_VEHICLE_CUSTOMER_ID
FOREIGN KEY (CUSTOMER_ID)
REFERENCES CUSTOMER (PERSON_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT FK_STAY_LICENSE_PLATE
FOREIGN KEY (LICENSE_PLATE)
REFERENCES VEHICLE (LICENSE_PLATE)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_PERSON_ID
FOREIGN KEY (NATURAL_PERSON_ID)
REFERENCES NATURAL_PERSON (PERSON_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT FK_STAY_EMPLOYEE_ID_ENTRANCE
FOREIGN KEY (EMPLOYEE_ID_ENTRANCE)
REFERENCES EMPLOYEE (NATURAL_PERSON_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE STAY ADD CONSTRAINT FK_STAY_EMPLOYEE_ID_OUTGOING
FOREIGN KEY (EMPLOYEE_ID_OUTGOING)
REFERENCES EMPLOYEE (NATURAL_PERSON_ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

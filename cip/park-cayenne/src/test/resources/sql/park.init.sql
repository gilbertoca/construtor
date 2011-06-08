--Needs to be first
DELETE FROM VEHICLE;
DELETE FROM VEHICLE_TYPE;
DELETE FROM PRICE_TABLE;

--Now it will work
DELETE FROM CUSTOMER;
DELETE FROM NATURAL_PERSON;
DELETE FROM LEGAL_ENTITY;
DELETE FROM PERSON;

--PERSON TABLE;
INSERT INTO PERSON (ID, ADDRESS,	NAME, P_TYPE)	VALUES(1,'ADDRESS1','LEGAL_ENTITY','LE');
INSERT INTO PERSON (ID, ADDRESS,	NAME, P_TYPE)	VALUES(2,'ADDRESS2','NATURAL_PERSON','NP');
--LEGAL_ENTITY TABLE
INSERT INTO LEGAL_ENTITY (PERSON_ID,TAXPAYERS_ID, DT_FOUNDATION)	VALUES(1,'1212','2009-03-21');
--NATURAL_PERSON TABLE
INSERT INTO NATURAL_PERSON (PERSON_ID, DT_BIRTH, LEGAL_DOCUMENT)	VALUES(2,'2009-03-21', '19552 SSP');
--CUSTOMER TABLE
INSERT INTO CUSTOMER (PERSON_ID, PAYMENT_DAY)	VALUES(2, 2);


--VEHICLE
INSERT INTO PRICE_TABLE(ID, ITEM, PRICE) VALUES(1, 'CARRO',2.00);
--VEHICLE_TYPE
INSERT INTO VEHICLE_TYPE(V_TYPE, MANUFACTURER, MODEL) VALUES('CARRO', 'FIAT','UNO');
--PRICE_TABLE
INSERT INTO VEHICLE(CUSTOMER_ID, PRICE_TABLE_ID, COLOR, LICENSE_PLATE,V_TYPE) VALUES(2, 1, 'COR', 'PLACA','CARRO');

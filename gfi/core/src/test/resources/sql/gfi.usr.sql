drop schema gfi;
create schema gfi;

CREATE DOMAIN BOOLEAN AS VARCHAR(1) DEFAULT '0' CHECK (VALUE IN ('S','s', 'N','n','0','1'));

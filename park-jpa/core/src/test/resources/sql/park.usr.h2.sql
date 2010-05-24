-- We assume the Database existence: "parkDB"

CREATE USER IF NOT EXISTS park PASSWORD 'park';

-- Role: "park"
CREATE ROLE IF NOT EXISTS park;

-- Schema: "park"

CREATE SCHEMA IF NOT EXISTS park AUTHORIZATION park;
GRANT ALL ON SCHEMA park TO park;

--DROP DOMAIN BOOLEAN;
--CREATE DOMAIN BOOLEAN AS VARCHAR(1) DEFAULT '0' CHECK (VALUE IN ('S','s', 'N','n','0','1'));


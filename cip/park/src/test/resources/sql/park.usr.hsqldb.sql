-- We assume the Database existence: "parkDB"
DROP USER park;
CREATE USER park PASSWORD 'park';

-- Role: "park"
DROP ROLE park;
CREATE ROLE park;

-- Schema: "park"
DROP SCHEMA park;
CREATE SCHEMA park AUTHORIZATION park;
CREATE SCHEMA ACCOUNTS AUTHORIZATION park_us
GRANT park TO park_user;

--DROP DOMAIN BOOLEAN;
--CREATE DOMAIN BOOLEAN AS VARCHAR(1) DEFAULT '0' CHECK (VALUE IN ('S','s', 'N','n','0','1'));

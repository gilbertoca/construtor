--We assume the Database existence: "parkDB"

--We can't drop the user role before database since the database belongs to the role.
--So ask the database admin to do it for you.
DROP ROLE park;
--Since 8.1 version postresql assumes users and groups as ROLE. All as clusterawre!
CREATE ROLE park LOGIN
  PASSWORD 'park'
  NOSUPERUSER NOINHERIT CREATEROLE;

-- Schema: "park"
DROP SCHEMA park;

CREATE SCHEMA park
  AUTHORIZATION park;
GRANT ALL ON SCHEMA park TO park;

--DROP DOMAIN BOOLEAN;
--CREATE DOMAIN BOOLEAN AS VARCHAR(1) DEFAULT '0' CHECK (VALUE IN ('S','s', 'N','n','0','1'));


USE FlashFeet_BreannaL;

DELIMITER $$
DROP PROCEDURE IF EXISTS AddJobSpec $$
CREATE PROCEDURE AddJobSpec()
BEGIN
  
ALTER TABLE JobRole
ADD COLUMN jobSpecification		varchar(70) 		NOT NULL
AFTER jobRole;

END $$
DELIMITER ;
CALL AddJobSpec();


USE FlashFeet_JohnMc;

DELIMITER $$
DROP PROCEDURE IF EXISTS AddCapability $$
CREATE PROCEDURE AddCapability()
BEGIN
  
ALTER TABLE JobRole
ADD COLUMN capability		varchar(70) 		NOT NULL
AFTER jobRole;

END $$
DELIMITER ;
CALL AddCapability();


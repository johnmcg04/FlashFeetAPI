USE FlashFeet_JohnMc;

DELIMITER $$
DROP PROCEDURE IF EXISTS AddResponsibilities $$
CREATE PROCEDURE AddResponsibilities()
BEGIN
  
ALTER TABLE JobRole
ADD COLUMN responsibilities	varchar(70)		 
AFTER bandlevel;

END $$
DELIMITER ;
CALL AddResponsibilities();

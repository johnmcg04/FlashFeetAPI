USE FlashFeet_JohnMc;

DELIMITER $$
DROP PROCEDURE IF EXISTS AddBandLevel $$
CREATE PROCEDURE AddBandLevel()
BEGIN
  
ALTER TABLE JobRole
ADD COLUMN bandLevel	varchar(70)		NOT NULL
AFTER capability;

END $$
DELIMITER ;
CALL AddBandLevel();

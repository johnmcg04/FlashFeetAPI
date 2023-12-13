CREATE DATABASE IF NOT EXISTS FlashFeet_JohnMc;
DELIMITER $$
DROP PROCEDURE IF EXISTS CreateDatabase $$
CREATE PROCEDURE CreateDatabase()
BEGIN
  START TRANSACTION;
USE FlashFeet_JohnMc;

CREATE TABLE IF NOT EXISTS JobRole(
	jobRole VARCHAR(70) PRIMARY KEY,
);

-- check the number of affected rows
  GET DIAGNOSTICS @rows = ROW_COUNT;
  IF @rows = 0 THEN
    -- if an error occurred,
    ROLLBACK;
    SELECT 'Transaction rolled back due to error.';
  ELSE
    -- If no error occurred, commit the Transaction
    COMMIT;
    SELECT 'Transaction committed successfully.';
END IF;
END $$
DELIMITER ;
CALL CreateDatabase();

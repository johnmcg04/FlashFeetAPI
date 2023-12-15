USE FlashFeet_JohnMc;

DELIMITER $$
DROP PROCEDURE IF EXISTS CreateCapabilityBandLevel $$
CREATE PROCEDURE CreateCapabilityBandLevel()
BEGIN

CREATE TABLE IF NOT EXISTS Capability ( 
capability			varchar(30)		NOT NULL, 
CONSTRAINT pk_jobfamily PRIMARY KEY (capability) 
); 
ALTER TABLE JobRole 
ADD CONSTRAINT fk_jobrole_capability FOREIGN KEY (capability) REFERENCES Capability(capability);

CREATE TABLE IF NOT EXISTS BandLevel( 
bandLevel			varchar(70)		NOT NULL, 
CONSTRAINT pk_bandLevel PRIMARY KEY (bandLevel) 
); 
ALTER TABLE JobRole
ADD CONSTRAINT fk_jobrole_blevel FOREIGN KEY (bandLevel) REFERENCES BandLevel(bandLevel);

ALTER TABLE JobRole
ADD COLUMN jobFamily	varchar(70)		NOT NULL
AFTER bandLevel;

ALTER TABLE JobRole
ADD COLUMN jobSpecSummary varchar(70) NOT NULL;

END $$
DELIMITER ;
CALL CreateCapabilityBandLevel();

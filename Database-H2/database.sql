/**
 * Grundstrukturierung des Datenbankmodells der H2-Datenbank
 *
 * @author Luca Ulrich - 9596
 * @version 1.1
 */
  
  CREATE TABLE `Mitglieder` (
	`MitgliedID` INT(11) NOT NULL AUTO_INCREMENT,
	`Vorname` VARCHAR(35) NOT NULL,
	`Nachname` VARCHAR(70) NOT NULL,
	`Strasse` VARCHAR(255) NOT NULL,
	`Hausnummer` INT(11) NOT NULL,
	`Postleitzahl` INT(11) NOT NULL,
	`Geburtstag` DATE NOT NULL,
	`Eintritt` DATE NOT NULL,
	`Kuendigung` DATE,
	`Austritt` DATE,
	`Mitgliedsart` VARCHAR(255) NOT NULL,
	`Kontoverbindung` INT(255) NOT NULL,
	`FamilienmitgliedID` INT(11),
	PRIMARY KEY (`MitgliedID`)
);

CREATE TABLE `Postleitzahlen` (
	`Postleitzahl` INT(11) NOT NULL,
	`Ort` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`Postleitzahl`)
);


CREATE TABLE `Mitgliedsarten` (
	`Bezeichnung` VARCHAR(255) NOT NULL,
	`Beitrag` DECIMAL(19,4) NOT NULL,
	PRIMARY KEY (`Bezeichnung`)
);

CREATE TABLE `Positionen` (
	`Rechnungsnummer` INT(11) NOT NULL AUTO_INCREMENT,
	`MitgliedID` INT(11) NOT NULL,
	`Zahlstatus` BOOLEAN NOT NULL DEFAULT false,
	`Betrag` DECIMAL(19,4) NOT NULL,
	`Jahr` DATE NOT NULL,
	PRIMARY KEY (`Rechnungsnummer`)
);

ALTER TABLE Mitglieder
ADD FOREIGN KEY (Postleitzahl) REFERENCES Postleitzahlen(Postleitzahl);

ALTER TABLE Mitglieder
ADD FOREIGN KEY (Mitgliedsart) REFERENCES Mitgliedsarten(Bezeichnung);

ALTER TABLE Positionen
ADD FOREIGN KEY (MitgliedID) REFERENCES Mitglieder(MitgliedID);

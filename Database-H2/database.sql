/**
 * Grundstrukturierung des Datenbankmodells der H2-Datenbank
 *
 * @author Luca Ulrich - 9596
 * @version 1.0
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
	`Mitgliedsart` VARCHAR(11) NOT NULL,
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
	`Beitrag` DECIMAL(19,4) unsigned NOT NULL,
	PRIMARY KEY (`Bezeichnung`)
);

CREATE TABLE `Positionen` (
	`Rechnungsnummer` INT(11) NOT NULL AUTO_INCREMENT,
	`Zahlstatus` BOOLEAN NOT NULL DEFAULT 0,
	`Betrag` DECIMAL(19,4) NOT NULL,
	`Jahr` DATE NOT NULL,
	PRIMARY KEY (`Rechnungsnummer`)
);

CREATE TABLE `MitgliederPositionen` (
	`MitgliedID` INT(11) NOT NULL,
	`Rechnungsnummer` INT(11) NOT NULL
);


ALTER TABLE Mitglieder
ADD FOREIGN KEY (Postleitzahl) REFERENCES Postleitzahlen(Postleitzahl);

ALTER TABLE Mitglieder
ADD FOREIGN KEY (Mitgliedsart) REFERENCES Mitgliedsarten(Bezeichnung);

ALTER TABLE MitgliederPositionen
ADD FOREIGN KEY (MitgliedID) REFERENCES Mitglieder(MitgliedID);

ALTER TABLE MitgliederPositionen
ADD FOREIGN KEY (Rechnungsnummer) REFERENCES Positionen(Rechnungsnummer);

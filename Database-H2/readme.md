# H2-Datenbank
Die Installation eines Datenbankservers wird durch das Nutzen der H2-Datenbankservers überflüssig. Sie speichert die Daten in dateibasierter Form und erleichtert somit die Datenhandhaltung in Gruppen, ohne die Verwendug eines zentralen Datenbankservers <br>

Informationen zur Datenbank und die jeweils aktuelle Version gibt es auf der Homepage:
http://www.h2database.com <br>

## Installation
Auf der Homepage des Anbieters kann eine Installationsdatei für eine H2-Datenbank heruntergeladen werden. Nach der Instalaltion befindet sich im Startmenü eine Möglichkeit die Webbasierte Konsole zu öffnen. Dieser Zugang ist limitiert auf einen Prozess! Alternativ kann IntelliJ die nötigen Treiber direkt herunterladen.


## Zugang
Der jdbc-Zugang erfolgt durch die URL:
```
jdbc:h2:XYZ
```
Der Platzhalter `XYZ` ist dabei durch den lokalen Pfad zur Datenbank-Datei zu ersetzen. Ein Beispielhafter Aufruf wäre also:
```
jdbc:h2:C:/iaa-i148-hausarbeit/Database-H2/db
```
Der Kennungsdaten des Nutzers lauten:
```
Benutzername: admin
Passwort: admin
```

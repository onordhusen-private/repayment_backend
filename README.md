# Verwendung

## Voraussetzungen
Folgende Programme in der angegebenen Version müssen installiert sein:
- Java 21

Sollte Java 21 bereits installiert sein, muss sichergestellt werden, dass es aktiviert ist.
Mit folgendem Befehl öffnet sich eine Liste aller installierten Java-Versionen, die außerdem aktiviert werden können:
```bash
sudo update-alternatives --config java
```

Kommt es trotz installierter und aktivierter Java 21 Version zu Java Version Fehlern, sollte geprüft werden, ob die Umgebungsvariable **JAVA_HOME** richtig gesetzt ist.

## Setup
In Spring ist idr. ein Maven-Wrapper initial dabei.

Mit diesem Wrapper lässt sich Maven ausführen, ohne es installiert haben zu müssen.

Um das Projekt zu initialisieren, muss folgender Befehl ausgeführt werden:
```bash
./mvnw install
```

Wenn das Programm ohne Fehler durchgelaufen ist, war das Setup erfolgreich.

## Start
Um das Programm zu staten, muss folgender Befehl ausgeführt werden:
```bash
./mvnw spring-boot:run
```

Mit **STRG+C** kann das Programm beendet werden.

Hier ist ein Beispiel *curl* zum Test:
```bash
curl 'localhost:8080/api/v1/repayment?loanAmount=250000&initialRepayment=2&interestRate=3.6&fixedInterestPeriod=10'
```
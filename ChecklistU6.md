# Übung 6
Erweitern Sie das Programm aus Übung 2 als Client-Server-Lösung. Der Client soll dabei die Oberfläche zur Bedienung realisieren und der Server die Geschäftslogik enthalten.

Die Berücksichtigung von Skalierbarkeit, Sicherheit und Transaktionskontrollen ist nicht gefordert.

Die Observer müssen nicht im Netzwerk funktionieren.

Clients und Servers haben jeweils eine eigene main-Methode (IntelliJ kann mehrere Applikationen parallel ausführen).

## Abgabeanforderungen
Die Abgabe hat als zip-Datei zu erfolgen, die ein lauffähiges IntelliJ-IDEA-Projekt enthält. Sie sollte die befüllte Checkliste im root des Projektes (neben der iml-Datei) enthalten in der der erreichte Stand bezüglich des Bewertungsschemas vermerkt ist.

Änderungen an der Checkliste sind grundsätzlich nicht zulässig. Davon ausgenommen ist das Befüllen der Checkboxen und ergänzende Anmerkungen die _kursiv gesetzt_ sind.
## Quellen
Zulässige Quellen sind suchmaschinen-indizierte Internetseiten. Werden mehr als drei zusammenhängende Anweisungen übernommen ist die Quelle in den Kommentaren anzugeben. Ausgeschlossen sind Quellen, die auch als Beleg oder Übungsaufgabe abgegeben werden oder wurden. Zulässig sind außerdem die über moodle bereitgestellten Materialien, diese können für die Übungsaufgaben und den Beleg ohne Quellenangabe verwendet werden.
## Bewertung
1 Punkt für die Erfüllung des Pflichtteils
### Pflichtteil
- [ ] Quellen angegeben
- [ ] zip Archiv
- [ ] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [ ] JUnit5 und Mockito als Testframeworks (soweit verwendet)
- [ ] keine weiteren Bibliotheken außer JavaFX
- [ ] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [ ] kompilierbar
- [ ] Trennung zwischen Test- und Produktiv-Code
- [ ] main-Methoden nur im default package
- [ ] ausführbar
- [ ] CRUD für einen Kuchentyp via TCP oder UDP
- [ ] saubere Trennung zwischen Oberfläche (Client) und Geschäftslogik (Server)
### empfohlene Realisierungen als Vorbereitung auf den Beleg
werden überprüft (aber nicht bewertet), wenn hier in der vorgegebenen Reihenfolge als bearbeitet angegeben
- [ ] je ein Stellvertreter-Test für Einfügen und Anzeigen pro Server
- [ ] Implementierung von Client und Server für TCP und UDP
- [ ] Unterstützung mehrerer konkurierender Clients pro Server (TCP oder UDP)
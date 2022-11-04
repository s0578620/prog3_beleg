# Übung 2
Erstellen Sie ein prototypisches CLI für den Beleg mit einem einfachen event-System welches Benutzeroberfläche und Geschäftslogik verbindet und außerdem einen Beobachter.

Eine prototypische Lösung würde 4 Befehle (und keine Modi) kennen: ein Befehl zum Anlegen eines Herstellers, ein Befehl zum Einfügen eines (vordefinierten) Kuchens, ein Befehl zum Anzeigen der Kuchen und ein Befehl zum Entfernen eines Kuchen.

Der Persistenzmodus sowie die Ausführung als Client bzw. Server sind nicht Teil der Übung.

## Abgabeanforderungen
Die Abgabe hat als zip-Datei zu erfolgen, die ein lauffähiges IntelliJ-IDEA-Projekt enthält. Sie sollte die befüllte Checkliste im root des Projektes (neben der iml-Datei) enthalten in der der erreichte Stand bezüglich des Bewertungsschemas vermerkt ist.
## Quellen
Zulässige Quellen sind suchmaschinen-indizierte Internetseiten. Werden mehr als drei zusammenhängende Anweisungen übernommen ist die Quelle in den Kommentaren anzugeben. Ausgeschlossen sind Quellen, die auch als Beleg oder Übungsaufgabe abgegeben werden oder wurden. Zulässig sind außerdem die über moodle bereitgestellten Materialien, diese können für die Übungsaufgaben und den Beleg ohne Quellenangabe verwendet werden.
## Bewertung
1 Punkt für die Erfüllung des Pflichtteils
### Pflichtteil
- [x] Quellen angegeben
- [x] zip Archiv
- [x] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [x] JUnit5 und Mockito als Testframeworks (soweit verwendet)
- [x] keine weiteren Bibliotheken außer JavaFX
- [x] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [x] kompilierbar
- [x] Trennung zwischen Test- und Produktiv-Code
- [x] main-Methoden nur im default package
- [x] ausführbar
- [x] Benutzeroberfläche und Geschäftslogik korrekt aufgeteilt
- [x] prototypisches CLI (nicht notwendig, wenn umfangreicheres CLI realisiert ist)
### empfohlene Realisierungen als Vorbereitung auf den Beleg
werden überprüft (aber nicht bewertet), wenn hier in der vorgegebenen Reihenfolge als bearbeitet angegeben
- [x] event-System für die Kommunikation vom CLI zur GL realisiert
- [x] Beobachter realisiert
- [ ] event-System für die Kommunikation von der GL zum CLI realisiert
- [ ] zwei Tests für Beobachter realisiert
- [ ] zwei Tests für listener realisiert
- [ ] angemessene Aufzählungstypen verwendet
- [ ] nach MVC strukturiert
- [ ] vollständiger Befehlssatz


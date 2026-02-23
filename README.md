# Like Hero To Zero

Prototypische Webanwendung zur Anzeige und Verwaltung nationaler CO₂-Emissionsdaten.

Die Anwendung wurde mit Spring Boot, Spring Data JPA, Spring Security und einer relationalen H2-Datenbank umgesetzt.

## Projekt starten

1. Projekt in einer IDE (z. B. IntelliJ IDEA Community Edition) öffnen
2. Klasse `LikeHeroToZeroApplication` starten
3. Browser öffnen: http://localhost:8080

## Demo-Logins

Scientist (Dateneingabe):
- Benutzername: scientist
- Passwort: secret
- Bereich: /scientist/dashboard

Publisher (Freigabe):
- Benutzername: publisher
- Passwort: secret
- Bereich: /publisher/pending

## Funktionalität

- Öffentliche Anzeige des aktuellsten freigegebenen CO₂-Emissionsdatensatzes
- Eingabe und Bearbeitung von Datensätzen durch Wissenschaftler:innen
- Freigabemechanismus durch Publisher-Rolle
- Persistente Speicherung in einer dateibasierten H2-Datenbank

## Technologien

- Spring Boot
- Spring Web (MVC)
- Spring Security
- Spring Data JPA (Hibernate)
- H2 Datenbank
- Thymeleaf


-- PRAGMA Anweisungen

PRAGMA encoding = UTF-8;
PRAGMA foreign_keys = ON;
PRAGMA auto_vacuum = 1;
PRAGMA automatic_index = ON;



-- CREATE Anweisungen

CREATE TABLE IF NOT EXISTS Nutzer
(
Benutzername text PRIMARY KEY,
EMail text NOT NULL UNIQUE,
Passwort    text NOT NULL
);
CREATE TABLE IF NOT EXISTS Premium_Nutzer
(
Benutzername text PRIMARY KEY,
FOREIGN KEY(Benutzername) REFERENCES Nutzer(Benutzername) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Schauspieler
(
Benutzername text PRIMARY KEY,
Vorname text NOT NULL,
Nachname text NOT NULL,
Künstlername text,
Geburtsdatum date NOT NULL,
Geburtsort text NOT NULL,
FOREIGN KEY(Benutzername) REFERENCES Premium_Nutzer(Benutzername) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Medienkonzern
(
Bezeichnung text PRIMARY KEY
);
CREATE TABLE IF NOT EXISTS Video
(
Bezeichnung text PRIMARY KEY,
Spieldauer INTEGER NOT NULL,
Erscheinungsjahr  varchar(4) NOT NULL,
Informationen text,
Medienkonzern_Bezeichnung text NOT NULL,
FOREIGN KEY(Medienkonzern_Bezeichnung) REFERENCES Medienkonzern(Bezeichnung)
);
CREATE TABLE IF NOT EXISTS Watchlist
(
ID integer PRIMARY KEY ASC AUTOINCREMENT,
Bezeichnung text NOT NULL,
Privatsphärestatus    varchar(10) NOT NULL CHECK(Privatsphärestatus = 'privat' OR Privatsphärestatus = 'öffentlich'),
Benutzername text NOT NULL,
FOREIGN KEY(Benutzername) REFERENCES Premium_Nutzer(Benutzername)
);
CREATE TABLE IF NOT EXISTS Film
(
Bezeichnung text PRIMARY KEY,
Produktionsbudget    integer NOT NULL,
FOREIGN KEY(Bezeichnung) REFERENCES Video(Bezeichnung)
);
CREATE TABLE IF NOT EXISTS Film_Kommentar
(
ID text PRIMARY KEY,
Kommentar text NOT NULL,
Benutzername text NOT NULL,
Bezeichnung text NOT NULL,
FOREIGN KEY(Benutzername) REFERENCES Premium_Nutzer(Benutzername),
FOREIGN KEY(Bezeichnung) REFERENCES Film(Bezeichnung)
);
CREATE TABLE IF NOT EXISTS Serie
(
ID integer PRIMARY KEY,
Serienname text NOT NULL
);
CREATE TABLE IF NOT EXISTS Staffel
(
ID integer PRIMARY KEY,
Staffelnummer integer NOT NULL,
SerieID integer NOT NULL,
FOREIGN KEY(SerieID) REFERENCES Serie(ID)
);
CREATE TABLE IF NOT EXISTS Folge
(
Bezeichnung text PRIMARY KEY,
StaffelId integer NOT NULL,
FOREIGN KEY(Bezeichnung) REFERENCES Video(Bezeichnung),
FOREIGN KEY(StaffelId) REFERENCES Staffel(ID)
);
CREATE TABLE IF NOT EXISTS Staffel_Kommentar
(
ID integer PRIMARY KEY,
Kommentar text NOT NULL,
Benutzername text NOT NULL,
StaffelId integer NOT NULL,
FOREIGN KEY(Benutzername) REFERENCES Premium_Nutzer(Benutzername),
FOREIGN KEY(StaffelId) REFERENCES Staffel(ID)
);
 
CREATE TABLE IF NOT EXISTS Trailer
(
Bezeichnung text PRIMARY KEY,
FilmBezeichnung text NOT NULL,
Qualität varchar(2) NOT NULL CHECK(Qualität = 'LQ' OR Qualität = 'HQ'),
FOREIGN KEY(Bezeichnung) REFERENCES Video(Bezeichnung),
FOREIGN KEY(FilmBezeichnung) REFERENCES Film(Bezeichnung)
);
CREATE TABLE IF NOT EXISTS Genre
(
Bezeichnung text PRIMARY KEY
);
 
CREATE TABLE IF NOT EXISTS Bewertet
(
Benutzername text,
Bezeichnung text,
Bewertung integer NOT NULL CHECK(Bewertung IN (1,2,3,4,5,6,7,8,9,10)),
PRIMARY KEY (Benutzername, Bezeichnung),
FOREIGN KEY(Bezeichnung) REFERENCES Film(Bezeichnung),
FOREIGN KEY(Benutzername) REFERENCES Nutzer(Benutzername)
);

CREATE TABLE IF NOT EXISTS Enthält
(
Bezeichnung text ,
WatchlistId integer,
PRIMARY KEY (Bezeichnung,WatchlistId),
FOREIGN KEY(Bezeichnung) REFERENCES Video(Bezeichnung),
FOREIGN KEY(WatchlistId) REFERENCES Watchlist(ID)
);
CREATE TABLE IF NOT EXISTS Wirkt_mit_Film
(
Bezeichnung text ,
Benutzername text,
PRIMARY KEY (Bezeichnung,Benutzername),
FOREIGN KEY(Bezeichnung) REFERENCES Film(Bezeichnung),
FOREIGN KEY(Benutzername) REFERENCES Schauspieler(Benutzername)
);
CREATE TABLE IF NOT EXISTS Wirkt_mit_Serie
(
SerieId integer ,
Benutzername text,
PRIMARY KEY (SerieId,Benutzername),
FOREIGN KEY(SerieId) REFERENCES Serie(ID),
FOREIGN KEY(Benutzername) REFERENCES Schauspieler(Benutzername)
);
CREATE TABLE IF NOT EXISTS Veröffentlicht
(
Medienkonzert_Bezeichnung text ,
Film_Bezeichnung text,
PRIMARY KEY (Medienkonzert_Bezeichnung,Film_Bezeichnung),
FOREIGN KEY(Medienkonzert_Bezeichnung) REFERENCES Medienkonzern(Bezeichnung),
FOREIGN KEY(Film_Bezeichnung) REFERENCES Film(Bezeichnung)
);
CREATE TABLE IF NOT EXISTS Gehört_an
(
Video_Bezeichnung text ,
Genre_Bezeichnung text,
PRIMARY KEY (Video_Bezeichnung,Genre_Bezeichnung),
FOREIGN KEY(Video_Bezeichnung) REFERENCES Video(Bezeichnung),
FOREIGN KEY(Genre_Bezeichnung) REFERENCES Genre(Bezeichnung)
);
CREATE TABLE IF NOT EXISTS Folgt
(
Benutzername text ,
Benutzername1 text,
PRIMARY KEY (Benutzername,Benutzername1),
FOREIGN KEY(Benutzername) REFERENCES Premium_Nutzer(Benutzername),
FOREIGN KEY(Benutzername1) REFERENCES Premium_Nutzer(Benutzername)
);
CREATE TABLE IF NOT EXISTS Steht_unter_Vertrag
(
Benutzername text ,
Bezeichnung text,
PRIMARY KEY (Benutzername,Bezeichnung),
FOREIGN KEY(Benutzername) REFERENCES Schauspieler(Benutzername),
FOREIGN KEY(Bezeichnung) REFERENCES Medienkonzern(Bezeichnung)
);
 

DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;


CREATE TABLE avdeling
(
	avdelingsID SERIAL,
	avdelingsnavn VARCHAR(30),
	sjefID INTEGER NOT NULL,
	
	CONSTRAINT Avdeling_PK PRIMARY KEY(avdelingsID)
);

CREATE TABLE Ansatt(

  ansattID SERIAL,
  brukernavn VARCHAR(30) UNIQUE,
  Fornavn VARCHAR(30),
  Etternavn VARCHAR(30),
  tilsettdato DATE,
  stilling VARCHAR(30),
  maanedslonn NUMERIC(10,2),
  avdelingsID INTEGER,
  
  CONSTRAINT Ansatt_PK PRIMARY KEY (ansattID),
  CONSTRAINT Avdeling_FK FOREIGN KEY (avdelingsID) REFERENCES avdeling(avdelingsID)
);

CREATE TABLE Prosjekt
(
  prosjektID SERIAL,
  prosjektnavn VARCHAR(30),
  prosjektbeskrivelse VARCHAR(30),
  
  CONSTRAINT Prosjekt_PK PRIMARY KEY (prosjektID)
);


CREATE TABLE Prosjektdeltakelse
(
  ProsjektdeltakelseID SERIAL,
  AnsattID INTEGER NOT NULL,
  ProsjektID INTEGER NOT NULL,
  arbeidstimer INTEGER,
  Rolle VARCHAR(30),
  
  CONSTRAINT Prosjektdeltakelse_PK PRIMARY KEY (ProsjektdeltakelseID),
  CONSTRAINT AnsattProsjekt_Unik UNIQUE (AnsattID, ProsjektID),
  CONSTRAINT Ansatt_FK FOREIGN KEY (AnsattID) REFERENCES Ansatt(AnsattID),
  CONSTRAINT Prosjekt_FK FOREIGN KEY (ProsjektID) REFERENCES Prosjekt(ProsjektID)  
);

INSERT INTO avdeling(avdelingsnavn, sjefID)
VALUES
	('Spillutvikling', 1),
	('Apputvikling', 2),
	('Programvare', 4);


INSERT INTO Ansatt(brukernavn, fornavn, etternavn, tilsettdato, stilling, maanedslonn, avdelingsID)
VALUES
  ('PePe', 'Per', 'Peresen', '2020-06-02', 'Data-ingeniør', 35000, 1),
  ('CaCa', 'Calle', 'Callesen', '2022-01-24', 'IT', 33000, 2),
  ('ArAr', 'Arve', 'Arvesen', '2020-11-11', 'APP-Utvikler', 30000, 1),
  ('JeJe', 'Jens', 'Jensen', '2020-09-30', 'Motivator', 20000, 3),
  ('StSt', 'Sture', 'Sturesen', '2021-05-14', 'Kjemiker', 31000, 3),
  ('PePo', 'Petter', 'Potter', '2020-12-24', 'Administrator', 36000, 3),
  ('SySy', 'Synne', 'Synnesen', '2020-05-09', 'Sekretær', 27000, 2),
  ('AnAn', 'Andrea', 'Andreasen', '2021-01-01', 'Fysioterapeut', 25000, 2),
  ('LeLe', 'Lene', 'Lenesen', '2020-07-24', 'Økonom', 29000, 1),
  ('StHu', 'Stine', 'Hurvik', '2020-04-19', 'Rengjører', 80000, 2);
  
  
ALTER TABLE avdeling 
ADD CONSTRAINT SjefID_FK FOREIGN KEY (sjefID) REFERENCES ansatt(ansattID);


INSERT INTO Prosjekt(prosjektnavn, prosjektbeskrivelse)
VALUES
  ('App-Utvikling', 'lage en ny populær app.'),
  ('Webdesigner', 'lage websider for kunder.'),
  ('Robotics', 'utvikle robotvirksomheten.'),
  ('AI', 'forbedre dagens AI.');
  


INSERT INTO Prosjektdeltakelse(AnsattID, ProsjektID, arbeidstimer, Rolle)
VALUES
  (1, 1, 101, 'Prosjektansvarlig'),
  (2, 1, 39, 'Koder'),
  (4, 3, 250, 'Prosjektansvarlig'),
  (3, 4, 98, 'Prosjektansvarlig'),
  (8, 3, 46, 'Tekniker'),
  (6, 2, 80, 'Prosjektansvarlig');

  

--- Important: Primary key can't be modified since we used GENERATED ALWAYS, i don't see why we would change a primary key, but in case if we want to, change ALWAYS with BY DEFAULT
-- A club can have many types of coachs, so we define a new domain

CREATE DOMAIN COACH_POST AS VARCHAR CHECK (
	VALUE IN  ('main', 'second', 'goalkeeping', 'physical', 'nutritionist', 'analyst', 'physiotherapist', 'psychologist')
);

CREATE DOMAIN ADMIN_OPERATION AS VARCHAR CHECK (
	VALUE IN ('add', 'delete', 'update')
);


CREATE TABLE admin (
	adminId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	nomAdmin VARCHAR (100) NOT NULL
); 

CREATE TABLE club (
	nomClub VARCHAR (100) NOT NULL,
	emailClub VARCHAR (254) PRIMARY KEY NOT NULL,
	passwordClub VARCHAR(256) NOT NULL,
	nomResponsable VARCHAR(100) NOT NULL,
	nbMaxAdherent INT DEFAULT 24
);

-- Can add constraints to naissanceInscrit (naissanceInscrit < CURRENT_DATE)
CREATE TABLE inscrit (
	emailInscrit VARCHAR (254) PRIMARY KEY NOT NULL,
	emailClub VARCHAR(254),
	nomInscrit VARCHAR (100) NOT NULL,
	telInscrit VARCHAR (10) NOT NULL UNIQUE,
	password VARCHAR (256) NOT NULL,
	naissanceInscrit DATE CHECK (
		naissanceInscrit < CURRENT_DATE - INTERVAL '6 years'
	),
	dateInscription  DATE DEFAULT CURRENT_DATE,
	CONSTRAINT fk_club
		FOREIGN KEY (emailClub)
			REFERENCES club (emailClub)
			ON DELETE CASCADE
			ON UPDATE CASCADE
);

CREATE TABLE coach (
	post COACH_POST NOT NULL
) INHERITS (inscrit);

CREATE TABLE gestion_admin_club (
	adminId INT REFERENCES admin (adminId),
	emailClub VARCHAR(254) REFERENCES club (emailclub) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (adminId, emailClub),
	dateGestion timestamp NOT NULL,
	operation ADMIN_OPERATION NOT NULL
);

-- Event table should be replaced with reservation + publication, because
CREATE TABLE evenements (
	evenementId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	titre VARCHAR (50) NOT NULL,
	description TEXT NOT NULL,
	dateDebut TIMESTAMP NOT NULL,
	dateFin TIMESTAMP NOT NULL CHECK (
		dateFin > dateDebut
	)
);

CREATE TABLE terrain (
	terrainId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	dispo BOOLEAN DEFAULT TRUE
);

CREATE TABLE creneau (
	dateCreneau DATE NOT NULL,
	heureCreneau TIME NOT NULL,
	PRIMARY KEY (dateCreneau, heureCreneau),
	duree TIME NOT NULL CHECK (
		duree > '00:00'
	)
);

-- Ternary relationship
CREATE TABLE reservation_terrain (
	terrainId INT REFERENCES terrain (terrainId),
	dateCreneau DATE NOT NULL,
	heureCreneau TIME NOT NULL,
	emailCLub VARCHAR(254) REFERENCES club (emailClub) ON DELETE CASCADE ON UPDATE CASCADE,
	emailInscrit VARCHAR(254) REFERENCES inscrit(emailInscrit),
	PRIMARY KEY (terrainId, dateCreneau, heureCreneau),
	FOREIGN KEY (dateCreneau, heureCreneau) REFERENCES creneau (dateCreneau, heureCreneau)
);

CREATE TABLE publication (
	publicationId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY
)

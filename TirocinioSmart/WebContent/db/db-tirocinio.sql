DROP DATABASE IF EXISTS dbtirocinio;
CREATE DATABASE dbtirocinio;
USE dbtirocinio;

DROP TABLE IF EXISTS convenzione;
CREATE TABLE IF NOT EXISTS convenzione(
	id					 int not null auto_increment,
	info				 varchar(25) not null,		
	azienda              varchar(25) not null,
	primary key (id)
);

DROP TABLE IF EXISTS tutor;
CREATE TABLE IF NOT EXISTS tutor(
	id					 int not null auto_increment,
	email				 varchar(25) not null,
	nome				 varchar(25) not null,
	pass				 varchar(50) not null,
	tipo				varchar(10) not null,	
	convenzioneID		int,
	foreign key (convenzioneID) references convenzione(id)
				ON DELETE CASCADE ON UPDATE CASCADE,	
	primary key (id, email)
);

DROP TABLE IF EXISTS registro;
CREATE TABLE IF NOT EXISTS registro(
	id					 int not null auto_increment,
	nome				 varchar(25) not null,
	descrizione		     varchar(50) not null,
	consegna             boolean not null,
	confermaTutorAcc     boolean not null,
	confermaTutorAz      boolean not null,
    primary key(id)
);

DROP TABLE IF EXISTS prog_form;
CREATE TABLE IF NOT EXISTS prog_form(
	id					 int not null auto_increment,
	info				 varchar(50) not null,
	approvazione		boolean not null,
    primary key(id)
);

DROP TABLE IF EXISTS studente;
CREATE TABLE IF NOT EXISTS studente(
	matricola			 varchar(20) not null,
	nome				 varchar(25) not null,	
	cfu 				 varchar(5) not null,
	pass				 varchar(50) not null,
	tutorAccID			int,				
	tutorAzID			int,
	tirocinio			int,
	registro			int, 
	
	foreign key (tutorAccID) references tutor(id)
				ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (tutorAzID) references tutor(id)
				ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (tirocinio) references prog_form(id)
				ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (registro) references registro(id)
				ON DELETE CASCADE ON UPDATE CASCADE,			
	primary key (matricola)
);

DROP TABLE IF EXISTS ufficio;
CREATE TABLE IF NOT EXISTS ufficio(
	id					 int not null auto_increment,
	email				 varchar(25) not null,
	nome				 varchar(25) not null,
	pass				 varchar(50) not null,
    primary key(id, email)
);








DROP TABLE IF EXISTS choose;
CREATE TABLE IF NOT EXISTS choose(
    id INT NOT NULL,
    description VARCHAR(250) DEFAULT 'unknown',
    tipo VARCHAR(50) NOT NULL DEFAULT 'select',
    PRIMARY KEY(id, description)
);

DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question(
    id INT NOT NULL,
    max_chooses INT NOT NULL,
    max_answers INT NOT NULL DEFAULT 1,
    question VARCHAR(250) NOT NULL,
    description VARCHAR(250) NOT NULL DEFAULT 'unknown',
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS questionario;
CREATE TABLE IF NOT EXISTS questionario(
    id INT NOT NULL,
    quests INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL DEFAULT 'unknown',
    tematica VARCHAR(100) DEFAULT 'Study',
    nutenti INT NOT NULL DEFAULT 0,
    PRIMARY KEY(id, nome)
);

DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer(
    quest INT NOT NULL,
    studente VARCHAR(20) NOT NULL,
    choose INT NOT NULL,
    a_date DATE NOT NULL,
    PRIMARY KEY(quest, studente, choose),
    FOREIGN KEY (quest) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (studente) REFERENCES studente(matricola) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (choose) REFERENCES choose(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS compila;
CREATE TABLE IF NOT EXISTS compila(
	studente VARCHAR(20) NOT NULL,
	questionario INT NOT NULL,
	data_inizio DATE NOT NULL,
	data_fine DATE NOT NULL,
	PRIMARY KEY(studente, questionario),
    FOREIGN KEY (studente) REFERENCES studente(matricola) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (questionario) REFERENCES questionario(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS include;
CREATE TABLE IF NOT EXISTS include(
	questionario INT NOT NULL,
	question INT NOT NULL,
	PRIMARY KEY(questionario, question),
    FOREIGN KEY (questionario) REFERENCES questionario(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS risponde;
CREATE TABLE IF NOT EXISTS risponde(
	quest INT NOT NULL,
	choose INT NOT NULL,
	PRIMARY KEY(quest, choose),
    FOREIGN KEY (quest) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (choose) REFERENCES choose(id) ON UPDATE CASCADE ON DELETE CASCADE
);

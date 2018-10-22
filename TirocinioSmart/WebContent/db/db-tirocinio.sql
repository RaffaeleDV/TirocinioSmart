DROP DATABASE IF EXISTS dbtirocinio;
CREATE DATABASE dbtirocinio;
USE dbtirocinio;

DROP TABLE IF EXISTS convenzione;
CREATE TABLE IF NOT EXISTS convenzione(
	id					 int not null auto_increment,
	info				 varchar(25) not null,		
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
	descrizione				varchar(50) not null,
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
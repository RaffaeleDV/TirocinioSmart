DROP DATABASE IF EXISTS dbtirocinio;
CREATE DATABASE dbtirocinio;
USE dbtirocinio;

DROP TABLE IF EXISTS convenzione;
CREATE TABLE IF NOT EXISTS convenzione(
	id					 varchar(20) not null auto_increment,
	info				 varchar(25) not null,		
	primary key (id)
);

DROP TABLE IF EXISTS tutor;
CREATE TABLE IF NOT EXISTS tutor(
	id					 varchar(20) not null auto_increment,
	nome				 varchar(25) not null,
	tipo				varchar(10) not null,	
	convenzioneID		varchar(25),
	foreign key (convenzioneID) references convenzione(id)
				ON DELETE CASCADE ON UPDATE CASCADE,	
	primary key (id)
);

DROP TABLE IF EXISTS registro;
CREATE TABLE IF NOT EXISTS registro(
	id					 varchar(20) not null auto_increment,
	nome				 varchar(25) not null,
	descrizione				varchar(50) not null
);

DROP TABLE IF EXISTS prog_form;
CREATE TABLE IF NOT EXISTS prog_form(
	id					 varchar(20) not null auto_increment,
	info				 varchar(50) not null,
	approvazione		boolean not null
);

DROP TABLE IF EXISTS studente;
CREATE TABLE IF NOT EXISTS studente(
	matricola					 varchar(20) not null,
	nome				 varchar(25) not null,	
	cfu 				 varchar(5) not null,	
	tutorAccID			varchar(20) not null,				
	tutorAzID			varchar(20) not null,
	tirocinio			varchar(20) not null,
	registro			varchar(20) not null,
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
DROP DATABASE IF EXISTS dbtirocinio;
CREATE DATABASE dbtirocinio;
USE dbtirocinio;

DROP TABLE IF EXISTS studente;
CREATE TABLE IF NOT EXISTS studente(
	matricola					 varchar(20) not null,
	nome				 varchar(25) not null,
	cognome 			 varchar(25) not null,
	Cfu 				 varchar(5) not null,	
	dataNascita 		 date not null,
	tutorAccID			varchar(25) not null,				
	tutorAzID			varchar(25) not null,
	tirocinio			varchar(25) not null,
	
	primary key (cf)
);
DROP DATABASE IF EXISTS dbtirocinio;
CREATE DATABASE dbtirocinio;

USE dbtirocinio;

DROP TABLE IF EXISTS tutor;
CREATE TABLE IF NOT EXISTS tutor(
    id                   INT NOT NULL auto_increment,
    email                VARCHAR(100) NOT NULL,
    nome                 VARCHAR(25) NOT NULL,
    pass                 VARCHAR(50) NOT NULL,
    tipo                 VARCHAR(10) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS convenzione;
CREATE TABLE IF NOT EXISTS convenzione(
    id                   INT NOT NULL auto_increment,
    info                 VARCHAR(25) NOT NULL,
    descrizione          VARCHAR(1024) NOT NULL,  -- descrizione con riferimenti a norme o regole
    tutorAzID            INT,                     -- id del tutor azendale che ha richiesto la stipula della convenzione nullo se e' stato un tutor accademico
    tutorAccID           INT,                     -- // // tutor accademico // // // // // // // // // // // Aziendale
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS registro;
CREATE TABLE IF NOT EXISTS registro(
    id                   INT NOT NULL auto_increment,
    nome                 VARCHAR(25) NOT NULL,
    descrizione          VARCHAR(50) NOT NULL,
    primaIstituzione     DATE NOT NULL,         -- la data di creazione del registro
    ultimoAgg            DATE NOT NULL,         -- la data dell' ultima modifica
    consegna             BOOLEAN NOT NULL,      -- dallo studente
    confermaTutorAcc     BOOLEAN NOT NULL,      -- dal tutor accademico
    confermaTutorAz      BOOLEAN NOT NULL,      -- dal tutor aziendale
    confermaUff          BOOLEAN NOT NULL,      -- dall' ufficio del dipartimento da cui è stato richiesto lo svolgimento delle attività del tirocinio
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS struttura_ospitante;
CREATE TABLE IF NOT EXISTS struttura_ospitante(
    id                   INT NOT NULL auto_increment,
    nome                 VARCHAR(50) NOT NULL,  -- nome dell' azienda o ente di cui se ne occupa
    ambitoLavorativo     VARCHAR(100) NOT NULL,
    nazione              VARCHAR(100) NOT NULL,
    regione              VARCHAR(100) NOT NULL,
    citta                VARCHAR(100) NOT NULL,
    via                  VARCHAR(100) NOT NULL,
    ncivico              INT,
        -- per rispettare la proporzione numero di tirocinanti incaricati ad un tutor aziendale 
        -- ed numero di dipendenti nella struttura ospitante
    ndipendenti          INT,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS attivita_tirocinio;
CREATE TABLE IF NOT EXISTS attivita_tirocinio(
    id                   INT NOT NULL auto_increment,
    registroID           INT,
    strutturaOspitanteID INT,
    descrizione          VARCHAR(255) NOT NULL,
    data                 DATE,
    ore                  INT NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS ufficio;
CREATE TABLE IF NOT EXISTS ufficio(
    id                   INT NOT NULL auto_increment,
    strutturaOspitanteID INT,                   -- chiave esterna della struttura che ospita l' ufficio
    email                VARCHAR(25) NOT NULL,
    nome                 VARCHAR(25) NOT NULL,
    pass                 VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS prog_form;
CREATE TABLE IF NOT EXISTS prog_form(
    id                   INT NOT NULL auto_increment,
    info                 VARCHAR(50) NOT NULL,
    formazione           VARCHAR(100) NOT NULL, -- la formazione ottenuta una volta terminato il periodo ed svolto come prestabilito le attività del registro di tirocinio ed le competenze eventualmente acquisite durante questo periodo 
    modalita             VARCHAR(100) NOT NULL,
    responsabile         VARCHAR(100) NOT NULL,
    inizioPeriodo        DATE NOT NULL,         -- la differenza tra inizio ed fine dettermina i mesi il cui valore massimo dipende dall' occupazione del tirocinante
    terminePeriodo       DATE NOT NULL,         -- la differenza non può superare i 24 mesi
    dataRilascio         DATE NOT NULL,
    approvazioneGenitori BOOLEAN,               -- l' approvazione data dai genitori nel caso in cui il tirocinante sia portatore di handicap
                                          -- null se lo studente non è portatore di handicap
    approvazioneRespo    BOOLEAN NOT NULL,      -- l' approvazione data dal responsabile
    approvazioneTutorAcc BOOLEAN NOT NULL,      -- l' approvazione data dal tutor accademico
    approvazioneTutorAz  BOOLEAN NOT NULL,      -- l' approvazione data dal tutor aziendale
    ufficioID            INT,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS rispetta;
CREATE TABLE IF NOT EXISTS rispetta(
    convenzioneID        INT NOT NULL,
    progettoFormativoID  INT NOT NULL,
    assicurazioneID      INT,                   -- gli ids degli estremi delle assicurazioni o polizze assicurative per la responsabilità civile ed INAIL
    PRIMARY KEY(convenzioneID, progettoFormativoID)
);

DROP TABLE IF EXISTS settore_operativo;
CREATE TABLE IF NOT EXISTS settore_operativo(
    id                  INT NOT NULL auto_increment,
    descrizione         VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS riguarda;
CREATE TABLE IF NOT EXISTS riguarda(
    progettoFormativoID  INT,
    settoreOperativoID   INT,
    priorita             INT NOT NULL,
    PRIMARY KEY(progettoFormativoID, settoreOperativoID)
);

DROP TABLE IF EXISTS percorso_formativo;
CREATE TABLE IF NOT EXISTS percorso_formativo(
    id                   INT NOT NULL auto_increment,
    progettoFormativoID  INT NOT NULL,
    percorso             VARCHAR(1024) NOT NULL, -- descrizione generale di come saranno svolte le attivita' del percorso formativo
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS obiettivo_tirocinio;
CREATE TABLE IF NOT EXISTS obiettivo_tirocinio(
    id                   INT NOT NULL auto_increment,
    progettoFormativoID  INT NOT NULL,
    obiettivo            VARCHAR(1024) NOT NULL, -- descrizione dell' obiettivo
    priorita             VARCHAR(10) NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS svolto_in;                 -- per lo svolgimento di attività prestabilite nel registro di tirocinio
CREATE TABLE IF NOT EXISTS svolto_in(
    progettoFormativoID  INT,
    strutturaOspitanteID INT,
    tutorAzID            INT,
    inizioPeriodo        DATE,
    terminePeriodo       DATE,
    PRIMARY KEY(progettoFormativoID, strutturaOspitanteID, tutorAzID)
);

DROP TABLE IF EXISTS studente;
CREATE TABLE IF NOT EXISTS studente(
    id                   INT NOT NULL auto_increment,
    matricola            VARCHAR(20) NOT NULL,  -- solo numeri
    nome                 VARCHAR(25) NOT NULL,  -- caratteri dell' alfabeto inglese
    cfu                  VARCHAR(5) NOT NULL,   -- solo numeri
    pass                 VARCHAR(50) NOT NULL,
  -- scuola secondaria, 
  -- lavoratore inoccupato, 
  -- disoccupato, 
  -- iscritto a liste di mobilità,
  -- istituto professionale di Stato, 
  -- di corso di formazione professionale,
  -- frequentante attivita'� formative post-diploma,
  -- frequentante attivita'� formative post-laurea,
  -- frequentano corsi di diploma universitario, 
  -- frequentano corsi di dottorato,
  -- frequentano corsi di ricerca, 
  -- frequentano corsi di perfezionamento,
  -- frequentano corsi di specializzazione,
  -- soggetto portatore di handicap
  -- etc...
  -- altro
  --
  -- in base alle normative l' occupazione può determinare il max numero di mesi consentito 
  -- per lo svolgimento delle attività da parte del tirocinante di un progetto formativo
    occupazione          VARCHAR(100) NOT NULL,
    tutorAccID           INT,       
    tutorAzID          INT,
    progettoFormativoID  INT,
    registroID           INT,
    PRIMARY KEY(id)
);








DROP TABLE IF EXISTS choose;
CREATE TABLE IF NOT EXISTS choose(
    id                   INT NOT NULL auto_increment,
    description          VARCHAR(1024) DEFAULT 'unknown',
    tipo                 VARCHAR(50) NOT NULL DEFAULT 'select',
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question(
    id                   INT NOT NULL auto_increment,
    maxChooses           INT,
    maxAnswers           INT DEFAULT 1,
    question             VARCHAR(1024) NOT NULL,
    description          VARCHAR(100) NOT NULL DEFAULT 'unknown',
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS questionario;
CREATE TABLE IF NOT EXISTS questionario(
    id                   INT NOT NULL auto_increment,
    questions            INT,
    nome                 VARCHAR(100) NOT NULL,
    description          VARCHAR(1024) NOT NULL DEFAULT 'unknown',
    tematica             VARCHAR(100) DEFAULT 'study',
    nstudenti            INT DEFAULT 0,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer(
    questionID           INT NOT NULL,
    utenteID             INT NOT NULL,
    tipoUtente           VARCHAR(20) NOT NULL,
    chooseID             INT NOT NULL,
    answerDate           DATE NOT NULL,
    PRIMARY KEY(questionID, utenteID, chooseID)
);

DROP TABLE IF EXISTS compila;
CREATE TABLE IF NOT EXISTS compila(
    utenteID             INT NOT NULL,
    questionarioID       INT NOT NULL,
    tipoUtente           VARCHAR(50) NOT NULL,     -- l' alternativa e' quella di appesantire l' operazione con delle inner join
    dataCompilazione     DATE,
    PRIMARY KEY(utenteID, questionarioID)
);

DROP TABLE IF EXISTS include;
CREATE TABLE IF NOT EXISTS include(
    questionarioID       INT,
    questionID           INT,
    PRIMARY KEY(questionarioID, questionID)
);

DROP TABLE IF EXISTS risponde;
CREATE TABLE IF NOT EXISTS risponde(
    questionID           INT,
    chooseID             INT,
    PRIMARY KEY(questionID, chooseID)
);









-- AGGIUNTA DI CHIAVI ESTERNE
    
ALTER TABLE convenzione ADD CONSTRAINT CONVENZIONETUTORAZFK
FOREIGN KEY(tutorAzID) REFERENCES tutor(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE convenzione ADD CONSTRAINT CONVENZIONETUTORACCFK
FOREIGN KEY(tutorAccID) REFERENCES tutor(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE attivita_tirocinio ADD CONSTRAINT ATTIVITATIROCINIOREGISTROFK
FOREIGN KEY(registroID) REFERENCES registro(id) 
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE attivita_tirocinio ADD CONSTRAINT ATTIVITATIROCINIOSTRUTTURAOSPITANTEFK
FOREIGN KEY(strutturaOspitanteID) REFERENCES struttura_ospitante(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ufficio ADD CONSTRAINT UFFICIOSTRUTTURAOSPITANTEFK
FOREIGN KEY(strutturaOspitanteID) REFERENCES struttura_ospitante(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE prog_form ADD CONSTRAINT PROGETTOFORMATIVOUFFICIOFK
FOREIGN KEY(ufficioID) REFERENCES ufficio(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rispetta ADD CONSTRAINT RISPETTACONVENZIONEFK
FOREIGN KEY(convenzioneID) REFERENCES convenzione(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE rispetta ADD CONSTRAINT RISPETTAPROGETTOFORMATIVOFK
FOREIGN KEY(progettoFormativoID) REFERENCES prog_form(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE riguarda ADD CONSTRAINT RIGUARDAPROGETTOFORMATIVOFK
FOREIGN KEY(progettoFormativoID) REFERENCES prog_form(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE riguarda ADD CONSTRAINT RIGUARDASETTOREOPERATIVOFK
FOREIGN KEY(settoreOperativoID) REFERENCES settore_operativo(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE percorso_formativo ADD CONSTRAINT PERCORSOFORMATIVOPROGETTOFORMATIVOFK
FOREIGN KEY(progettoFormativoID) REFERENCES prog_form(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE obiettivo_tirocinio ADD CONSTRAINT OBIETTIVOTIROCINIOPROGETTOFORMATIVOFK
FOREIGN KEY(progettoFormativoID) REFERENCES prog_form(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE svolto_in ADD CONSTRAINT SVOLTOINPROGETTOFORMATIVOFK
FOREIGN KEY(progettoFormativoID) REFERENCES prog_form(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE svolto_in ADD CONSTRAINT SVOLTOINSTRUTTURAOSPITANTEFK
FOREIGN KEY(strutturaOspitanteID) REFERENCES struttura_ospitante(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE svolto_in ADD CONSTRAINT SVOLTOINTUTORAZFK
FOREIGN KEY(tutorAzID) REFERENCES tutor(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE studente ADD CONSTRAINT STUDENTETUTORACCFK
FOREIGN KEY(tutorAccID) REFERENCES tutor(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE studente ADD CONSTRAINT STUDENTETUTORAZFK
FOREIGN KEY(tutorAzID) REFERENCES tutor(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE studente ADD CONSTRAINT STUDENTEPROGETTOFORMATIVOFK
FOREIGN KEY(progettoFormativoID) REFERENCES prog_form(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE studente ADD CONSTRAINT STUDENTEREGISTROFK
FOREIGN KEY(registroID) REFERENCES registro(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE answer ADD CONSTRAINT ANSWERQUESTIONFK
FOREIGN KEY(questionID) REFERENCES question(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE answer ADD CONSTRAINT ANSWERCHOOSEFK
FOREIGN KEY(chooseID) REFERENCES choose(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE compila ADD CONSTRAINT COMPILAQUESTIONARIOFK
FOREIGN KEY(questionarioID) REFERENCES questionario(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE include ADD CONSTRAINT INCLUDEQUESTIONARIOFK
FOREIGN KEY(questionarioID) REFERENCES questionario(id)
  ON DELETE CASCADE ON UPDATE CASCADE;
    
ALTER TABLE include ADD CONSTRAINT INCLUDEQUESTIONFK
FOREIGN KEY(questionID) REFERENCES question(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE risponde ADD CONSTRAINT RISPONDEQUESTIONFK
FOREIGN KEY(questionID) REFERENCES question(id)
  ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE risponde ADD CONSTRAINT RISPONDECHOOSEFK
FOREIGN KEY(chooseID) REFERENCES choose(id)
  ON DELETE CASCADE ON UPDATE CASCADE;










-- POPOLAZIONE DEL DATABASE

INSERT INTO tutor VALUES (1, 'tutor1@email.it', 'Rossi', 'rossi', 'Accademico');
INSERT INTO tutor VALUES (2, 'tutor2@email.it', 'Verdi', 'verdi', 'Aziendale');
INSERT INTO tutor VALUES (3, 'tutor3@email.it', 'Marco', 'marco', 'Accademico');
INSERT INTO tutor VALUES (4, 'tutor4@email.it', 'Giuseppe', 'giuseppe', 'Aziendale');
INSERT INTO tutor VALUES (5, 'tutor5@email.it', 'Daniele', 'daniele', 'Aziendale');
INSERT INTO tutor VALUES (6, 'tutor6@email.it', 'Domenico', 'domenico', 'Accademico');

INSERT INTO convenzione VALUES (1, 'InfoConvenzione', 'Descrizione Convenzione', 2, 6);
INSERT INTO convenzione VALUES (2, 'InfoConvenzione', 'Descrizione Convenzione', 2, 3);
INSERT INTO convenzione VALUES (3, 'InfoConvenzione', 'Descrizione Convenzione', 4, 3);
INSERT INTO convenzione VALUES (4, 'InfoConvenzione', 'Descrizione Convenzione', 5, 1);
INSERT INTO convenzione VALUES (5, 'InfoConvenzione', 'Descrizione Convenzione', 4, 6);
INSERT INTO convenzione VALUES (6, 'InfoConvenzione', 'Desrcizione Convenzione', 5, 6);

INSERT INTO registro VALUES (1, 'Registro1', 'DescrizioneRegistro', '2005-10-10', '2005-11-11', false, false, false, false);
INSERT INTO registro VALUES (2, 'Registro2', 'DescrizioneRegistro', '2010-5-5', '2010-10-5', true, false, true, false);
INSERT INTO registro VALUES (3, 'Registro3', 'DescrizioneRegistro', '2010-10-10', '2010-12-12', false, true, true, true);
INSERT INTO registro VALUES (4, 'Registro4', 'DescrizioneRegistro', '2015-7-5', '2015-7-25', true, true, true, false);
INSERT INTO registro VALUES (5, 'Registro5', 'DescrizioneRegistro', '2015-8-14', '2015-10-14', false, true, true, true);
INSERT INTO registro VALUES (6, 'Registro6', 'DescrizioneRegistro', '2016-8-22', '2016-9-21', true, true, true, true);

INSERT INTO struttura_ospitante VALUES (1, 'M4t3R0', 'Business IT(Music)', 'Belgio', 'Fiandre', 'Bruges', 'Spastraat', 1, 400);
INSERT INTO struttura_ospitante VALUES (2, 'PU17123', 'Business E-commerce', 'Germania', 'Francoforte', 'Kelsterbach', 'Moselstrabe', 18, 157);
INSERT INTO struttura_ospitante VALUES (3, 'M4ter4', 'Ricerca(Museo)', 'Alaska', 'Artic', 'Atqasuk', 'Keerik St.', 818, 34);
INSERT INTO struttura_ospitante VALUES (4, 'K4ve', 'Business Ricerca Archeologica', 'Madagascar', 'Menabe', 'Morondava', 'Rue Principal', null, 431);
INSERT INTO struttura_ospitante VALUES (5, 'B3ta', 'Business Gestione', 'Francia', 'Bretagna', 'Rennes', 'Rue D Antrain', 15, 133);
INSERT INTO struttura_ospitante VALUES (6, 'b3k', 'Consulenza', 'Croazia', 'Karlovac', 'Banija', 'UI. Ante Starcevica', 22, 14);
INSERT INTO struttura_ospitante VALUES (7, 'l4wer', 'Consulenza', 'Croazia', 'Karlovac', 'Svarca', 'Radnicka ul.', 14, 149);
INSERT INTO struttura_ospitante VALUES (8, 'tr4vis', 'Ricerca(Museo)', 'Croazia', 'Karlovac', 'Svarca', 'Trigvlaska ul.', null, 11);



INSERT INTO attivita_tirocinio VALUES (1, 1, 1, 'Anagrafe clienti', '2019-3-13', 3);
INSERT INTO attivita_tirocinio VALUES (2, 1, 1, 'Affidamento clienti', '2019-3-14', 3);
INSERT INTO attivita_tirocinio VALUES (3, 1, 1, 'Rating clienti', '2019-3-15', 1);
INSERT INTO attivita_tirocinio VALUES (4, 2, 2, 'Informazioni Commerciali', '2019-3-21', 2);
INSERT INTO attivita_tirocinio VALUES (5, 2, 2, 'Fatturazioni clienti', '2019-3-22', 3);
INSERT INTO attivita_tirocinio VALUES (6, 2, 2, 'Export prodotti', '2019-3-23', 1);
INSERT INTO attivita_tirocinio VALUES (7, 3, 3, 'Gestione del credito', '2019-3-27', 3);
INSERT INTO attivita_tirocinio VALUES (8, 3, 3, 'Gestione scadenziario', '2019-3-28', 3);
INSERT INTO attivita_tirocinio VALUES (9, 3, 3, 'Incassi', '2019-3-29', 2);
INSERT INTO attivita_tirocinio VALUES (10, 4, 4, 'Bonifici', '2019-4-3', 1);
INSERT INTO attivita_tirocinio VALUES (11, 4, 4, 'Versamento Assegni', '2019-4-4', 3);
INSERT INTO attivita_tirocinio VALUES (12, 4, 4, 'Prima Nota Contabile', '2019-4-5', 2);
INSERT INTO attivita_tirocinio VALUES (13, 5, 5, 'Recupero Crediti', '2019-4-6', 3);
INSERT INTO attivita_tirocinio VALUES (14, 5, 5, 'Anagrafica fornitori', '2019-5-14', 1);
INSERT INTO attivita_tirocinio VALUES (15, 5, 5, 'Controllo fatture di fornitore materiale codificato', '2019-5-15', 1);
INSERT INTO attivita_tirocinio VALUES (16, 6, 6, 'Controllo fatture di fornitore materiale non codificato', '2019-5-23', 3);
INSERT INTO attivita_tirocinio VALUES (17, 6, 6, 'Desposizione di pagamenti', '2019-5-24', 3);
INSERT INTO attivita_tirocinio VALUES (18, 6, 6, 'Affidamento clienti', '2019-5-25', 3);



INSERT INTO ufficio VALUES (30, 1, 'ufficio1@email.it', 'Verdi', 'verdi');
INSERT INTO ufficio VALUES (31, 2, 'ufficio2@email.it', 'Giacomo', 'giacomo');
INSERT INTO ufficio VALUES (32, 3, 'ufficio3@email.it', 'Paolo', 'paolo');
INSERT INTO ufficio VALUES (33, 4, 'ufficio4@email.it', 'Rossi', 'rossi');
INSERT INTO ufficio VALUES (34, 5, 'ufficio5@email.it', 'Antonio', 'antonio');
INSERT INTO ufficio VALUES (35, 6, 'ufficio6@email.it', 'Michele', 'michele');
INSERT INTO ufficio VALUES (36, 7, 'ufficio7@email.it', 'Riccardo', 'riccardo');
INSERT INTO ufficio VALUES (37, 8, 'ufficio8@email.it', 'Pasquale', 'pasquale');



INSERT INTO prog_form VALUES (1, 'InfoProgettoFormativo', 'Disegno(Grafica)', 'Frequentazione Corsi', 'Marco De Nardo', '2017-10-25', '2017-11-14', '2017-5-5', true, true, true, true, 30);
INSERT INTO prog_form VALUES (2, 'InfoProgettoFormativo', 'Analisi(Matematica)', 'Attivita In Laboratorio', 'Giovanni Grimaldi', '2018-4-10', '2018-6-6', '2018-3-3', true, false, true, true, 31);
INSERT INTO prog_form VALUES (3, 'InfoProgettoFormativo', 'Analisi(Chimica)', 'Attivita In Laboratorio', 'Roberto Della Valle', '2019-3-4', '2019-5-1', '2019-1-1', false, true, true, false, 32);
INSERT INTO prog_form VALUES (4, 'InfoProgettoFormativo', 'Analisi(Fisica)', 'Frequentazione Seminari', 'Paolo Capuano', '2019-7-5', '2019-10-5', '2019-3-3', true, false, false, false, 30);
INSERT INTO prog_form VALUES (5, 'InfoProgettoFormativo', 'Analisi(Sicurezza)', 'Attivita In Laboratorio', 'Paola Sessa', '2019-4-10', '2019-8-10', '2019-1-1', false, true, false, true, 31);
INSERT INTO prog_form VALUES (6, 'InfoProgettoFormativo', 'Disegno(Grafica)', 'Attivita In Laboratorio', 'Gennaro Abate', '2019-6-16', '2019-11-5', '2019-1-4', false, true, false, false, 32);

INSERT INTO rispetta VALUES (1, 1, 1000);
INSERT INTO rispetta VALUES (2, 2, 1001);
INSERT INTO rispetta VALUES (3, 3, 1002);
INSERT INTO rispetta VALUES (4, 4, 1003);
INSERT INTO rispetta VALUES (5, 5, 1004);
INSERT INTO rispetta VALUES (6, 6, 1005);

INSERT INTO settore_operativo VALUES (1, 'Descrizione Settore Operativo In IT(Music): ...');
INSERT INTO settore_operativo VALUES (2, 'Descrizione Settore Operativo In E-commerce: ...');
INSERT INTO settore_operativo VALUES (3, 'Descrizione Settore Operativo In Ricerca Nel Museo: ...');
INSERT INTO settore_operativo VALUES (4, 'Descrizione Settore Operativo Ricerca Archeologica: ...');
INSERT INTO settore_operativo VALUES (5, 'Descrizione Settore Operativo In Gestione: ...');
INSERT INTO settore_operativo VALUES (6, 'Descrizione Settore Operativo Consulenza: ...');
INSERT INTO settore_operativo VALUES (7, 'Descrizione Settore Operativo In Consulenza: ...');

INSERT INTO riguarda VALUES (1, 1, 1);
INSERT INTO riguarda VALUES (2, 2, 2);
INSERT INTO riguarda VALUES (3, 3, 3);
INSERT INTO riguarda VALUES (4, 4, 4);
INSERT INTO riguarda VALUES (5, 5, 5);
INSERT INTO riguarda VALUES (6, 6, 6);
INSERT INTO riguarda VALUES (7, 6, 19);

INSERT INTO percorso_formativo VALUES (1, 1, 'Descrizione Progetto Formativo: ...');
INSERT INTO percorso_formativo VALUES (2, 2, 'Descrizione Progetto Formativo: ...');
INSERT INTO percorso_formativo VALUES (3, 3, 'Descrizione Progetto Formativo: ...');
INSERT INTO percorso_formativo VALUES (4, 4, 'Descrizione Progetto Formativo: ...');
INSERT INTO percorso_formativo VALUES (5, 5, 'Descrizione Progetto Formativo: ...');
INSERT INTO percorso_formativo VALUES (6, 6, 'Descrizione Progetto Formativo: ...');

INSERT INTO obiettivo_tirocinio VALUES (1, 1, 'Descrizione Obiettivo Progetto Formativo: ...', 'alta');
INSERT INTO obiettivo_tirocinio VALUES (2, 2, 'Descrizione Obiettivo Progetto Formativo: ...', 'bassa');
INSERT INTO obiettivo_tirocinio VALUES (3, 3, 'Descrizione Obiettivo Progetto Formativo: ...', 'media');
INSERT INTO obiettivo_tirocinio VALUES (4, 4, 'Descrizione Obiettivo Progetto Formativo: ...', '10');
INSERT INTO obiettivo_tirocinio VALUES (5, 5, 'Descrizione Obiettivo Progetto Formativo: ...', '11');
INSERT INTO obiettivo_tirocinio VALUES (6, 6, 'Descrizione Obiettivo Progetto Formativo: ...', '12');
INSERT INTO obiettivo_tirocinio VALUES (7, 7, 'Descrizione Obiettivo Progetto Formativo: ...', '13');

INSERT INTO svolto_in VALUES (1, 1, 2, '2019-3-3', '2019-4-4');
INSERT INTO svolto_in VALUES (2, 2, 4, '2019-1-1', '2019-2-2');
INSERT INTO svolto_in VALUES (3, 3, 5, '2019-1-1', '2019-1-10');
INSERT INTO svolto_in VALUES (4, 4, 2, '2019-2-1', '2019-2-10');
INSERT INTO svolto_in VALUES (5, 5, 4, '2019-2-2', '2019-2-21');
INSERT INTO svolto_in VALUES (6, 6, 5, '2019-3-1', '2019-3-10');

INSERT INTO studente VALUES (1, '0512103600', 'Marco Romano', '55', 'marco', 'Scuola Secondaria', 6, 2, 3, 3);
INSERT INTO studente VALUES (2, '0512103603', 'Ferchichi Fakher', '130', 'fakher', 'Dottorato', 1, 2, 1, 1);
INSERT INTO studente VALUES (3, '0512103606', 'Vincenzo Romano',  '49', 'vincenzo', 'Ricerca', 6, 5, 4, 4);
INSERT INTO studente VALUES (4, '0512103607', 'Daniele Rossi', '79', 'daniele', 'Iscritto a liste mobilita', 3, 4, 2, 2);
INSERT INTO studente VALUES (5, '0512103609', 'Vincenzo Sessa', '51', 'vincenzo', 'Specializzazione', 1, 5, 5, 5);
INSERT INTO studente VALUES (6, '0512103619', 'Matteo Rinaldi', '151', 'matteo', 'Portatore Di Handicap', 1, 2, 6, 6);



INSERT INTO choose VALUES (10, 'C programming language', 'select');
INSERT INTO choose VALUES (11, 'Java programming language', 'select');
INSERT INTO choose VALUES (12, 'Ruby programming language', 'select');
INSERT INTO choose VALUES (13, 'C++ programming language', 'select');
INSERT INTO choose VALUES (14, 'Python programming language', 'select');
INSERT INTO choose VALUES (15, 'Ada programming language', 'select');
INSERT INTO choose VALUES (16, 'COBOL programming language', 'select');
INSERT INTO choose VALUES (17, 'C# programming language', 'select');
INSERT INTO choose VALUES (18, 'Perl programming language', 'select');
INSERT INTO choose VALUES (19, 'HTML', 'select');
INSERT INTO choose VALUES (20, 'JavaScript programming language', 'select');
INSERT INTO choose VALUES (21, 'Bash Shell', 'select');
INSERT INTO choose VALUES (22, '1', 'select');
INSERT INTO choose VALUES (23, '2', 'select');
INSERT INTO choose VALUES (24, '3', 'select');
INSERT INTO choose VALUES (25, '4', 'select');
INSERT INTO choose VALUES (26, '5', 'select');



INSERT INTO question VALUES (500, 5, 1, 'Which is your favourite programming language?', 'Preferences');
INSERT INTO question VALUES (501, 5, 1, 'Which are the most used programming language from your point of view?', 'Point Of View');
INSERT INTO question VALUES (502, 5, 1, 'How much do you use the C programming language(from 1 to 5) ?', 'Ratio');
INSERT INTO question VALUES (503, 5, 1, 'How much do you use the Java programming language(from 1 to 5) ?', 'Ratio');
INSERT INTO question VALUES (504, 5, 1, 'How much time do you spend in programming web sites(from 1 to 5) ?', 'Ratio');



INSERT INTO questionario VALUES (800, 2, 'Questionario-134', 'How much ...', 'Programming Languages', 2);
INSERT INTO questionario VALUES (801, 1, 'Questionario-135', 'How much ...', 'Programming Languages', 1);
INSERT INTO questionario VALUES (802, 1, 'Questionario-136', 'How much ...', 'Programming Languages', 2);
INSERT INTO questionario VALUES (803, 1, 'Questionario-137', 'How much ...', 'Programming Languages', 1);
INSERT INTO questionario VALUES (804, 1, 'Questionario-138', 'How much ...', 'Programming Languages', 2);



INSERT INTO answer VALUES (500, 1, 'S', 10, '2015-4-4');
INSERT INTO answer VALUES (500, 2, 'S', 11, '2015-4-4');
INSERT INTO answer VALUES (500, 3, 'S', 22, '2015-4-4');
INSERT INTO answer VALUES (501, 4, 'S', 11, '2015-4-4');
INSERT INTO answer VALUES (502, 5, 'S', 22, '2015-4-4');
INSERT INTO answer VALUES (503, 1, 'S', 23, '2015-4-4');
INSERT INTO answer VALUES (504, 2, 'S', 23, '2015-4-4');



INSERT INTO compila VALUES (1, 800, 'S', '2015-4-4');
INSERT INTO compila VALUES (2, 801, 'S', '2016-5-5');
INSERT INTO compila VALUES (3, 800, 'S', '2017-7-7');
INSERT INTO compila VALUES (4, 802, 'S', '2015-4-5');
INSERT INTO compila VALUES (5, 803, 'S', '2015-4-6');
INSERT INTO compila VALUES (6, 802, 'S', '2015-4-7');
INSERT INTO compila VALUES (1, 804, 'S', '2015-4-8');
INSERT INTO compila VALUES (2, 804, 'S', '2015-4-9');



INSERT INTO include VALUES (800, 500);
INSERT INTO include VALUES (800, 501);
INSERT INTO include VALUES (801, 502);
INSERT INTO include VALUES (802, 502);
INSERT INTO include VALUES (803, 503);
INSERT INTO include VALUES (804, 504);



INSERT INTO risponde VALUES (500, 10);
INSERT INTO risponde VALUES (500, 11);
INSERT INTO risponde VALUES (500, 12);
INSERT INTO risponde VALUES (500, 13);
INSERT INTO risponde VALUES (500, 14);
INSERT INTO risponde VALUES (501, 10);
INSERT INTO risponde VALUES (501, 12);
INSERT INTO risponde VALUES (501, 13);
INSERT INTO risponde VALUES (502, 22);
INSERT INTO risponde VALUES (502, 23);
INSERT INTO risponde VALUES (502, 24);
INSERT INTO risponde VALUES (502, 25);
INSERT INTO risponde VALUES (502, 26);
INSERT INTO risponde VALUES (503, 22);
INSERT INTO risponde VALUES (503, 23);
INSERT INTO risponde VALUES (503, 24);
INSERT INTO risponde VALUES (503, 25);
INSERT INTO risponde VALUES (503, 26);
INSERT INTO risponde VALUES (504, 22);
INSERT INTO risponde VALUES (504, 23);
INSERT INTO risponde VALUES (504, 24);
INSERT INTO risponde VALUES (504, 25);
INSERT INTO risponde VALUES (504, 26);

 
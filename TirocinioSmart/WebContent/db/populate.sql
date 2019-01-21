
INSERT INTO convenzione VALUES (1, 'Info', 'A');
INSERT INTO convenzione VALUES (2, 'Info', 'B');
INSERT INTO convenzione VALUES (3, 'Info', 'C');
INSERT INTO convenzione VALUES (4, 'Info', 'D');
INSERT INTO convenzione VALUES (5, 'Info', 'E');
INSERT INTO convenzione VALUES (6, 'Info', 'F');

INSERT INTO tutor VALUES (1, 'tutor1@email.it', 'Rossi', 'rossi', 'Accademico', 1);
INSERT INTO tutor VALUES (2, 'tutor2@email.it', 'Verdi', 'verdi', 'Aziendale', 2);
INSERT INTO tutor VALUES (3, 'tutor3@email.it', 'Marco', 'marco', 'Accademico', 3);
INSERT INTO tutor VALUES (4, 'tutor4@email.it', 'Giuseppe', 'giuseppe', 'Aziendale', 4);
INSERT INTO tutor VALUES (5, 'tutor5@email.it', 'Daniele', 'daniele', 'Aziendale', 5);
INSERT INTO tutor VALUES (6, 'tutor6@email.it', 'Domenico', 'domenico', 'Accademico', 6);

INSERT INTO registro VALUES (1, 'Registro1', 'Descrizione', false, false, false);
INSERT INTO registro VALUES (2, 'Registro2', 'Descrizione', false, false, false);
INSERT INTO registro VALUES (3, 'Registro3', 'Descrizione', false, false, false);
INSERT INTO registro VALUES (4, 'Registro4', 'Descrizione', false, false, false);
INSERT INTO registro VALUES (5, 'Registro5', 'Descrizione', false, false, false);
INSERT INTO registro VALUES (6, 'Registro6', 'Descrizione', false, false, false);

INSERT INTO prog_form VALUES (1, 'Info', true);
INSERT INTO prog_form VALUES (2, 'Info', true);
INSERT INTO prog_form VALUES (3, 'Info', true);
INSERT INTO prog_form VALUES (4, 'Info', false);
INSERT INTO prog_form VALUES (5, 'Info', false);
INSERT INTO prog_form VALUES (6, 'Info', false);

INSERT INTO studente VALUES ('0512103600', 'Marco Romano', '55', 'marco', 6, 2, 3, 3);
INSERT INTO studente VALUES ('0512103603', 'Ferchichi Fakher', '130', 'fakher', 1, 2, 1, 1);
INSERT INTO studente VALUES ('0512103606', 'Vincenzo Romano',  '49', 'vincenzo', 6, 5, 4, 4);
INSERT INTO studente VALUES ('0512103607', 'Daniele Rossi', '79', 'daniele', 3, 4, 2, 2);
INSERT INTO studente VALUES ('0512103609', 'Vincenzo Sessa', '51', 'vincenzo', 1, 5, 5, 5);
INSERT INTO studente VALUES ('0512103619', 'Matteo Rinaldi', '151', 'matteo', 1, 2, 6, 6);

INSERT INTO ufficio VALUES (30, 'ufficio1@email.it', 'Verdi', 'verdi');
INSERT INTO ufficio VALUES (31, 'ufficio2@email.it', 'Giacomo', 'giacomo');
INSERT INTO ufficio VALUES (32, 'ufficio3@email.it', 'Paolo', 'paolo');







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

INSERT INTO answer VALUES (500, '0512103600', 10, '2015-4-4');
INSERT INTO answer VALUES (500, '0512103603', 11, '2015-4-4');
INSERT INTO answer VALUES (500, '0512103606', 22, '2015-4-4');
INSERT INTO answer VALUES (501, '0512103607', 11, '2015-4-4');
INSERT INTO answer VALUES (502, '0512103619', 22, '2015-4-4');
INSERT INTO answer VALUES (503, '0512103603', 23, '2015-4-4');
INSERT INTO answer VALUES (504, '0512103606', 23, '2015-4-4');

INSERT INTO compila VALUES ('0512103600', 800, '2015-4-4', '2015-4-4');
INSERT INTO compila VALUES ('0512103600', 801, '2016-5-5', '2016-5-5');
INSERT INTO compila VALUES ('0512103603', 800, '2017-7-7', '2017-7-7');
INSERT INTO compila VALUES ('0512103603', 802, '2015-4-5', '2015-4-5');
INSERT INTO compila VALUES ('0512103606', 803, '2015-4-6', '2015-4-6');
INSERT INTO compila VALUES ('0512103606', 802, '2015-4-7', '2015-4-7');
INSERT INTO compila VALUES ('0512103607', 804, '2015-4-8', '2015-4-8');
INSERT INTO compila VALUES ('0512103619', 804, '2015-4-9', '2015-4-9');

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

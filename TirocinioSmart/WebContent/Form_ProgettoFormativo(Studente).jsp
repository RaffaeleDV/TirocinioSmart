<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Progetto Formativo</title>
<link rel = "stylesheet" href = "stile.css">
</head>
<body>
<p id = "introduzione"> Compila il progetto formativo riempendo tutti i campi del form
ed invia la richiesta di tirocinio. </p>
<div class = "wrap">
	<h2>Compila tutti i campi</h2>
		<input type = "text" name = "nome" placeholder = "Nome">  <input type = "text" name = "cognome" placeholder = "Cognome">
		<input type = "number" name = "matricola" placeholder = "Matricola">
		<input type = "text" name = "CF" placeholder = "Codice fiscale">
		<input id ="luogo" type = "text" name = "luogo di nascita" placeholder = "Luogo di nascita"> <br>
		Residente in via: <input type = "text" name = "via" > <br>
		<span> Data di nascita :</span> <br>
			<select name = "mese">
				<option> Gennaio </option>
				<option> Febbraio </option>
				<option> Marzo </option>
				<option> Aprile </option>
				<option> Maggio </option>
				<option> Giugno </option>
				<option> Luglio </option>
				<option> Agosto </option>
				<option> Settembre </option>
				<option> Ottobre </option>
				<option> Novembre </option>
				<option> Dicembre </option>
			</select>
			<select name = "giorno">
				<option> 1 </option>
				<option> 2 </option>
				<option> 3 </option>
				<option> 4 </option>
				<option> 5 </option>
				<option> 6 </option>
				<option> 7 </option>
				<option> 8 </option>
				<option> 9 </option>
				<option> 10 </option>
				<option> 11 </option>
				<option> 12 </option>
				<option> 13 </option>
				<option> 14 </option>
				<option> 15 </option>
				<option> 16 </option>
				<option> 17 </option>
				<option> 18 </option>
				<option> 19 </option>
				<option> 20 </option>
				<option> 21 </option>
				<option> 22 </option>
				<option> 23 </option>
				<option> 24 </option>
				<option> 25</option>
				<option> 26 </option>
				<option> 27 </option>
				<option> 28 </option>
				<option> 29 </option>
				<option> 30 </option>
				<option> 31 </option>
			</select>
			<select name = "anno">
				<option> 1980 </option>
				<option> 1981 </option>
				<option> 1982 </option>
				<option> 1983 </option>
				<option> 1984 </option>
				<option> 1985 </option>
				<option> 1986 </option>
				<option> 1987 </option>
				<option> 1988 </option>
				<option> 1989 </option>
				<option> 1990 </option>
				<option> 1991 </option>
				<option> 1992 </option>
				<option> 1993 </option>
				<option> 1994 </option>
				<option> 1995 </option>
				<option> 1996 </option>
				<option> 1997 </option>
			</select>
			
		<input id ="input" type = "email" name = "e-mail" placeholder = "E-mail"> <br>
		<span> Portatore di Handicap? </span>
		<input type = "radio" name = "si"> Si
		<input type = "radio" name = "no"> No <br>
		<input type = "text" name = "tutor" placeholder = "Tutor accademico">
		<input type = "text" name = "tutor" placeholder = "Tutor aziendale">
		<span> Scegli l'azienda (tra queste convenzionate) con cui svolgere il tirocinio </span>
		<br> <select id="azienda" name = "azienda">
				<option> Dipartimento di Informatica UNISA (Salerno)</option>
				<option> C.T.A. LIFE S.R.L. (Trapani) </option>
				<option> Limacorporate Spa (Udine) </option>
				<option> MANITALIDEA SPA (Torino) </option>
				<option> ADVANCED COMPOSITES SOLUTIONS SRL (Teramo) </option>
				<option> A.S.D. PANTANELLI SPORT (Siracusa) </option>
				<option> MAGNETI MARELLI SpA - POWERTRAIN (Bologna)</option>
			</select> <br> <br>
	<textarea id ="obiettivi" name ="obiettivi" placeholder = "Obiettivi formativi"></textarea>
	<br> <br> <input id ="submit" type = "submit" value = "Invia richiesta">

</div>

</body>
</html>
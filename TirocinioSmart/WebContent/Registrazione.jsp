<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione</title>
<link rel = "stylesheet" href = "stile.css">
</head>
<body>
<h1>Benvenuti su TirocinioSmart</h1>
<p align = "center">
<img id = "logo" src="images/logo.png">
</p>
<p id = "introduzione"> Il sistema che offre tutti i servizi che coinvolgono l'attivit� di tirocinio degli studenti
del dipartimento di Informatica dell'Universit� degli studi di Salerno. Per poter usufruire del nostro sistema occorre 
compilare il form di registrazione per registrarsi al nostro sistema
o di login se si � gia registrati. </p>
<div class = "wrap">
	<form> 
		<h2>Registrati al sistema</h2>
			<input id ="input" type = "text" name = "nome" placeholder = "Nome">
			<input id ="input" type = "text" name = "cognome" placeholder = "Cognome">
			<input id ="input" type = "email" name = "e-mail" placeholder = "E-mail">
			<input id ="input" type = "password" name = "password" placeholder = "Password">
			<input id ="input" type = "password" name = "conferma_password" placeholder = "Conferma password">
			<input id ="submit" type = "submit" value = "Registrati">
			<p> Sei gia registrato? </p>
			<input id ="submit" type = "submit" value = "Vai al login del sistema">
	</form>
</div>
</body>
</html>
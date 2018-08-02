<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questionario di valutazione</title>
<link rel = "stylesheet" href = "stile.css">
</head>
<body>
<p id = "introduzione"> Compila il questionario di valutazione abbinato al tirocinio </p>
<div class = "wrap">
	<h2>Compila tutti i campi</h2>
		<textarea id ="obiettivi" name ="obiettivi" placeholder = "Cosa hai ottenuto da questa esperienza?"></textarea>
		<span> Hai raggiunto i tuoi obiettivi? </span>
		<input type = "radio" name = "si"> Si
		<input type = "radio" name = "no"> No <br> <br>
		<span> Soddisfatto di questa esperienza? </span>
		<input type = "radio" name = "si"> Si
		<input type = "radio" name = "no"> No <br> <br>
		<span>Soddisfatto dei Tutor? </span>
		<input type = "radio" name = "si"> Si
		<input type = "radio" name = "no"> No <br> <br>
		<textarea id ="obiettivi" name ="obiettivi" placeholder = "Come sono stati i rapporti con i tutor? (Evidenzia i principali punti critici e positivi)"></textarea> <br> <br>
		<textarea id ="obiettivi" name ="obiettivi" placeholder = "Cosa hai realizzato durante questa esperienza?"></textarea>
		<br> <br> <input id ="submit" type = "submit" value = "Invia questionario">
</div>
</body>
</html>
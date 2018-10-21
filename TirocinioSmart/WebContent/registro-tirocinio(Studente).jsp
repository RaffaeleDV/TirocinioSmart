<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro del tirocinio</title>
<link rel = "stylesheet" href = "stile.css">
</head>
<body>
<p id = "introduzione"> Compila il registro e consegnalo </p>

<div class = "wrap">
	<h2>Compila tutti i campi</h2>
		<textarea id ="obiettivi" name ="obiettivi" placeholder = "Attività svolta"></textarea>
		<input type = "number" name = "data" placeholder = "Data"> <br>
		Orario: <br>
		<input type = "number" name = "ingresso" placeholder = "Ingresso"> 
		<input type = "number" name = "uscita" placeholder = "Uscita">
		<input type = "number" name = "totale" placeholder = "Totale ore">
		<input id ="submit" type = "submit" value = "Consegna Registro">
</div>
</body>
</html>
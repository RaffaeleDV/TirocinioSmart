<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file = "general-head.jsp"  %>
</head>
<body>
	<h1>Benvenuti su TirocinioSmart</h1>
	<p align = "center">
	<img id = "logo" src="images/logo.png">
	</p>
	<p id = "introduzione"> Il sistema che offre tutti i servizi che coinvolgono l'attività di tirocinio degli studenti
	del dipartimento di Informatica dell'Università degli studi di Salerno. Per poter usufruire del nostro sistema occorre 
	compilare il form di registrazione per registrarsi al nostro sistema
	o di login se si è gia registrati. </p>
	<div class = "wrap">
		
		<form> 
			<h2>Registrati al sistema</h2>
			<hr>
		    <input type="radio" class="radiob" id="radio_stud" name="tipo" value="studente"> Studente<br>
		    <input type="radio" class="radiob" id="radio_tut" name="tipo" value="tutor"> Tutor<br>
		    <input type="radio" class="radiob" id="radio_uff" name="tipo" value="ufficio"> Ufficio Tirocinio
			<hr>
			<input id ="input" type = "text" name = "nome" placeholder = "Nome">
			<input id ="in-key" type = "email" name = "matricola" placeholder = "Matricola">
			<input id ="input" type = "password" name = "password" placeholder = "Password">
			<input id ="input" type = "password" name = "conferma_password" placeholder = "Conferma password">
			<input type = "submit" value = "Registrati">
			<p> Sei gia registrato? </p>
			<a href="login-page.jsp">
				<input type = "button" value = "Vai al login del sistema">
			</a>
		</form>
	</div>
	<script type="text/javascript">
	$('.radiob').click(function() {
		
	
	if($('#radio_stud').is(':checked')){
		console.log("Studente");
		$("#login-studente-container").show();
		$("#login-tutor-container").hide();
		$("#login-ufficio-container").hide();
		
	}else if($("#radio_tut").is(':checked')){
		console.log("Tutor");
		$("#login-studente-container").hide();
		$("#login-tutor-container").show();
		$("#login-ufficio-container").hide();
		
	}else if($("#radio_uff").is(':checked')){
		console.log("Ufficio");
		$("#login-studente-container").hide();
		$("#login-tutor-container").hide();
		$("#login-ufficio-container").show();
		
	}
	})	
	</script>
</body>
</html>
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
	compilare il form di registrazione, per registrarsi al nostro sistema
	o di login se si è gia registrati. </p>
	
	<form class="wrap">
	  <input type="radio" class="radiob" id="radio_stud" name="tipo" value="studente"> Studente<br>
	  <input type="radio" class="radiob" id="radio_tut" name="tipo" value="tutor"> Tutor<br>
	  <input type="radio" class="radiob" id="radio_uff" name="tipo" value="ufficio"> Ufficio Tirocinio
	</form>
	
	<div class = "wrap" id="login-container">
		<div id="login-studente-container" style="display: none;">
			<form method="post" action="LoginServletStudente" > 
				<h2>Entra nel nostro sistema</h2>
					<input type = "text" name = "login-nome" placeholder = "Nome">
					<input type = "password" name = "login-password" placeholder = "Password">
					<input type = "submit" value = "Login">
					<p> Non sei registrato? </p>
					<a href="signup-page.jsp">
						<input type = "button" value = "Registrati">
					</a>					
			</form>
		</div>
		<div id="login-tutor-container" style="display: none;">
			<form method="post" action="LoginServletTutor"> 
				<h2>Entra nel nostro sistema</h2>
					<input type = "email" name = "login-nome" placeholder = "Nome">
					<input type = "password" name = "login-password" placeholder = "Password">
					<input type = "submit" value = "Login">
					<p> Non sei registrato? </p>
					<a href="signup-page.jsp">
						<input type = "button" value = "Registrati">
					</a>
			</form>
		</div>
		<div id="login-ufficio-container" style="display: none;">
			<form method="post" action="LoginServletUfficio" > 
				<h2>Entra nel nostro sistema</h2>
					<input type = "text" name = "login-nome" placeholder = "Nome">
					<input type = "password" name = "login-password" placeholder = "Password">
					<input type = "submit" value = "Login">
					<p> Non sei registrato? </p>
					<a href="signup-page.jsp">
						<input type = "button" value = "Registrati">
					</a>
			</form>
		</div>
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
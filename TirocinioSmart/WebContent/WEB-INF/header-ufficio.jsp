<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.UfficioBean"
    import="it.unisa.model.TutorBean" %>
<header id="header">
	<div id="logo-name-wrapper">
		<span id="logo-wrapper">
			<img id="ts-logo" src="images/logo.png"/>
		</span>
	</div>
	<div>
		<nav id="navbar">
			<ul>
				<li><a class="navbar-link" href="home-studente.jsp">Home</a></li>
				<li><a class="navbar-link" href="tirocinio-studente.jsp">Tirocinio</a></li>
				<li><a class="navbar-link" href="registro-studente.jsp">Registro Del Tirocinio</a></li>
				<li><a class="navbar-link" href="progetto-studente.jsp">Progetto Formativo</a></li>
				<li><a class="navbar-link" href="questionario.jsp">Questionario</a></li>
				<li><a class="navbar-link" href="convenzione.jsp">Convenzione</a></li>
			</ul>
		</nav>
	</div>	
	<span id="user-info-wrapper">
		<b id="user-name">
			<% 	UfficioBean ufficio = (UfficioBean) session.getAttribute("SessionUser"); 
				  if (ufficio != null) {
			%>
			        <%= ufficio.getNome() %>
			<%
				  } else {
				  	response.sendRedirect(request.getContextPath() + "/login-page.jsp");
				  }
			%>
		</b>
		<a class="button" href="">Logout</a>
	</span>
</header>
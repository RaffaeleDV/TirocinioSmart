<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isThreadSafe="false"
    import="it.unisa.model.StudenteBean" %>
<section id="studente-info-wrapper">
  <div class="info-wrapper">
    <%
      StudenteBean studente = 
        (StudenteBean)session.getAttribute("SessionStudente");
    %>
  	<p class="info"> Matricola: <%= studente.getMatricola() %></p>
  </div>
  <div class="info-wrapper">
  	<p class="info"> Nome: <%= studente.getNome() %></p>
  </div>
  <div class="info-wrapper">
    <p class="info"> CFU: <%= studente.getCfu() %></p>
  </div>
</section>>
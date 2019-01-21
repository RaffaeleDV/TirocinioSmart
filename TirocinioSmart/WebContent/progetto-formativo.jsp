<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isThreadSafe="false"
    import="it.unisa.model.ProgettoFormativoBean"%>
<section id="prog-formativo-wrapper" class="wrapper">
  <div class="wrapper">
    <div class="wrapper">
      <%
        ProgettoFormativoBean progetto = 
            (ProgettoFormativoBean)session.getAttribute("SessionProgetto");
      %>
      <div class="info-wrapper">
        <b class="info">ID Del Progetto Formativo:</b>
        <p class="info"><%= progetto.getId() %></p>
      </div>
      <div class="wrapper">
	    <b class="info">Informazioni Del Progetto Formativo:
        <p class="info"> 
          <%= progetto.getInfo() %>
        </p>
      </div>
      <div class="wrapper">
        <b class="info">Approvazione Del Progetto Formativo:</b>
        <p class="info">
          <%= progetto.getApprovazione() %>
        </p>
      </div>
    </div>
    <label id="button-wrapper" class="wrapper">
        <input class="button" name="visualizza-prog-formativo" type="button" value="Visualizza"/>
        <input class="button" name="approva-prog-formativo" type="button" value="Approva"/>
    </label>
  </div>
</section>
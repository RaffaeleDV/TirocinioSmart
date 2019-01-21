<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.ProgettoFormativoBean"%>
<section id="tirocinio">
  <div id="tirocinio-wrapper">
    <div>
      <input class="button" type="button" value="Segli Tirocinio"/>
    </div>
    <div>
      <div id="info-wrapper">
        <p class="info">
          <%
            ProgettoFormativoBean progetto = (ProgettoFormativoBean)session.getAttribute("SessionProgetto"); 
          %>
        </p>
        <input class="button" type="button" value="Visualizza Info"/>
      </div>
    </div>
    <div>
      <%@include file="tirocinio-info.jsp" %>
    </div>
    <div>
      <input class="button" type="button" value="Registri" />
    </div>
  </div>
</section>
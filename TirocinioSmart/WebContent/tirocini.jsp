<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.ProgettoFormativoBean"
    import="java.util.ArrayList"%>
<section class="tirocini-wrapper">
  <h1 class="info">Tirocini</h1>
  <%
    ArrayList<ProgettoFormativoBean> progetti = 
        (ArrayList<ProgettoFormativoBean>) session.getAttribute("SessionProgetti");
  %>
  <%
    ProgettoFormativoBean progetto = null;
    
  for(int i = 0; i < progetti.size(); i++) {
    progetto = progetti.get(i);
    
    if (progetto != null) {
  %>
    <div class="info-wrapper">
      <span class="info">
        <%
          int posNome = progetto.getInfo().indexOf("Nome");
        %>
        <%= progetto.getInfo().substring(posNome + 6, posNome + 25) %>
      </span>
    </div>
  <%
    }
  }
  %>
</section>
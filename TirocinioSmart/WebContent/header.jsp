<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.StudenteBean"
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.UtenteBean"
    import="it.unisa.model.UfficioBean" %>
<header id="header-wrapper" class="wrapper">
  <% 
    Object user = session.getAttribute("SessionUser");
      
    if (user == null) {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
  %>
  <div id="logo-wrapper" class="wrapper">
   <img id="logo" src="images/logo.png"/>
  </div>
  <div id="navbar-wrapper" class="wrapper">
    <nav id="navbar" class="wrapper">
      <ul>
        <li><a id="navbar-link" href="home-page.jsp">Home</a></li>
        <li><a id="navbar-link" href="tirocinio-page.jsp">Tirocinio</a></li>
        <%
          if (user instanceof StudenteBean) {
        %>
            <li><a id="navbar-link" href="registro-studente-page.jsp" onclick="visualizzaRegistroStudente()">Registro Del Tirocinio(Studente)</a></li>
        <%
          } else if (user instanceof TutorBean) {
        %>
            <li><a id="navbar-link" href="registri-tutor-page.jsp">Registri Del Tirocinio(Tutor)</a></li>
        <%
          } else if (user instanceof UfficioBean) {
        %>
            <li><a id="navbar-link" href="registri-ufficio-page.jsp">Registri Del Tirocinio(Ufficio)</a></li>
        <%
          }
        %>
        <li><a id="navbar-link" href="progetto-formativo-page.jsp">Progetto Formativo</a></li>
        <% 
          if (user.getClass().getName().equals(UfficioBean.class.getName())) {
        %>
            <li><a id="navbar-link" href="questionario-page.jsp">Questionario</a></li>
        <%
          }
        %>
        
        <%
          if (user.getClass().getName().equals(UfficioBean.class.getName())) {
        %>
            <li><a id="navbar-link" href="convenzione-page.jsp">Convenzione</a></li>
        <%
          }
        %>
      </ul>
    </nav>
  </div>	
  <span id="info-wrapper">
    <b id="user-name">
      <%
        UtenteBean utente = (UtenteBean) user;
        String nome = utente.getNome();
        
        if (utente.getClass().getName().equals(UfficioBean.class.getName())) {
        %>
          <%= "Ufficio".toString() %>
        <%
        } else if (nome != null) {
          if (!nome.equals("")) {
        %>
            <%= nome.toString() %>
        <%
          }
        }
        %>
    </b>
    <input type="button" class="button" value="Logout" />
  </span>
  <script type="text/javascript">
    function visualizzaRegistroStudente() {
      $.ajax({
        type : "POST",
        url : "RegistroStudenteServlet",
        contentType: "application/x-www-form-urlencoded",
        datatype : "json",
        data: "",
        success: function(data) {
          console.log("richiesta del registro da parte dello studente effettuata");
        },
        error: function(error) {
          console.log("Errore:"+ error);
        }
      })
    }
  </script>
</header>
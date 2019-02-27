<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.StudenteBean"
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.UfficioBean"
    import="it.unisa.model.AbstractBean" 
    import="java.util.logging.Logger"
    import="java.util.logging.Level" %>
<header id="header-wrapper" class="wrapper">
  <% 
    AbstractBean userBean = (AbstractBean) session.getAttribute("SessionUser");
      
    if (userBean == null) {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
  %>
  <div id="logo-wrapper" class="wrapper">
   <img id="logo" src="images/logo.png"/>
  </div>
  <div id="navbar-wrapper" class="wrapper">
    <nav id="navbar" class="wrapper">
      <ul id="navbar-list">
        <li><a id="navbar-link" href="home-page.jsp">Home</a></li>
        <%
          if (userBean instanceof StudenteBean) {
        %>
            <li><a id="navbar-link" href="registro-studente-page.jsp">Registro Del Tirocinio(Studente)</a></li>
            <li><a id="navbar-link" href="progetto-formativo-studente-page.jsp">Progetto Formativo(Studente)</a>
        <%
          } else if (userBean instanceof TutorBean) {
        %>
            <li><a id="navbar-link" href="registri-tutor-page.jsp">Registri Del Tirocinio(Tutor)</a></li>
            <li><a id="navbar-link" href="progetti-formativi-tutor-page.jsp">Progetti Formativi(Tutor)</a>
        <%
          } else if (userBean instanceof UfficioBean) {
        %>
            <li><a id="navbar-link" href="registri-ufficio-page.jsp">Registri Del Tirocinio(Ufficio)</a></li>
            <li><a id="navbar-link" href="progetti-formativi-ufficio-page.jsp">Progetti Formativi(Ufficio)</a></li>
            <li><a id="navbar-link" href="questionario-page.jsp">Questionario</a></li>
            <li><a id="navbar-link" href="convenzione-page.jsp">Convenzione</a></li>
        <%
          } else {
            //redirect to an [login] page
            RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
            view.forward(request, response);
          }
        %>
      </ul>
    </nav>
  </div>
  <div id="log-wrapper">
    <img id="login-icon" src="images/login-icon.png" onclick="visualizzaUserLog()"/>
  </div>
  <script type="text/javascript">
    function visualizzaUserLog() {
      var h = document.getElementById("user-log-wrapper").hidden;
      console.log("visualizzaUserLog invocato");
      if (h == false) {
        document.getElementById("user-log-wrapper").hidden = true;
      } else {
        document.getElementById("user-log-wrapper").hidden = false;
      }
    }
  </script>
</header>


	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.StudenteBean"
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.UtenteBean"
    import="it.unisa.model.UfficioBean" %>
<header id="header-wrapper" class="wrapper">
  <% 
    Object user = (UtenteBean) session.getAttribute("SessionUser");
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
        <li><a class="navbar-link" href="home-page.jsp">Home</a></li>
        <li><a class="navbar-link" href="tirocinio-page.jsp">Tirocinio</a></li>
        <li><a class="navbar-link" href="registro-page.jsp">Registro Del Tirocinio</a></li>
        <li><a class="navbar-link" href="progetto-formativo-page.jsp">Progetto Formativo</a></li>
        <% 
          if (user instanceof UfficioBean)
        %>
          <li><a class="navbar-link" href="questionario-page.jsp">Questionario</a></li>
        <%
          if (user instanceof UfficioBean)
        %>
            <li><a class="navbar-link" href="convenzione-page.jsp">Convenzione</a></li>
      </ul>
    </nav>
  </div>	
  <span id="info-wrapper">
    <b id="user-name">
    <%
      if (user != null) {
    %>
      <%= (((UtenteBean) user).getNome()).toString() %>
    <%
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    %>
    </b>
    <input type="button" class="button" href="">Logout</a>
  </span>
</header>
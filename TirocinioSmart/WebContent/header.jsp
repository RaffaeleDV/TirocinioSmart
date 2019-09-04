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
      response.sendRedirect(request.getContextPath() + "/login-page.jsp");
    }
  %>
  <div id="hlogo-wrapper" class="container">
   <img id="hlogo" src="images/logo.png"/>
  </div>
  <div id="hnav-wrapper" class="container">
    <nav id="hnav" class="container">
      <ul id="navbar-list">
        <li>
          <a id="navbar-link" href="home-page.jsp">Home</a>
        </li>
        <%
          if (userBean.getClass().getName().equals(StudenteBean.class.getName())) {
        %>
            <li><a id="navbar-link" href="registro-page.jsp">Registro Del Tirocinio(Studente)</a></li>
            <li><a id="navbar-link" href="progetto-formativo-page.jsp">Progetto Formativo(Studente)</a>
        <%
          } else if (userBean.getClass().getName().equals(TutorBean.class.getName())) {
        %>
            <li><a id="navbar-link" href="registri-page.jsp">Registri Del Tirocinio(Tutor)</a></li>
            <li><a id="navbar-link" href="progetti-formativi-page.jsp">Progetti Formativi(Tutor)</a>
        <%
          } else if (userBean.getClass().getName().equals(UfficioBean.class.getName())) {
        %>
            <li><a id="navbar-link" href="registri-page.jsp">Registri Del Tirocinio(Ufficio)</a></li>
            <li><a id="navbar-link" href="progetti-formativi-page.jsp">Progetti Formativi(Ufficio)</a></li>
        <%
          } else {
            response.sendRedirect(request.getContextPath() + "/login-page.jsp");
          }
        %>
      </ul>
    </nav>
  </div>
</header>
<script type="text/javascript">
  if (typeof String.trim == "undefined") {
    String.prototype.trim = function() {
      return this.replace(/(^\s*)|(\s*$)/g, "");
    }
  }    
</script>


	
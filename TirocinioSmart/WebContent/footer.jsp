<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.AbstractBean" 
    import="it.unisa.model.StudenteBean"
    import="it.unisa.model.TutorBean" 
    import="it.unisa.model.UfficioBean" %>
<footer id="footer-wrapper" class="wrapper">
	<div id="scroll-up-wrapper">
	  <a href="#header-wrapper">
	    <img id="scroll-up" src="images/scroll-up.png"/>
	  </a>
	</div>
	<% 
      AbstractBean userBeanFooter = (AbstractBean) session.getAttribute("SessionUser");
      
      if (userBeanFooter == null) {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    %>
	<nav id="footerbar" class="wrapper">
      <ul id="footerbar-list">
        <li><a id="footerbar-link" href="home-page.jsp">Home</a></li>
        <%
          if (userBeanFooter instanceof StudenteBean) {
        %>
            <li><a id="footerbar-link" href="registro-studente-page.jsp" onclick="visualizzaRegistroStudente()">Registro Del Tirocinio(Studente)</a></li>
            <li><a id="footerbar-link" href="progetto-formativo-studente-page.jsp">Progetto Formativo(Studente)</a>
        <%
          } else if (userBeanFooter instanceof TutorBean) {
        %>
            <li><a id="footerbar-link" href="registri-tutor-page.jsp">Registri Del Tirocinio(Tutor)</a></li>
            <li><a id="footerbar-link" href="progetti-formativi-tutor-page.jsp">Progetti Formativi(Tutor)</a>
        <%
          } else if (userBeanFooter instanceof UfficioBean) {
        %>
            <li><a id="footerbar-link" href="registri-ufficio-page.jsp">Registri Del Tirocinio(Ufficio)</a></li>
            <li><a id="footerbar-link" href="progetti-formativi-ufficio-page.jsp">Progetti Formativi(Ufficio)</a></li>
            <li><a id="footerbar-link" href="questionario-page.jsp">Questionario</a></li>
            <li><a id="footerbar-link" href="convenzione-page.jsp">Convenzione</a></li>
        <%
          } else {
            //redirect to an [login] page
            RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
            view.forward(request, response);
          }
        %>
      </ul>
    </nav>
</footer>
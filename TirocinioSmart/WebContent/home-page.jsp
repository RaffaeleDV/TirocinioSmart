<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <%@ include file = "general-head.jsp" %>	
</head>
<body id="body-home">
  <%@ include file = "header.jsp" %>
  <%@ include file = "questionari.jsp" %>
  <%
    AbstractBean convenzioniUtente = (AbstractBean) session.getAttribute("SessionUser");

    if (convenzioniUtente == null) {
      //redirect to an [login] page
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }

    if (convenzioniUtente.getClass().getName().equals(TutorBean.class.getName())) {
    %>
      <%@ include file = "convenzioni.jsp" %>
    <%
    }
  %>
  <%@ include file = "footer.jsp" %>
</body>
</html>
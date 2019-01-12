<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <%@ include file = "general-head.jsp" %>	
</head>
<body>
  <%@ include file = "header.jsp" %>
  <%@ include file = "questionari.jsp" %>
  <%
    Object convenzioniUtente = session.getAttribute("SessionUser");

    if (convenzioniUtente == null) {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }

    if (convenzioniUtente instanceof TutorBean) {
      %>
        <%@ include file = "convenzioni.jsp" %>
      <%
    }
  %>
  <%@ include file = "footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="404-error-wrapper">
  <%
    String errorMessage404 = (String) session.getAttribute("SessionErrorMessage404");
  %>
  <div id="404-error-message-wrapper">
    <p id="404-error-message"><%= errorMessage404.toString() %></p>
  </div>
  <div>
    <p id="404-info"> Page Not Found </p>
    <p id="404-text"> 404 </p>
  </div>
</section>
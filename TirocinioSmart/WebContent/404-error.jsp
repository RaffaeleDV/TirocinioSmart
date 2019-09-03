<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="error-wrapper">
  <div id="400-wrapper">
    <h1 id="400" style="color: black;" align="center"></p>
    <h2 id="page-not-found">Page Not Found(400)</h2>
  </div>
  <%
    String errorMessage404 = (String) session.getAttribute("SessionErrorMessage404");

    if (errorMessage404 != null) {
      if (!errorMessage404.equals("")) {
        %>
        <div id="error-message-wrapper">
          <p id="error-message"><%= errorMessage404.toString() %></p>
        </div>
        <%
      }
    }
  %>
</section>
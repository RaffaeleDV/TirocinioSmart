<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="error-wrapper">
  <div id="500-wrapper">
    <h1 id="500" style="color: black;" align="center"></p>
    <h2 id="internal-error">Internal Server Error(500)</h2>
  </div>
  <%
    String errorMessage500 = (String) session.getAttribute("SessionErrorMessage500");
  
    if (errorMessage500 != null) {
      if (!errorMessage500.equals("")) {
        %>
        <div id="error-message-wrapper">
          <p id="error-message"><%= errorMessage500.toString() %> </p>
        </div>
        <%
      }
    }
  %>
</section>
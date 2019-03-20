<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="500-error-wrapper">
  <%
    String errorMessage500 = (String) session.getAttribute("SessionErrorMessage500");
  
    if (errorMessage500 != null) {
      if (!errorMessage500.equals("")) {
        %>
        <div id="500-error-message-wrapper">
          <p id="500-error-message"> <%= errorMessage500.toString() %> </p>
        </div>
        <div>
          <p id="500-info"> Internal Server Error </p>
          <p id="500-text"> 500 </p>
        </div>
        <%
      }
    }
  %>
</section>
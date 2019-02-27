<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.UfficioBean"
    import="it.unisa.model.StudenteBean" 
    import="it.unisa.model.AbstractBean"
    import="it.unisa.model.TutorBean"
    import="java.util.logging.Level"
    import="java.util.logging.Logger" %>
<div id="user-log-wrapper" class="wrapper" hidden>
  <div id="user-log-info-wrapper">
    <div id="user-icon-wrapper">
      <img id="user-icon" src="images/user-icon.png"/>
    </div>
    <b id="user-name">
    <%
      AbstractBean userBeanLog = (AbstractBean) session.getAttribute("SessionUser");
      String nome = null;
     
      if (userBeanLog != null) {
        if (userBeanLog.getClass().getName().equals(UfficioBean.class.getName())) {
          %>
            <%= "Ufficio: ".toString() %>
          <%
       
          UfficioBean ufficioBean = (UfficioBean) userBeanLog;
          nome = ufficioBean.getNome();
          if (nome != null) {
            if (!nome.equals("")) {
              %>
                <%= nome.toString() %>
              <%
            } else {
              //redirect to an [error] page
              //set del valore per SessionErrorMessage500
              RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
              view.forward(request, response);
            }
          } else {
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
        } else if (userBeanLog.getClass().getName().equals(StudenteBean.class.getName())) {
          StudenteBean studenteBean = (StudenteBean) userBeanLog;
          %>
            <%= "Studente: ".toString() %>
          <%
          nome = studenteBean.getNome();
          if (nome != null) {
            if (!nome.equals("")) {
              %>
                <%= nome.toString() %>
              <%
            } else {
              //redirect to an [error] page
              //set del valore per SessionErrorMessage500
              RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
              view.forward(request, response);
            }
          } else {
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
        } else if (userBeanLog.getClass().getName().equals(TutorBean.class.getName())) {
          TutorBean tutorBean = (TutorBean) userBeanLog;
          %>
            <%= "Tutor: ".toString() %>
          <%
          nome = tutorBean.getNome();
          if (nome != null) {
            if (!nome.equals("")) {
              %>
                <%= nome.toString() %>
              <%
            } else {
              //redirect to an [error] page
              //set del valore per SessionErrorMessage500
              RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
              view.forward(request, response);
            }
          } else {
            //redirect to an [error] page
            //set del valore per SessionErrorMessage500
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
        } else {
          //redirect to an [error] page
          //set del valore per SessionErrorMessage500
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun utente trovato da visualizzare nel header");
        //redirect to an [login] page
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    %>
    </b>
  </div>
  <div id="user-log-command-wrapper" class="wrapper">
    <input id="user-log-button" type="button" value="Login"/>
    <input id="user-log-button" type="button" value="Logout"/> 
  </div>
</div>

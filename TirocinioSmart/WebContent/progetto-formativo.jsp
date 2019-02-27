<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isThreadSafe="false"
    import="it.unisa.model.ProgettoFormativoBean"
    import="it.unisa.model.ProgettoFormativoModelDM"
    import="it.unisa.model.AbstractBean" 
    import="it.unisa.model.StudenteBean" 
    import="it.unisa.model.TutorBean" 
    import="it.unisa.model.UfficioBean"
    import="java.util.List" 
    import="java.util.logging.Logger"
    import="java.util.logging.Level"
    import="java.sql.SQLException" %>
<section id="progetto-formativo" class="wrapper">
  <div id="progetto-formativo-heading" align="center" style="padding:50px;">
    <h3 style="font-size: 24px; color: black;">Progetto Formativo Dello Studente</h3>
    <img id="progetto-formativo-heading-icon" src="images/prog-form-icon.png"/>
  </div>
  <%
    AbstractBean utenteProgetto = (AbstractBean) session.getAttribute("SessionUser");
    ProgettoFormativoModelDM progettoFormativoModelDM = (ProgettoFormativoModelDM) getServletContext().getAttribute("ContextProgettoFormativoModelDM");
    List<AbstractBean> progetti = null;
    ProgettoFormativoBean progettoFormativoBean = null;
    String userBeanClass = null;
    StudenteBean studenteBean = null;
    TutorBean tutorBean = null;
    UfficioBean ufficioBean = null;
    
    if (progettoFormativoModelDM == null) {
      progettoFormativoModelDM = new ProgettoFormativoModelDM();
      getServletContext().setAttribute("ContextProgettoFormativoModelDM", progettoFormativoModelDM);
    }
    
    if (utenteProgetto != null) {
      userBeanClass = utenteProgetto.getClass().getName();
    } else {
      //redirect to an [login] page
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    
    if (userBeanClass.equals(StudenteBean.class.getName())) {
      studenteBean = (StudenteBean) utenteProgetto;
      try {
        progettoFormativoBean = (ProgettoFormativoBean) progettoFormativoModelDM.doRetrieveByMatricola(studenteBean.getMatricola());
      } catch (SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        //redirect to an [error] page
      }
    } else if (
          userBeanClass.equals(TutorBean.class.getName()) ||
          userBeanClass.equals(UfficioBean.class.getName())) {
        tutorBean = (TutorBean) utenteProgetto;
        progettoFormativoBean = (ProgettoFormativoBean) request.getAttribute("RequestProgettoFormativoBean");
    } else {
      //redirect to an [login] page
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
     
    if (progettoFormativoBean != null) {
      //codice html
  %>
      <div id="progetto-formativo-wrapper" class="wrapper">
        <p id="prog-form-info" class="info">ID: </p><b id="prog-form-info-id" class="info"><%= progettoFormativoBean.getID() %></b>
      </div>
      <div id="progetto-formativo-info" class="wrapper" hidden >
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Info: </p><b id="prog-form-info-info" class="info"><%= progettoFormativoBean.getInfo() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">IDs Assicurazioni: </p><b id="prog-form-info-ids" class="info"><%= progettoFormativoBean.getIdsAssicurazioni() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Formazione: </p><b id="prog-form-info-formazione" class="info"><%= progettoFormativoBean.getFormazione() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Modalita: </p><b id="prog-form-info-modalita" class="info"><%= progettoFormativoBean.getModalita() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Responsabile: </p><b id="prog-form-info-respo" class="info"><%= progettoFormativoBean.getResponsabile() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Inizio Periodo: </p><b id="prog-form-info-inizio" class="info"><%= progettoFormativoBean.getInizioPeriodo() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Termine Periodo: </p><b id="prog-form-info-termine" class="info"><%= progettoFormativoBean.getTerminePeriodo() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Data Rilascio: </p><b id="prog-form-info-rilascio" class="info"><%= progettoFormativoBean.getDataRilascio() %></b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Approvazione: </p>
          <b id="prog-form-info-approvazione" class="info">
          <%
            if (progettoFormativoBean.getApprovazione()) {
          %>
              <b id="approvazione-info"><%= "Il Progetto Formativo Risulta Approvato Dallo Studente".toString() %></b>
              <img id="true-false-icon-approvazione" src="images/true.png"/>
          <%
            } else {
          %>
              <b id="approvazione-info"><%= "Il Progetto Formativo Non Risulta Approvato Dallo Studente".toString() %></b>
              <img id="true-false-icon-approvazione" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Approvazione Genitori: </p>
          <b id="prog-form-info-approvazione-genitori" class="info">
          <% 
            if (progettoFormativoBean.getApprovazioneGenitori()) {
          %>
              <%= "Il Progetto Formativo Risulta Approvato Dai Genitori".toString() %>
              <img id="true-false-icon" src="images/true.png"/>
          <%
            } else {
          %>
              <%= "Il Progetto Formativo Non Risulta Approvato Dai Genitori".toString() %>
              <img id="true-false-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Approvazione Responsabile: </p>
          <b id="prog-form-info-approvazione-respo" class="info">
          <%
            if (progettoFormativoBean.getApprovazioneRespo()) {
          %>
              <%= "Il Progetto Formativo Risulta Approvato Dal Responsabile".toString() %>
              <img id="true-false-icon" src="images/true.png"/>
          <%
            } else {
          %>
              <%= "Il Progetto Formativo Non Risulta Approvato Dal Responsabile".toString() %>
              <img id="true-false-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Approvazione Tutor Accademico: </p>
          <b id="prog-form-info-approvazione-tutorAcc" class="info">
          <%
            if (progettoFormativoBean.getApprovazioneTutorAcc()) {
          %>
              <%= "Il Progetto Formativo Risulta Approvato Dal Tutor Accademico".toString() %>
              <img id="true-false-icon" src="images/true.png"/>
          <%
            } else {
          %>
              <%= "Il Progetto Formativo Non Risulta Approvato Dal Tutor Accademico".toString() %>
              <img id="true-false-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-wrapper" class="wrapper">
          <p id="prog-form-info" class="info">Approvazione Tutor Aziendale: </p>
          <b id="prog-form-info-approvazione-tutorAz" class="info">
          <%
            if (progettoFormativoBean.getApprovazioneTutorAz()) {
          %>    
              <%= "Il Progetto Formativo Risulta Approvato Dal Tutor Aziendale".toString() %>
              <img id="true-false-icon" src="images/true.png"/>
          <%  
            } else {
          %>
              <%= "Il Progetto Formativo Non Risulta Approvato Dal Tutor Aziendale".toString() %>
              <img id="true-false-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
      </div>
  <%
    } else {
      //redirect to an [error] page
      //set del valore per SessionErrorMessage404
      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
      view.forward(request, response);
    }
  %>
  <span id="prog-form-button-wrapper" class="wrapper" align="ltr">
    <input id="button" class="button" name="visualizza-prog-formativo" type="button" value="Visualizza" onclick="visualizzaProgettoFormativo()"/>
    <input id="button" class="button" name="approva-prog-formativo" type="button" value="Approva" onclick="approvaProgettoFormativo()"/>
  </span>
  
  <div hidden >
    <p id="approvazione-info"><%= progettoFormativoBean.getApprovazione() %></p>
  </div>
  
  <script type="text/javascript">
    function visualizzaProgettoFormativo() {
      var hidden = document.getElementById("progetto-formativo-info").hidden;
    
      if (hidden == true) {
        document.getElementById("progetto-formativo-info").hidden = false;
      } else {
        document.getElementById("progetto-formativo-info").hidden = true;
      }
    }
    
    function approvaProgettoFormativo() {
      var approvazione = document.getElementById("approvazione-info").innerHTML;
      
      if (approvazione == "true") {
        console.log("Progetto Formativo Approvato");
      } else {
        var id = document.getElementById("prog-form-info-id").innerHTML;
        
        $.ajax({
          type : "POST",
          url : "ApprovazioneProgettoFormativoStudente",
          contentType: "application/x-www-form-urlencoded",
          datatype : "json", 
          data: "id="+id,
          success: function(data) {
	        var json = data;
	        var approvazione = json.approvazione;
	        
	        if (approvazione) {
	          document.getElementById("approvazione-info").innerHTML = "Il Progetto Formativo Risulta Approvato Dallo Studente";
	          document.getElementById("true-false-icon-approvazione").src = "images/true.png";
	        }
	        
          },
          error: function(error) {
            console.log("Errore:"+ error);
          }
        })
      }
    }
  </script>
</section>
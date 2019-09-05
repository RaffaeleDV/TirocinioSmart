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
<section id="progetto-formativo-wrapper" class="wrapper">
  <div id="progetto-formativo-heading-wrapper">
    <img id="progetto-formativo-heading-icon" src="images/prog-form-icon.png"/>
    <h3 id="progetto-formativo-heading" style="font-size: 24px; color: black;"></h3>
  </div>
  <%
    AbstractBean utenteBean = null;
    StudenteBean studenteBean = null;
    AbstractBean utenteProgetto = (AbstractBean) session.getAttribute("SessionUser");
    ProgettoFormativoBean progettoFormativoBean = (ProgettoFormativoBean) request.getAttribute("RequestProgettoFormativoBean");
    
    if (progettoFormativoBean == null) {
    	if (session != null) {
    	  utenteBean = (AbstractBean) session.getAttribute("SessionUser");
    	  if (utenteBean.getClass().getName().equals(StudenteBean.class.getName())) {
    	    studenteBean = (StudenteBean) utenteBean;
    	    try {
    	      progettoFormativoBean = (ProgettoFormativoBean) ProgettoFormativoModelDM.INSTANCE.doRetrieveByMatricola(studenteBean.getMatricola());
    	    } catch (SQLException e) {
    	      Logger.getGlobal().log(Level.SEVERE, "Oggetto ProgettoFormativoBean Non Trovato.");
    	      //redirect to an error page
    	      //set the error message
    	      RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
    	      view.forward(request, response);
    	    }
    	  } else if (utenteBean.getClass().getName().equals(TutorBean.class.getName()) ||
    	      utenteBean.getClass().getName().equals(TutorBean.class.getName())) {
    	    response.sendRedirect(request.getContextPath() + "/500-page.jsp");
    	  } else {
    	    Logger.getGlobal().log(Level.SEVERE, "Oggetto HttpSession Non Trovato.");
    	    //redirect to an [login] page
    	    RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
    	    view.forward(request, response);
    	  }
      } else {
    	  response.sendRedirect(request.getContextPath() + "/login-page.jsp");
      }
    } else {
  %>
      <div id="progetto-formativo-info-container" class="wrapper" hidden=true>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">ID</p>
          <p id="progetto-formativo-info-id" class="info"><%= progettoFormativoBean.getID() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Info.</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getInfo() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">IDs Assicurazioni</p>
          <p id="progetto-formativo-info" class="info"><%= ProgettoFormativoModelDM.INSTANCE.doRetrieveIDSAssicurazioni(progettoFormativoBean) %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Formazione</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getFormazione() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Modalita</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getModalita() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Responsabile</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getResponsabile() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Inizio Periodo</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getInizioPeriodo() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Termine Periodo</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getTerminePeriodo() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Data Rilascio</p>
          <p id="progetto-formativo-info" class="info"><%= progettoFormativoBean.getDataRilascio() %></p>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Approvazione Genitori</p>
          <b id="prog-form-info-approvazione-genitori" class="info">
          <% 
            if (progettoFormativoBean.isApprovazioneGenitori()) {
          %>
              <!-- <%= "Il Progetto Formativo Risulta Approvato Dai Genitori".toString() %>-->
              <img id="tf-agenitori" class="tf-icon" src="images/true.png"/>
          <%
            } else {
          %>
              <!-- <%= "Il Progetto Formativo Non Risulta Approvato Dai Genitori".toString() %> -->
              <img id="tf-agenitori" class="tf-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Approvazione Responsabile: </p>
          <b id="prog-form-info-approvazione-respo" class="info">
          <%
            if (progettoFormativoBean.isApprovazioneRespo()) {
          %>
              <!-- <%= "Il Progetto Formativo Risulta Approvato Dal Responsabile".toString() %>  -->
              <img id="tf-aresponsabile" class="tf-icon" src="images/true.png"/>
          <%
            } else {
          %>
              <!-- <%= "Il Progetto Formativo Non Risulta Approvato Dal Responsabile".toString() %> -->
              <img id="tf-aresponsabile" class="tf-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Approvazione Tutor Accademico: </p>
          <b id="prog-form-info-approvazione-tutorAcc" class="info">
          <%
            if (progettoFormativoBean.isApprovazioneTutorAcc()) {
          %>
              <!-- <%= "Il Progetto Formativo Risulta Approvato Dal Tutor Accademico".toString() %> -->
              <img id="tf-atutor-accademico" class="tf-icon" src="images/true.png"/>
          <%
            } else {
          %>
              <!-- <%= "Il Progetto Formativo Non Risulta Approvato Dal Tutor Accademico".toString() %>  -->
              <img id="tf-atutor-accademico" class="tf-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
        <div id="progetto-formativo-info-wrapper" class="wrapper">
          <p id="progetto-formativo-info-heading" class="info-heading">Approvazione Tutor Aziendale</p>
          <b id="prog-form-info-approvazione-tutorAz" class="info">
          <%
            if (progettoFormativoBean.isApprovazioneTutorAz()) {
          %>    
              <!-- <%= "Il Progetto Formativo Risulta Approvato Dal Tutor Aziendale".toString() %> -->
              <img id="tf-atutor-aziendale" class="tf-icon" src="images/true.png"/>
          <%  
            } else {
          %>
              <!-- <%= "Il Progetto Formativo Non Risulta Approvato Dal Tutor Aziendale".toString() %> -->
              <img id="tf-atutor-aziendale" class="tf-icon" src="images/false.png"/>
          <%
            }
          %>
          </b>
        </div>
      </div>
  <%
    }
  %>
  <div id="progetto-formativo-options-wrapper" class="wrapper">
    <input id="ts-button" class="button" name="visualizza-prog-formativo" type="button" value="Visualizza" onclick="visualizzaProgettoFormativoWrapper()"/>
    <%
      if (utenteProgetto != null) {
    	  if (utenteProgetto.getClass().getName().equals(TutorBean.class.getName())) {
          TutorBean tBean = (TutorBean) utenteProgetto;
          if (tBean.getTipo().equals("Accademico")) {
    %>
            <input id="ts-button" class="button" name="approva-prog-formativo" type="button" value="Approva" onclick="approvaProgettoFormativoAccademico()"/>
    <%
          } else if (tBean.getTipo().equals("Aziendale")) {
    %>
            <input id="ts-button" class="button" name="approva-prog-formativo" type="button" value="Approva" onclick="approvaProgettoFormativoAziendale()"/>
    <%
          } else {
            //redirect to an error page
            //set the 500 error message
            RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
            view.forward(request, response);
          }
    	  }
      } else {
    	  response.sendRedirect(request.getContextPath() + "/login-page.jsp");
      }
    %>
  </div>
</section>
<script type="text/javascript">
  function visualizzaProgettoFormativoWrapper() {
    var hidden = document.getElementById("progetto-formativo-info-container").hidden;
    if (hidden == true) {
      hidden = false;
      document.getElementById("progetto-formativo-info-container").hidden = false;
    } else {
      hidden = true;
      document.getElementById("progetto-formativo-info-container").hidden = true;
    }
  };
  
  function approvaProgettoFormativoAccademico() {
    var approvazione = document.getElementById("atutor-accademico").innerHTML;
    
    if (approvazione == "false") {
      var id = document.getElementById("prog-form-info-id").innerHTML.trim();
      
      $.ajax({
        type : "POST",
        url : "ApprovazioneProgettoFormativoAccademicoStudente",
        contentType: "application/x-www-form-urlencoded",
        datatype : "json", 
        data: "id="+id,
        success : function(data) {
          if (data.approvazione == true) {
            console.log("Il Progetto Formativo E' Stato Approvato Con Successo Dal Tutor Accademico");
            document.getElementById("atutor-accademico").innerHTML = "true";
            document.getElementById("tf-atutor-accademico").src = "images/true.png";
          } else {
            document.getElementById("atutor-accademico").innerHTML = "false";
            document.getElementById("tf-atutor-accademico").scr = "images/false.png";
          }
        },
        error : function(error) {
          console.log("Errore:" + error);
          document.getElementById("atutor-accademico").innerHTML = "false";
          document.getElementById("tf-atutor-accademico").src = "images/false.png";
        }
      })
    }
  };
  
  function approvaProgettoFormativoAziendale() {
    var approvazione = document.getElementById("atutor-aziendale").innerHTML.trim();
    
    if (approvazione == "true") {
      console.log("Il Progetto Formativo Risulta Approvato");
    } else {
      var id = document.getElementById("prog-form-info-id").innerHTML.trim();
      
      $.ajax({
        type : "POST",
        url : "ApprovazioneProgettoFormativoAziendaleStudente",
        contentType: "application/x-www-form-urlencoded",
        datatype : "json",
        data : "id="+id,
        success : function(data) {
          if (data.approvazione == true) {
            console.log("Il Progetto Formativo E' Stato Approvato Con Successo Dal Tutor Aziendale");
            document.getElementById("atutor-aziendale").innerHTML = "true";
            document.getElementById("tf-atutor-aziendale").src = "images/true.png";
          } else {
            document.getElementById("atutor-aziendale").innerHTML = "false";
            document.getElementById("tf-atutor-aziendale").src = "images/"
          }
        },
        error : function(error) {
          console.log("Error:" + error);
          document.getElementById("atutor-accademico").innerHTML = "false";
          document.getElementById("tf-atutor-aziendale").src = "images/false.png";
        }
      })
    }
  };
  
  if (typeof String.trim == "undefined") {
    String.prototype.trim = function() {
      return this.replace(/(^\s*)|(\s*$)/g, "");
    }
  }
</script>
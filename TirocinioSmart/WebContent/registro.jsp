<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
  isThreadSafe="false"
  import="it.unisa.model.RegistroBean"
  import="it.unisa.model.StudenteBean"
  import="it.unisa.model.AbstractBean"
  import="it.unisa.model.TutorBean"
  import="it.unisa.model.UfficioBean"
  import="it.unisa.model.AttivitaTirocinioBean"
  import="it.unisa.model.AttivitaTirocinioModelDM"
  import="it.unisa.model.StrutturaOspitanteBean"
  import="it.unisa.model.StrutturaOspitanteModelDM"
  import="java.sql.SQLException"
  import="it.unisa.model.RegistroModelDM"
  import="java.util.List"
  import="java.util.logging.Logger"
  import="java.util.logging.Level"%>
<section id="registro-wrapper" class="wrapper">
  <div id="registro-heading-wrapper" class="wrapper">
    <img id="registro-heading-icon" src="images/register-icon.png" class="icon"/>
    <h3 id="registro-heading" class="heading"></h3>
  </div>
    <%
      RegistroBean registroBean = null;
      List<AbstractBean> attivitaTirocinioRegistro = null;
      AbstractBean utenteRegistroBean = (AbstractBean) session.getAttribute("SessionUser");
      StudenteBean studenteRegistroBean = null;
      TutorBean tutorRegistroBean = null;
      UfficioBean ufficioRegistroBean = null;
      String registroID = request.getParameter("id");
      int idRegistro = -1;
      
      try {
    	  idRegistro = Integer.valueOf(registroID);
      } catch (NumberFormatException e) {
    	  Logger.getGlobal().log(Level.SEVERE, e.getMessage());
    	  //redirect to an [error] page
      }
      
      if (utenteRegistroBean != null) {
        if (utenteRegistroBean instanceof StudenteBean) {
          studenteRegistroBean = (StudenteBean) utenteRegistroBean;
          registroBean = (RegistroBean) session.getAttribute("SessionRegistro");
          if (registroBean == null) {
            try {
              registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByStudente(studenteRegistroBean.getID());
            } catch (SQLException e) {
              Logger.getGlobal().log(Level.SEVERE, e.getMessage());
              //redirect to an [error] page
            }
          } else if (utenteRegistroBean instanceof TutorBean) {
            if (idRegistro >= 0) {
              try {
            	  if (tutorRegistroBean.getTipo().equals("Accademico")) {
                  if (RegistroModelDM.INSTANCE.isTutorAccRegistro(tutorRegistroBean.getID(), idRegistro)) {
                    registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByKey(idRegistro);
                  } else {
                	  Logger.getGlobal().log(Level.SEVERE, "Accesso Negato Al Registro: ID Registro Risulta Di Un' Altro Tutor");
                	  //redirect to an [error] page
                  }
            	  } else if (tutorRegistroBean.getTipo().equals("Aziendale")) {
            		  if (RegistroModelDM.INSTANCE.isTutorAzRegistro(tutorRegistroBean.getID(), idRegistro)) {
            			  registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByKey(idRegistro);
            		  } else {
            			  Logger.getGlobal().log(Level.SEVERE, "Accesso Negato Al Registro: ID Registro Risulta Di Un' Altro Tutor");
            			  //redirect to an [error] page
            		  }
            	  } else {
            		  Logger.getGlobal().log(Level.SEVERE, "Accesso Negato Al Registro: Istanza Di Tutor Non Valida");
            		  //redirect to an [error] page
            	  }
              } catch (SQLException e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                //redirect to an [error] page
              }
            } else {
              Logger.getGlobal().log(Level.SEVERE, "Accesso Negato Al Registro: ID Negativo");
              //redirect to an [error] page
            }
          } else if (utenteRegistroBean instanceof UfficioBean) {
            idRegistro = (int) session.getAttribute("SessionRegistroID");
            if (idRegistro >= 0) {
              try {
            	  if (RegistroModelDM.INSTANCE.isUfficioRegistro(ufficioRegistroBean.getID(), idRegistro)) {
                  registroBean = (RegistroBean) RegistroModelDM.INSTANCE.doRetrieveByKey(idRegistro);
            	  } else {
            		  Logger.getGlobal().log(Level.SEVERE, "Accesso Negato Al Registro: ID Registro Risulta Di Un' Altro Ufficio");
            		  //redirect to an [error] page
            	  }
              } catch (SQLException e) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                //redirect to an [error] page
              }
            } else {
            	Logger.getGlobal().log(Level.SEVERE, "Accesso Negato Al Registro Con ID Negativo");
              //redirect to an [error] page
            }
          } else {
        	  Logger.getGlobal().log(Level.SEVERE, "Istanza Di Utente Non Valida");
            //redirect to an [error] page
          }
        } else {
          //redirect to an [error] page
        }
      } else {
    	  Logger.getGlobal().log(Level.SEVERE, "Nessun Utente Registrato Al Login");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }

      if (registroBean != null) {
        if (studenteRegistroBean instanceof StudenteBean) {
          session.setAttribute("SessionRegistro", registroBean);
        }
        try {
          attivitaTirocinioRegistro = (List<AbstractBean>) AttivitaTirocinioModelDM.INSTANCE.doRetrieveByRegistro(registroBean.getID());
          session.setAttribute("SessionAttivitaTirocinioList", attivitaTirocinioRegistro);
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
          //set the 500 error message
          RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
          view.forward(request, response);
        }
      } else {
        //redirect to an [error] page
        //set the 404 error message
        RequestDispatcher view = request.getRequestDispatcher("404-page.jsp");
        view.forward(request, response);
      }
 	  %>
 	<div id="registro-info-container" class="wrapper" hidden=true>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Nome</a>
      <p id="registro-info-nome" class="info">
        <%= registroBean.getNome() %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">ID</a>
      <p id="registro-info-id" class="info">
        <%= registroBean.getID() %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Descrizione</a>
      <p id="registro-info-descrizione" class="info">
        <%= registroBean.getDescrizione() %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Consegna</a>
      <p id="registro-info-consegna" class="info">
        <%
          if (registroBean.getConsegna()) {
        %>
            <img id="tf-consegna" class="tf-icon" src="images/true.png"/>
        <%
          } else {
        %>
            <img id="tf-consegna" class="tf-icon" src="images/false.png"/>
        <%
          }
        %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Conferma Del Tutor Accademico</a>
      <p id="registro-info-conferma" class="info">
        <%
          if (registroBean.getConfermaTutorAcc()) {
        %>
            <img id="tf-caccademico" class="tf-icon" src="images/true.png"/>
        <%
          } else {
        %>
            <img id="tf-caccademico" class="tf-icon" src="images/false.png"/>
        <%
          }
        %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Conferma Del Tutor Aziendale</a>
      <p id="registro-info-conferma" class="info">
        <%
          if (registroBean.getConfermaTutorAz()) {
        %>
            <img id="tf-caziendale" class="tf-icon" src="images/true.png"/>
        <%
          } else {
        %>
            <img id="tf-caziendale" class="tf-icon" src="images/false.png"/>
        <%
          }
        %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Conferma Dell' Ufficio</a>
      <p id="registro-info-conferma" class="info">
        <%
          if (registroBean.getConfermaUff()) {
        %>
            <img id="tf-cufficio" class="tf-icon" src="images/true.png"/>
        <%
          } else {
        %>
            <img id="tf-cufficio" class="tf-icon" src="images/false.png"/>
        <%
          }
        %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Data Creazione Del Registro</a>
      <p id="registro-info-data-istituzione" class="info">
        <%= registroBean.getPrimaIstituzione() %>
      </p>
    </div>
    <div id="registro-info-wrapper" class="wrapper">
      <a id="registro-info-heading" class="info-heading">Data Di Ultimo Aggiornamento</a>
      <p id="registro-info-data-aggiornamento" class="info">
        <%= registroBean.getUltimoAgg().toString() %>
      </p>
    </div>
  </div>
  <div id="modifica-registro-wrapper" class="wrapper" hidden=true>
    <p id="modifica-registro-info">Nome: </p>
    <input id="mrnome" type="text" placeholder="inserire nome" 
        oninput="goodNome()"/>
    <p id="modifica-registro-info">ID: </p>
    <input id="mrid" type="text" placeholder="inserire id"
        oninput="goodID()"/>
    <p id="modifica-registro-info">Descrizione: </p>
    <input id="mrdescrizione" type="text" placeholder="inserire descrizione"
        oninput="goodDescrizione()"/>
    <div id="conferma-wrapper" class="wrapper">
      <input id="conferma-registro-info" class="button" type="button" value="Conferma" onclick="modificaRegistro()"/>
    </div>
  </div>
  <div id="registro-options-wrapper" class="wrapper" hidden=false>
    <input id="ts-button" class="button"
        type="button" value="Visualizza" onclick="visualizzaRegistroWrapper()"/>
    <input id="ts-button" class="button"
        type="button" value="Modifica" onclick="modificaRegistro()"/>
    <input id="ts-button" class="button"
        type="button" value="Consegna" onclick="consegnaRegistro()"/>
  </div>
</section>
<script type="text/javascript">
  var MAXREGID = 10;
  var MAXREGNOME = 30;
  var MAXREGDESCRIZIONE = 1024;
  
  goodID();
  goodNome();
  goodDescrizione();
  
  function goodID() {
	  if (validateRegistroID() == true) {
		  $("#mrid").css("border", "3px #80ff80 solid");
	  } else {
		  $("#mrid").css("border", "3px #ff4040 solid");
	  }
  };
  
  function goodNome() {
	  if (validateRegistroNome() == true) {
		  $("#mrnome").css("border", "3px #80ff80 solid");
	  } else {
		  $("#mrnome").css("border", "3px #ff4040 solid");
	  }
  };

  function goodDescrizione() {
	  if (validateRegistroDescrizione() == true) {
		  $("#mrdescrizione").css("border", "3px #80ff80 solid");
	  } else {
		  $("#mrdescrizione").css("border", "3px #ff4040 solid");
	  }
  };
  
  function validateRegistroID() {
	  var id = $("#mrid").val().trim();
	  if (id != null) {
		  var reID = /^[0-9]+$/;
	    if (id.length <= MAXREGID && reID.exec(id)) {
	      return true;
	    }
	  }
	  return false;
  };
  
  function validateRegistroNome() {
	  var nome = $("#mrnome").val().trim();
	  if (nome != null) {
		  var reNome = /^[A-Za-z0-9]+$/;
	    if (nome.length <= MAXREGNOME && reNome.exec(nome)) {
	      return true;
	    }
	  }
	  return false;
  };
  
  function validateRegistroDescrizione() {
	  var descrizione = $("#mrdescrizione").val().trim();
	  if (descrizione != null) {
		  var reDescrizione = /^[A-Za-z0-9\s]+$/g;
	    if (descrizione.length <= MAXREGDESCRIZIONE && reDescrizione.exec(descrizione)) {
	      return true;
	    }
	  }
	  return false;
  };
  
  function visualizzaRegistroWrapper() {
	  var hRegistroWrapper = document.getElementById("registro-info-container").hidden;
    if (hRegistroWrapper == true) {
      hRegistroWrapper = false;
      document.getElementById("registro-info-container").hidden = false;
    } else {
      hRegistroWrapper = true;
      document.getElementById("registro-info-container").hidden = true;
      document.getElementById("modifica-registro-wrapper").hidden = true;
    }
  };
  
  function visualizzaModificaRegistroWrapper() {
	  var hidden = document.getElementById("modifica-registro-wrapper").hidden;
    if (document.getElementById("registro-info-container").hidden == false &&
      hidden == true) {
      hidden = false;
      document.getElementById("modifica-registro-wrapper").hidden = false;
    } else {
      hidden = true;
      document.getElementById("modifica-registro-wrapper").hidden = true;
    }
  };
  
  function modificaRegistro() {
    var hidden = document.getElementById("modifica-registro-wrapper").hidden;
    if (hidden == true) {
    	visualizzaModificaRegistroWrapper();
    	return false;
    } else {
    	visualizzaModificaRegistroWrapper();
    }
    
    var oldId = document.getElementById("registro-info-id").innerHTML.trim();
    var id = $("#mrid").val().trim();
    var nome = $("#mrnome").val().trim();
    var descrizione = $("#mrdescrizione").val().trim();
    if (!(validateRegistroNome() &&
        validateRegistroID() &&
        validateRegistroDescrizione())) {
    	console.log("oldID: " + oldId);
    	console.log("id: " + id);
    	console.log("nome: " + nome);
    	console.log("descrizione: " + descrizione);
    	console.log("Nome, Descrizione o ID errati nella modifica del registro");
    	return false;
    }
    
    $.ajax({
      type: "POST",
      url: "ModificaRegistroTirocinioStudenteServlet",
   	  contentType: "application/x-www-form-urlencoded",
   	  datatype: "json",
      data: "id="+id+"&nome="+nome+"&descrizione="+descrizione+"&old_id="+oldId,
      success: function(data) {
        if (data.modifica == true) {
        	console.log("Modifica Avvenuta.");
          document.getElementById("registro-info-id").innerHTML = id;
          document.getElementById("registro-info-nome").innerHTML = nome;
          document.getElementById("registro-info-descrizione").innerHTML = descrizione;
          document.getElementById("modifica-registro-wrapper").hidden = true;
        } else {
        	console.log("Modifica Non Avvenuta.");
        }
      },
      error: function(error) {
        console.log("Errore:"+ error);
      }
    })
  };

  function consegnaRegistro() {
    var id = document.getElementById("registro-info-id").innerHTML.trim();

    $.ajax({
      type : "POST",
      url : "ConsegnaRegistroServlet",
      contentType : "application/x-www-form-urlencoded",
      datatype : "json",
      data : "id="+id,
      success : function (data) {
        if (data.consegna == true) {
          console.log("Consegna Effettuata.");
          document.getElementById("tf-consegna").src = "images/true.png";
        } else {
          console.log("Consegna Non Effettuata.");
          document.getElementById("tf-consegna").src = "images/false.png";
        }
      },
      error : function(error) {
        console.log("Errore:"+ error);
      }
    })
  };
  
  if (typeof String.trim == "undefined") {
	  String.prototype.trim = function() {
		  return this.replace(/(^\s*)|(\s*$)/g, "");
	  }
  }
</script>



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
<section id="registro-tirocinio" >
    <div id="wrap-registro-tirocinio" align="center" >
    	<div id="registro-heading" align="center" style="padding:50px;">
    		<h3 style="padding: 35px; font-size: 24px; color: black;">Registro Del Tirocinio</h3>
    	    <img id="registro-heading-icon" src="images/register-icon.png"/>
    	</div>
        <div id="reg-tirocinio">
            <div id="wrap-reg-tirocinio">
            	<%
			      AbstractBean utenteRegistro = (AbstractBean) session.getAttribute("SessionUser");
            	  RegistroBean registroBean = null;
            	  List<AbstractBean> attivitaTirocinioRegistro = null;
            	  RegistroModelDM registroModelDM = (RegistroModelDM) getServletContext().getAttribute("ContextRegistroModelDM");
            	  AttivitaTirocinioModelDM attivitaTirocinioModelDM = (AttivitaTirocinioModelDM) getServletContext().getAttribute("ContextAttivitaModelDM");
            	  StrutturaOspitanteModelDM strutturaOspitanteModelDM = (StrutturaOspitanteModelDM) getServletContext().getAttribute("ContextStrutturaOspitanteModelDM");
            	  StudenteBean studenteRegistro = null;
            	  
            	  if (registroModelDM == null) {
            	    registroModelDM = new RegistroModelDM();
            	    session.setAttribute("SessionRegistroModelDM", registroModelDM);
            	  }
            	  
            	  if (attivitaTirocinioModelDM == null) {
            	    attivitaTirocinioModelDM = new AttivitaTirocinioModelDM();
            	    session.setAttribute("SessionAttivitaTirocinioModelDM", attivitaTirocinioModelDM);
            	  }
            	  
            	  if (strutturaOspitanteModelDM == null) {
            	    strutturaOspitanteModelDM = new StrutturaOspitanteModelDM();
            	    session.setAttribute("SessionStrutturaOspitanteModelDM", strutturaOspitanteModelDM);
            	  }
			      
            	  if (registroBean == null) {
			        if (utenteRegistro != null) {
			          if (utenteRegistro instanceof StudenteBean) {
			            studenteRegistro = (StudenteBean) utenteRegistro;
			            try {
			              registroBean = (RegistroBean) registroModelDM.doRetrieveByStudente(studenteRegistro.getID());
			            } catch (SQLException e) {
			              Logger.getGlobal().log(Level.SEVERE, e.getMessage());
			              //redirect to an [error] page
			            }
			          } else {
			            //redirect to an [error] page
			          }
			        } else {
			          //redirect to an [login] page
			          RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
			          view.forward(request, response);
			        }
            	  }
            	  
            	  if (registroBean != null) {
           	        try {
           	          attivitaTirocinioRegistro = (List<AbstractBean>) attivitaTirocinioModelDM.doRetrieveByRegistro(registroBean.getID());
           	        } catch (SQLException e) {
                      Logger.getGlobal().log(Level.SEVERE, e.getMessage());
                      //redirect to an [error] page
           	        }
            	  } else {
            	    //redirect to an [error] page
            	  }
            	%>
                <div id="reg-info-wrapper" class="wrapper" >
                	<p id="reg-info" class="info">Nome: </p><b id="reg-info-nome" class="info" name="nome" ><%= registroBean.getNome() %></b>
                </div>
                <div id="registro-info" hidden >
                  <div id="reg-info-wrapper" class="wrapper" >
	            	<p id="reg-info">ID: </p><b id="reg-info-id" class="info" name="id" ><b id="rid"><%= registroBean.getID() %></b></b>
            	  </div>
                  <div id="reg-info-wrapper" class="wrapper" >
                  	<p id="reg-info" class="info">Descrizione: </p><b id="reg-info-descrizione" class="info" name="descrizione" ><%= registroBean.getDescrizione() %></b>
                  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
              		<p id="reg-info" class="info" name="consegna" >Consegna:   </p>
              		<b id="reg-info-consegna" class="info" >
	           		<%
	           			if (registroBean.getConsegna()) {
	           		%>
	           			  <%= "Il Registro Delle Attività Risulta Consegnato".toString() %>
	           			  <p><img id="consegna-icon" src="images/supplemento.jpg"/></p>
	           		<%
	           			} else {
	           		%>
	           			  <%= "Il Registro Delle Attività Non Risulta Consegnato".toString() %>
	           		<%
	           			}
	           		%>
	           		</b>
            	  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
            		<p id="reg-info" class="info" name="confermaAcc" >Conferma Del Tutor Accademico: </p>
            		<b id="reg-info" class="info">
	           		<%
	           			if (registroBean.getConfermaTutorAcc()) {
	           	    %>
	           				<%= "Confermato Dal Tutor Accademico".toString() %>
	           				<p><img id="true-false-icon" src="images/true.png"/></p>
	           		<%
	           			} else {
	           		%>
	           				<%= "Non Confermato Dal Tutor Accademico".toString() %>
	           				<p><img id="true-false-icon" src="images/false.png"/></p>
	           		<%
	           			}
	           		%>
	           		</b>
            	  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
            		<p id="reg-info" class="info" name="confermaAz">Conferma Del Tutor Aziendale: </p>
            		<b id="reg-info" class="info">
	           		<%
	           			if (registroBean.getConfermaTutorAz()) {
	           		%>
	           				<%= "Confermato Dal Tutor Aziendale".toString() %>
	           				<p><img id="true-false-icon" src="images/true.png"/></p>
	           		<%
	           			} else {
	           		%>
	           				<%= "Non Confermato Dal Tutor Aziendale".toString() %>
	           				<p><img id="true-false-icon" src="images/false.png"/></p>
	           		<%
	           			}
	           		%>
	           		</b>
            	  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
            	    <p id="reg-info" class="info" name="confermaUff">Conferma Dell' Ufficio: </p>
            	    <b id="reg-info" class="info">
            	    <%
            	        if (registroBean.getConfermaUff()) {
            	    %>
            	            <%= "Confermato Dall' Ufficio".toString() %>
            	            <p><img id="true-false-icon" src="images/true.png"/></p>
            	    <%
            	        } else {
            	    %>
            	            <%= "Non Confermato Dall' Ufficio".toString() %>
            	            <p><img id="true-false-icon" src="images/false.png"/></p>
            	    <%
            	        }
            	    %>
            	    </b>
            	  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
            	    <p id="reg-info" class="info" name="dataCreazione">Data Creazione Del Registro: </p>
            	    <b id="reg-info-data-creazione" class="info">
            	      <%= registroBean.getPrimaIstituzione() %>
            	    </b>
            	  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
            	    <p id="reg-info" class="info" name="ultimoAggiornamento">Data Di Ultimo Aggiornamento: </p>
            	    <b id="reg-info-ultimo-aggiornamento" class="info">
            	      <%= registroBean.getUltimoAgg().toString() %>
            	    </b>
            	  </div>
                </div>
            </div>
            <!--<div id="ts-input-reg-wrapper">
                <a href="#reg-tirocinio" style="text-decoration: none;">
                  <input id="button" class="ts-button" name="vis-reg-tirocinio"
                      type="button" value="Visualizza" onclick="visualizzaRegistro()" />
                </a>
                <a href="#modifica-registro-wrapper" style="text-decoration: none;">
                  <input id="button" class="ts-button" name="modifica-reg-tirocinio"
                      type="button" value="Modifica" onclick="modificaRegistro()"/>
                </a>
                <input id="button" class="ts-button" name="consegna-reg-tirocinio"
                    type="button" value="Consegna" onclick="consegnaRegistro()" />
            </div>
            -->
            <div id="ts-input-reg-wrapper">
              <input id="button" class="ts-button" name="vis-reg-tirocinio"
                  type="button" value="Visualizza" onclick="visualizzaRegistro()" />
              <input id="button" class="ts-button" name="modifica-reg-tirocinio"
                  type="button" value="Modifica" onclick="modificaRegistro()"/>
              <input id="button" class="ts-button" name="consegna-reg-tirocinio"
                  type="button" value="Consegna" onclick="consegnaRegistro()" />
            </div>
            <div id="modifica-registro-wrapper" hidden>
              <p id="modifica-registro-info">Nome: </p>
              <input id="mrnome" type="text" placeholder="Nome:" />
              <p id="modifica-registro-info">ID: </p>
              <input id="mrid" type="text" placeholder="ID:" />
              <p id="modifica-registro-info">Descrizione: </p>
              <input id="mrdescrizione" type="text" placeholder="Descrizione:" />
            </div>
            
            <div id="reg-attivita-wrapper" class="wrapper" >
              <!-- tabella del registro delle attivita con il nome della struttura ospitante in cui si svolgera l' attivita -->
              <%
                if (attivitaTirocinioRegistro == null) {
              %>
                  <p style="color: black; padding-bottom: 60px;">Nessuna Attivita Trovata Per Il Registro</p>
              <%  
                } else {
                  if (attivitaTirocinioRegistro.isEmpty()) {
                  %>
                    <p style="color: black; padding-bottom: 60px;">Nessuna Attivita Trovata Per Il Registro</p>
                  <%
                  } else {
                  %>
                    <p  style="color: black; padding-bottom: 60px;">Attivita Svolte Del Tirocinio</p>
                  <%  
                  }
                }
              %>
              <%
                if (attivitaTirocinioRegistro != null) {
                  if (!attivitaTirocinioRegistro.isEmpty()) {
              %>
		              <table id="reg-attivita">
		                <thead id="reg-attivita-thead">
		                  <tr id="reg-attivita-row">
		                    <th> </th>
		                    <th id="reg-attivita-head" style="background-color: #3AD1D3;">ID</th>
		                    <th id="reg-attivita-head" style="background-color: #5BE599;">Nome Registro</th>
		                    <th id="reg-attivita-head" style="background-color: #D5BE59;">Struttura Ospitante</th>
		                    <th id="reg-attivita-head" style="background-color: #E4E55B;">Descrizione</th>
		                    <th id="reg-attivita-head" style="background-color: #92DB50;">Data</th>
		                    <th id="reg-attivita-head" style="background-color: #E06969;">Ore</th>
		                  </tr>
		                </thead>
		                <tbody id="reg-attivita-tbody">
		                <%
		                  int rowCounter = 1;
		                
		                  if (attivitaTirocinioRegistro != null) {
		                    for (AbstractBean product: attivitaTirocinioRegistro) {
		                      AttivitaTirocinioBean attivitaTirocinioBean = (AttivitaTirocinioBean) product;
		                      StrutturaOspitanteBean strutturaOspitanteBean = (StrutturaOspitanteBean) strutturaOspitanteModelDM.doRetrieveByAttivitaTirocinio(attivitaTirocinioBean.getID());
		                      if (attivitaTirocinioBean != null) {
		                %>
		                        <tr id="reg-attivita-row">
		                          <th id="reg-attivita-row-counter"> <%= rowCounter++ %> </th>
		                          <td id="reg-attivita-data"> <%= attivitaTirocinioBean.getID() %> </td>
		                          <td id="reg-attivita-data"> <%= registroBean.getNome() %> </td>
		                          <td id="reg-attivita-data"> <%= strutturaOspitanteBean.getNome() %> </td>
		                          <td id="reg-attivita-data"> <%= attivitaTirocinioBean.getDescrizione() %> </td>
		                          <td id="reg-attivita-data"> <%= attivitaTirocinioBean.getData() %> </td>
		                          <td id="reg-attivita-data"> <%= attivitaTirocinioBean.getOre() %> </td>
		                        </tr>
		                <%
		                      }
		                    }
		                  } else {
		                    Logger.getGlobal().log(Level.INFO, "Nessuna attivita di tirocinio trovata");
		                  }
		                %>
		                </tbody>
		              </table>
	          <%
                  }
                }
	          %>
            </div>
        </div>
    </div>
    <script type="text/javascript">
      function visualizzaRegistro(){
        var h = document.getElementById("registro-info").hidden;
        
        if(h == true){
          document.getElementById("registro-info").hidden = false;
        }else{
          document.getElementById("registro-info").hidden = true;
          document.getElementById("modifica-registro-wrapper").hidden = true;
        }
      }
      
      function modificaRegistro(){
        var h = document.getElementById("registro-info").hidden;
        
        if(h == false){
          document.getElementById("modifica-registro-wrapper").hidden = false;
          console.log()
        }else{
          var oldId = $('rid').val();
          var id = $('#mrid').val();
          var nome = $('#mrnome').val();
          var descrizione = $('#mrdescrizione').val();
          
          console.log("Effettuando modifica del registro");
          
          $.ajax({
              type : "POST",
              url : "ModificaRegistroTirocinioStudenteServlet",
              contentType: "application/x-www-form-urlencoded",
              datatype : "json", 
              data: "id="+id+"&nome="+nome+"&descrizione="+descrizione+"&old_id="+oldId,
              success: function(data) {
    	        var json = data;
    	        var modifica = json.modifica;
    	        
    	        if (modifica) {
                  document.getElementById("reg-info-id").innerHTML = id;
                  document.getElementById("reg-info-nome").innerHTML = nome;
                  document.getElementById("reg-info-descrizione").innerHTML = descrizione;
                  document.getElementById("modifica-registro-wrapper").hidden = true;
                }
              },
              error: function(error) {
                console.log("Errore:"+ error);
              }
          })
        }
      }
      
      function consegnaRegistro(){
    	var id = document.getElementById("rid").innerHTML;
    	
    	$.ajax({
    	  type : "POST",
    	  url : "ConsegnaRegistroTirocinioServlet",
    	  contentType : "application/x-www-form-urlencoded",
    	  datatype : "json",
    	  data : "id="+id,
    	  success : function(data){
            var json = data;
            var consegna = json.consegna;
            
            if(consegna){
              console.log("Consegna effettuata");
              document.getElementById("reg-info-consegna").innerHTML = "Il Registro Delle Attività Risulta Consegnato" + "<p><img id=\"consegna-icon\" src=\"images/supplemento.jpg\"/></p>";
            }else{
              console.log("Consegna non effettuata");
              document.getElementById("reg-info-consegna").innerHTML = "Il Registro Delle Attività Non Risulta Consegnato";
			}
          },
          error : function(error){
            console.log("Errore:"+ error);
          }
        })
      }
    </script>
</section>
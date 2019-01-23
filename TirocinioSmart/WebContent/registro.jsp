<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isThreadSafe="false"
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.StudenteBean"
    import="java.sql.SQLException"
    import="it.unisa.model.RegistroModelDM"
    import="java.util.logging.Logger"
    import="java.util.logging.Level"%>
<section id="registro-tirocinio" >
    <div id="wrap-registro-tirocinio" align="center" >
    	<div align="center" style="padding-top:50px;">
    		<h3 style="color: black;">Registro Del Tirocinio</h3>
    	</div>
        <div id="reg-tirocinio">
            <div id="wrap-reg-tirocinio">
            	<%
			      Object utenteRegistro = session.getAttribute("SessionUser");
            	  RegistroBean registro = (RegistroBean) session.getAttribute("SessionRegistro");  
            	  StudenteBean studenteRegistro = null;
			      
            	  if (registro == null) {
			        if (utenteRegistro != null) {
			          if (utenteRegistro instanceof StudenteBean) {
			            studenteRegistro = (StudenteBean) utenteRegistro;
			            try {
			              registro = (RegistroBean) RegistroModelDM.loadRegistroStudente(studenteRegistro);
  			              session.setAttribute("SessionRegistro", registro);
			            } catch(SQLException e) {
			              Logger.getGlobal().log(Level.SEVERE, e.getMessage());
			            }
			          }
			        }
            	  }
            	%>
                <div id="reg-info-wrapper" class="wrapper" >
                	<p id="reg-info" class="info" name="nome" >Nome: <%= registro.getNome() %></p>
                    <input id="mrnome" type="text" placeholder="Nome:" hidden />
                </div>
                <div id="registro-info" hidden >
                  <div id="reg-info-wrapper" class="wrapper" >
	            	<p id="reg-info" class="info" name="id" >ID: <b id="rid"><%= registro.getId() %></b></p>
	            	<input id="mrid" type="text" placeholder="ID:" hidden />
            	  </div>
                  <div id="reg-info-wrapper" class="wrapper" >
                  	<p id="reg-info" class="info" name="descrizione" >Descrizione: <%= registro.getDescrizione() %></p>
                  	<input id="mrdescrizione" type="text" placeholder="Descrizione:" hidden />
                  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
              		<b id="reg-info" class="info" name="consegna" >Consegna:   </b>
	           		<%
	           			if (registro.getConsegna()) {
	           		%>
	           				<b id="reg-info" class="info" >Il Registro Delle Attività Risulta Consegnato</b>
	           		<%
	           			} else {
	           		%>
	           				<b id="reg-info" class="info">Il Registro Delle Attività Non Risulta Consegnato</b>
	           		<%
	           			}
	           		%>
            	  </div>
            	  <div class="reg-info-wrapper" >
            		<b id="reg-info" class="info" name="confermaAcc" >Conferma Del Tutor Accademico:   </b>
	           		<%
	           			if (registro.getConfermaTutorAcc()) {
	           	    %>
	           				<b id="reg-info" class="info">Confermato Dal Tutor Accademico</b>
	           		<%
	           			} else {
	           		%>
	           				<b id="reg-info" class="info">Non Confermato Dal Tutor Accademico</b>
	           		<%
	           			}
	           		%>
            	  </div>
            	  <div id="reg-info-wrapper" class="wrapper" >
            		<b id="reg-info" class="info" name="confermaAz">Conferma Del Tutor Aziendale:   </b>
	           		<%
	           			if (registro.getConfermaTutorAz()) {
	           		%>
	           				<b id="reg-info" class="info">Confermato Dal Tutor Aziendale</b>
	           		<%
	           			} else {
	           		%>
	           				<b id="reg-info" class="info">Non Confermato Dal Tutor Aziendale</b>
	           		<%
	           			}
	           		%> 
            	  </div>
                </div>
            </div>
            <label id="wrap-ts-input-reg">
                <input class="ts-button" name="vis-reg-tirocinio"
                    type="button" value="Visualizza" onclick="visualizzaRegistro()" />
                <input class="ts-button" name="modifica-reg-tirocinio"
                    type="button" value="Modifica" onclick="modificaRegistro()" />
                <input class="ts-button" name="consegna-reg-tirocinio"
                    type="button" value="Consegna" onclick="consegnaRegistro()" />
            </label>
        </div>
    </div>
    <script type="text/javascript">
      function visualizzaRegistro() {
    	  var h = document.getElementById("registro-info").hidden;
    	  
    	  if (h == true) {
    	    document.getElementById("registro-info").hidden = false;
    	  } else {
    		  document.getElementById("registro-info").hidden = true;
    	  }
      }
      
      function modificaRegistro() {
    	  var h = document.getElementById("registro-info").hidden;
    	  
    	  if (h == false) {
    		  var hm = document.getElementById("mrnome").hidden;
    		  if (hm == true) {
    		      document.getElementById("mrnome").hidden = false;
    		      document.getElementById("mrid").hidden = false;
    		      document.getElementById("mrdescrizione").hidden = false;
    		  } else {
    			  var id = $('#mrid').val();
    	    	  var nome = $('#mrnome').val();
    	    	  var descrizione = $('#mrdescrizione').val();
    	    	  var str = "id:" + id + ", nome:" + nome + ", descrizione:" + descrizione;
    	    	  $.ajax({
    	    			type : "POST",
    	    			url : "ModificaRegistroTirocinioStudenteServlet",
    	    			contentType: "application/x-www-form-urlencoded",
    	    			datatype : "json", 
    	    			data: "id="+id+"&nome="+nome+"&descrizione="+descrizione,
    	    			success: function(data){
    	    				var json = data;
    	    				var mod = json.modifica;
    	    				
    	    				if (mod) {
    	    					alert("Modifica effettuata, occorre aggiornare la pagina per rendere visibili gli effetti");
    	    				} else {
    	    					alert("Modifica non effettuata");
    	    				}
    	    			},
    	    			error: function(error){
    	    				console.log("Errore:"+ error);
    	    			}
    	          })
    	    	  document.getElementById("mrnome").hidden = true;
      		      document.getElementById("mrid").hidden = true;
      		      document.getElementById("mrdescrizione").hidden = true;
    		  }
    	  }
      }
      
      function consegnaRegistro() {
    	  var id = document.getElementById("rid").innerHTML;
    	  
    	  $.ajax({
  			type : "POST",
  			url : "ConsegnaRegistroTirocinioServlet",
  			contentType: "application/x-www-form-urlencoded",
  			datatype : "json",
  			data: "id="+id,
  			success: function(data){
  				var json = data;
  				var con = json.consegna;
  				
  				if (consegna) {
  					alert("Consegna effettuata");
  				} else {
  					alert("Consegna non effettuata");
  				}
  			},
  			error: function(error){
  				console.log("Errore:"+ error);
  			}
        })
      }
    </script>
</section>
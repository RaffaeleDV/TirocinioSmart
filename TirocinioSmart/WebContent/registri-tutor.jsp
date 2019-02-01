<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.*" 
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.RegistroModelDM" 
    import="it.unisa.model.TutorBean"
    import="java.sql.SQLException"
    import="java.util.logging.Logger"
    import="java.util.logging.Level" %>
<section id="nomi-registri-wrapper" class="wrapper">
  <h1 id="registri-heading" class="info">Registri</h1>
  <div id="scelta-registro-wrapper" class="wrapper">
    <input id="id-reg-input" type="text" placeholder="Inserire ID Del Registro Scelto" />
    <input id="button-registro" type="button" onclick="vaiRegistro()" value="Vai Al Registro" />
  </div>
  <%
    TutorBean tutor = null;
    Object userRegistriTutor = session.getAttribute("SessionUser");
  	List<RegistroBean> regs = (ArrayList<RegistroBean>) session.getAttribute("SessionRegistriTutor");
    
  	if (userRegistriTutor != null) {
  	  if (userRegistriTutor instanceof TutorBean) {
  	    tutor = (TutorBean) userRegistriTutor;
  	  } else {
  	    //redirect to an [error] page
  	  }
  	} else {
  	  //redirect to an [login] page
  	}
  	
    if (regs == null) {
      if (tutor.getTipo().equals("Aziendale")) {
        try {
          regs = RegistroModelDM.loadRegistriTutorAz(tutor.getId());
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
      } else if (tutor.getTipo().equals("Accademico")) {
        try {
          regs = RegistroModelDM.loadRegistriTutorAcc(tutor.getId());
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
      }
      
      session.setAttribute("SessionRegistriTutor", regs);
  	}
  
    if (regs != null) {
      for (int i = 0; i < regs.size(); i++) {
        RegistroBean registroBean = regs.get(i);
        if (registroBean != null){
  %>
          <div id="nome-registro-wrapper" class="wrapper">
            <p id="nome-registro-info" class="info"> ID Registro: <b id="id-reg"><%= registroBean.getId() %></b>
            <p id="nome-registro-info" class="info"> Nome Registro: <b id="nome-reg"><%= registroBean.getNome() %></b></p>
          </div>
  <%
        }
      }
    } else {
      //redirect to an [error] page
    }
  %>
  
  <script type="text/javascript">
    function vaiRegistro() {
      var idReg = $('#id-reg-input').val();
      
      $.ajax({
        type : "POST",
        url : "RegistroTutorServlet",
        contentType: "application/x-www-form-urlencoded",
        datatype : "json",
        data: "id="+idReg,
        success: function(data) {
          console.log("richiesta del registro con id: " + idReg + " effettuata ");
        },
        error: function(error) {
          console.log("Errore:"+ error);
        }
      })
    }
  </script>
</section>
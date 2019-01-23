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
  <%
    TutorBean tutor = null;
    Object userRegistriTutor = session.getAttribute("SessionUser");
  	List<RegistroBean> regs = (ArrayList<RegistroBean>) session.getAttribute("SessionRegistriTutor");
    
  	if (userRegistriTutor instanceof TutorBean) {
  	  tutor = (TutorBean) userRegistriTutor;
  	}
  	
  	
    if (regs == null) {
      if (tutor != null) {
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
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
  	    view.forward(request, response);
      }
  	}
  
    if (regs != null) {
      for (int i = 0; i < regs.size(); i++) {
        RegistroBean registroBean = regs.get(i);
        if (registroBean != null){
  %>
          <div id="nome-registro-wrapper" class="wrapper">
            <p id="id-registro-info" class="info" hidden> ID Registro: <b id="id-reg"><%= registroBean.getId() %></b>
            <p id="nome-registro-info" class="info"> Nome Registro: <b id="nome-reg"><%= registroBean.getNome() %></b></p>
          </div>
  <%
        }
      }
    } else {
      //redirect to an [error] page
    }
  %>
  <!-- 
  <script type="text/javascript">
    function vaiRegistro() {
      var idReg = document.getElementById("id-reg").innerHTML;
      var nomeReg = document.getElementById("nome-reg").innerHTML;
      
      $.ajax({
        type : "POST",
        url : "RegistroServlet",
        contentType: "application/x-www-form-urlencoded",
        datatype : "json", 
        data: "id="+idReg+"&nome="+nomeReg,
        success: function(data){
          console.log("richiesta di registro per nome effettuata");
        },
        error: function(error){
          console.log("Errore:"+ error);
        }
      })
    }
  </script>
  -->
</section>
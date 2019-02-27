<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*" 
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.RegistroModelDM" 
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.AbstractBean"
    import="java.sql.SQLException"
    import="java.util.logging.Logger"
    import="java.util.logging.Level" %>
<section id="nomi-registri-wrapper" class="wrapper">
  <h3 style="font-weight: 200; padding: 35px; font-size: 28px; color: black;" align="center">Registri</h3>
  <!--
  <div id="scelta-registro-wrapper" class="wrapper">
    <input id="search-input" type="text" placeholder="Inserire ID Del Registro Scelto" />
    <input id="button" type="button" onclick="vaiRegistro()" value="Vai Al Registro" />
  </div>
  -->
  <%
    TutorBean tutorBean = null;
    AbstractBean userRegistriTutor = (AbstractBean) session.getAttribute("SessionUser");
  	List<AbstractBean> regs = null;
    RegistroModelDM registroModelDM = (RegistroModelDM) getServletContext().getAttribute("SessionRegistroModelDM");
    if (registroModelDM == null) {
      registroModelDM = new RegistroModelDM();
      getServletContext().setAttribute("SessionRegistroModelDM", registroModelDM);
    }
    
  	if (userRegistriTutor != null) {
  	  if (userRegistriTutor instanceof TutorBean) {
  	    tutorBean = (TutorBean) userRegistriTutor;
  	  } else {
  	    Logger.getGlobal().log(Level.INFO, "E' necessario il login del tutor per accedere ai suoi registri");
  	    RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
  	    view.forward(request, response);
  	  }
  	} else {
  	  Logger.getGlobal().log(Level.INFO, "E' necessario il login del tutor per accedere ai suoi registri");  
  	  RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
  	  view.forward(request, response);
  	}
  	
    if (tutorBean != null) {
      if (tutorBean.getTipo().equals("Aziendale")) {
        try {
          regs = (List<AbstractBean>) registroModelDM.doRetrieveByTutorAz(tutorBean.getID());
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else if (tutorBean.getTipo().equals("Accademico")) {
        try {
          regs = (List<AbstractBean>) registroModelDM.doRetrieveByTutorAcc(tutorBean.getID());
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else {
        //redirect to an [error] page
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }

    if (regs != null) {
      for (AbstractBean product: regs) {
        RegistroBean registroBean = null;
        if (product instanceof RegistroBean) {
          registroBean = (RegistroBean) product;
        } else {
          Logger.getGlobal().log(Level.INFO, "Prodotto che non risulta un registro");
          //redirect to an [error] page
        }
        
        if (registroBean != null){
  %>
          <div id="nome-registro-wrapper" class="wrapper">
            <div>
              <img id="registro-icon" src="images/registro-icon.png"/>
            </div>
            <div>
              <p> </p>
            </div>
            <div>
              <p id="nome-registro-info" class="info"> ID Registro: <b id="id-reg"><%= registroBean.getID() %></b></p>
              <p id="nome-registro-info" class="info"> Nome Registro: <b id="nome-reg"><%= registroBean.getNome() %></b></p>
            </div>
            <div>
            
            </div>
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
        
        },
        error: function(error) {
          console.log("Errore:"+ error);
        }
      })
    }
  </script>
</section>
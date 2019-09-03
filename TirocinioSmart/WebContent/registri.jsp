<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*" 
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.RegistroModelDM" 
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.AbstractBean"
    import="it.unisa.model.UfficioBean"
    import="java.sql.SQLException"
    import="java.util.logging.Logger"
    import="java.util.logging.Level" %>
<section id="registri-items-wrapper" class="wrapper">
  <h3 style="border-radius: 10px; margin: 100px; background-color: #05003F; border: 1px #ffffff solid; font-weight: xx-large; padding: 15px; font-size: 28px; color: white;" align="center">Registri</h3>
  <%
    TutorBean tutorBean = null;
    UfficioBean ufficioBean = null;
    AbstractBean userRegistri = (AbstractBean) session.getAttribute("SessionUser");
  	List<AbstractBean> regs = null;
    
  	if (userRegistri != null) {
  	  if (userRegistri instanceof TutorBean) {
  	    tutorBean = (TutorBean) userRegistri;
  	    if (tutorBean.getTipo().equals("Aziendale")) {
          try {
            regs = (List<AbstractBean>) RegistroModelDM.INSTANCE.doRetrieveByTutorAz(tutorBean.getID());
          } catch(SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
          }
        } else if (tutorBean.getTipo().equals("Accademico")) {
          try {
            regs = (List<AbstractBean>) RegistroModelDM.INSTANCE.doRetrieveByTutorAcc(tutorBean.getID());
          } catch(SQLException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            //redirect to an [error] page
          }
        } else {
          //redirect to an [error] page
        }
  	  } else if (userRegistri instanceof UfficioBean) {
  		  ufficioBean = (UfficioBean) userRegistri;
        try {
        	regs = (List<AbstractBean>) RegistroModelDM.INSTANCE.doRetrieveByUfficio(ufficioBean.getID());
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else {
  	    RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
  	    view.forward(request, response);
  	  }
  	} else {
  	  RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
  	  view.forward(request, response);
  	}

    if (regs != null && regs.size() > 0) {
      for (AbstractBean product: regs) {
        RegistroBean registroBean = null;
        if (product instanceof RegistroBean) {
          registroBean = (RegistroBean) product;
        } else {
          Logger.getGlobal().log(Level.INFO, "Prodotto che non risulta un registro");
          //redirect to an [error] page
        }
        
        if (registroBean != null) {
          %>
          <div id="registro-item-wrapper" class="wrapper">
            <div>
              <img id="registro-item-icon" src="images/registro-icon.png"/>
            </div>
            <div>
              <p id="registro-item-info" class="info">ID: <b id="id-reg"><%= registroBean.getID() %></b></p>
              <p id="registro-item-info" class="info">Nome: <b id="nome-reg"><%= registroBean.getNome() %></b></p>
            </div>
            <div id="registro-item-link" onclick="vaiRegistro($('#id-reg')).val()">
              
            </div>
          </div>
          <%
        }
      }
    } else {
    	%>
    	<div id="warning-wrapper">
    	  <h2>Nessun Regisrto Trovato</h2>
    	</div>
      <%
    }
  %>
</section>
<script type="text/javascript">
  function vaiRegistro(a) {
    console.log("ID Registro: " + a.toString());
    
    $.ajax({
      type : "POST",
      url : "RegistroServlet",
      contentType: "application/x-www-form-urlencoded",
      datatype : "json",
      data: "id="+a,
      success: function(data) {
      
      },
      error: function(error) {
        console.log("Errore:"+ error);
      }
    })
  };
</script>
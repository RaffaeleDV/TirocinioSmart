<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*" 
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.RegistroModelDM" 
    import="it.unisa.model.UfficioBean"
    import="it.unisa.model.AbstractBean"
    import="java.sql.SQLException"
    import="java.util.logging.Logger"
    import="java.util.logging.Level" %>
<section id="nomi-registri-wrapper">
  <h3 style="font-weight: 200; padding: 35px; font-size: 28px; color: black;" align="center">Registri</h3>
  <!--
  <div id="scelta-registro-wrapper" class="wrapper">
    <input id="search-input" type="text" placeholder="Inserire ID Del Registro Scelto" />
    <input id="button" type="button" onclick="vaiRegistro()" value="Vai Al Registro" />
  </div>
  -->
  <%
    UfficioBean ufficioBean = null;
    Object userRegistriUfficio = session.getAttribute("SessionUser");
    List<AbstractBean> regs = null;
    RegistroModelDM registroModelDM = (RegistroModelDM) session.getAttribute("SessionRegistroModelDM");
    if (registroModelDM == null) {
      registroModelDM = new RegistroModelDM();
      session.setAttribute("SessionRegistroModelDM", registroModelDM);
    }
    
    if (userRegistriUfficio != null) {
      if (userRegistriUfficio instanceof UfficioBean) {
        ufficioBean = (UfficioBean) userRegistriUfficio;
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    
    if (ufficioBean != null) {
      try {
        regs = (List<AbstractBean>) registroModelDM.doRetrieveByUfficio(ufficioBean.getID());
      } catch(SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
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
          Logger.getGlobal().log(Level.INFO, "Oggetto non del tipo di RegistroBean tra i registri");
          //redirect to an [error] page
        }
  %>
        <div id="nome-registro-wrapper" class="wrapper">
          <div>
            <img id="registro-icon" src="images/registro-icon.png"/>
          </div>
          <div>
            <p id="nome-registro-info" class="info"> ID Registro: <b id="id-reg"><%= registroBean.getID() %></b>
            <p id="nome-registro-info" class="info"> Nome Registro: <b id="nome-reg" class="selector"><%= registroBean.getNome() %></b></p>
          </div>
          <div>
          
          </div>
        </div>
  <%
      }
    }
  %>
  
  <script type="text/javascript">
    function vaiRegistro() {
      var a = $('#button').closest('#id-reg').innerHTML;
      
      alert("" + a);
    	
      var idReg = $('#id-reg-input').val();
      
      $.ajax({
        type : "POST",
        url : "RegistroUfficioServlet",
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
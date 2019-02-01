<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*" 
    import="it.unisa.model.RegistroBean"
    import="it.unisa.model.RegistroModelDM" 
    import="it.unisa.model.UfficioBean"
    import="java.sql.SQLException"
    import="java.util.logging.Logger"
    import="java.util.logging.Level" %>
<section id="nomi-registri-wrapper">
  <h1 id="registri-heading" class="info">Registri</h1>
  <div id="scelta-registro-wrapper" class="wrapper">
    <input id="id-reg-input" type="text" placeholder="Inserire ID Del Registro Scelto" />
    <input id="button-registro" type="button" onclick="vaiRegistro()" value="Vai Al Registro" />
  </div>
  <%
    UfficioBean ufficio = null;
    Object userRegistriUfficio = session.getAttribute("SessionUser");
    List<RegistroBean> regs = (ArrayList<RegistroBean>) session.getAttribute("SessionRegistriUfficio");
    
    if (userRegistriUfficio != null) {
      if (userRegistriUfficio instanceof UfficioBean) {
        ufficio = (UfficioBean) userRegistriUfficio;
      } else {
        //redirect to an [error] page
      }
    } else {
      //redirect to an [login] page
    }
    
    if (regs == null) {
      try {
        regs = RegistroModelDM.loadRegistri();
      } catch(SQLException e) {
        Logger.getGlobal().log(Level.SEVERE, e.getMessage());
      }
    }
    
    if (regs != null) {
      for (RegistroBean registro: regs) {
  %>
        <div id="nome-registro-wrapper" class="wrapper">
          <p id="nome-registro-info" class="info"> ID Registro: <b id="id-reg"><%= registro.getId() %></b>
          <p id="nome-registro-info" class="info"> Nome Registro: <b id="nome-reg"><%= registro.getNome() %></b></p>
        </div>
  <%
      }
    }
  %>
  
  <script type="text/javascript">
    function vaiRegistro() {
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
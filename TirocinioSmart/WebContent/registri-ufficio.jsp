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
  <%
    UfficioBean ufficio = null;
    Object userRegistriUfficio = session.getAttribute("SessionUser");
    List<RegistroBean> regs = (ArrayList<RegistroBean>) session.getAttribute("SessionRegistriUfficio");
    
    if (userRegistriUfficio instanceof UfficioBean) {
      ufficio = (UfficioBean) userRegistriUfficio;
    }
    
    if (regs == null) {
      if (ufficio != null) {
        try {
          regs = RegistroModelDM.loadRegistri();
        } catch(SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
  	    view.forward(request, response);
      }
    }
    
    if (regs != null) {
      for (RegistroBean registro: regs) {
  %>
        <div id="nome-registro-wrapper" class="wrapper">
          <p id="id-registro-info" class="info" hidden> ID Registro: <b id="id-reg"><%= registro.getId() %></b>
          <p id="nome-registro-info" class="info"> Nome Registro: <b id="nome-reg"><%= registro.getNome() %></b></p>
        </div>
  <%
      }
    }
  %>
</section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.AbstractBean"
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.UfficioBean"
    import="it.unisa.model.ProgettoFormativoModelDM"
    import="it.unisa.model.ProgettoFormativoBean"
    import="java.util.logging.Logger"
    import="java.util.logging.Level"
    import="java.util.List"
    import="java.sql.SQLException"
    %>
<section id="progetti-formativi-items-wrapper" class="wrapper">
  <h3 style="border-radius: 10px; margin: 100px; background-color: #05003F; font-weight: xx-large; padding: 15px; font-size: 28px; color: white;" align="center">Progetti Formativi</h3>
  <%
    TutorBean tutorBean = null;
    UfficioBean ufficioBean = null;
    AbstractBean userProgetti = (AbstractBean) session.getAttribute("SessionUser");
    List<AbstractBean> progetti = null;
    
    if (userProgetti != null) {
      if (userProgetti.getClass().getName().equals(TutorBean.class.getName())) {
        tutorBean = (TutorBean) userProgetti;
      } else if (userProgetti.getClass().getName().equals(UfficioBean.class.getName())) {
        ufficioBean = (UfficioBean) userProgetti;
      } else {
        Logger.getGlobal().log(Level.INFO, "E' necessario il login del tutor o dell' ufficio per accedere ai progetti");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    } else {
      Logger.getGlobal().log(Level.INFO, "E' necessario almeno un login per accedere ai progetti");
      RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
      view.forward(request, response);
    }
    
    if (progetti == null) {
      if (tutorBean != null) {
        try {
          progetti = (List<AbstractBean>) ProgettoFormativoModelDM.INSTANCE.doRetrieveByTutor(tutorBean);
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else if (ufficioBean != null) {
        try {
          progetti = (List<AbstractBean>) ProgettoFormativoModelDM.INSTANCE.doRetrieveByUfficio(ufficioBean.getID());
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
    }
    
    if (progetti != null && progetti.size() > 0) {
      for (AbstractBean product: progetti) {
        ProgettoFormativoBean progettoFormativoBean = null;
        if (product.getClass().getName().equals(ProgettoFormativoBean.class.getName())) {
          progettoFormativoBean = (ProgettoFormativoBean) product;
        } else {
          response.sendRedirect(request.getContextPath() + "/500-page.jsp");
        }
        
        if (progettoFormativoBean != null) {
  %>
          <div id="progetto-formativo-item-wrapper" class="wrapper">
            <div>
              <img id="progetto-formativo-item-icon" src="images/progetto-icon.png"/>
            </div>
            <div>
              <p id="progetto-formativo-item-info" class="info">ID: <b id="id-prog"><%= progettoFormativoBean.getID() %></b></p>
            </div>
            <div id="progetto-formativo-item-link" onclick="retrieveProgettoFormativo(<%= progettoFormativoBean.getID() %>);"></div>
          </div>
  <%
        }
      }
    } else {
    	%>
    	<div id="warning-wrapper">
    	  <h2>Nessun Progetto Formativo Trovato</h2>
    	</div>
      <%
    }
  %>
</section>
<script type="text/javascript">
  function retrieveProgettoFormativo(id) {
    $.ajax({
      type : "POST",
      url : "ProgettoFormativoServlet",
      contenteType : "application/x-www-form-urlencoded",
      datatype : "json",
      data: "id="+id,
      success: function(data) {
      
      },
      error: function(error) {
      
      }
    })
  };
</script>
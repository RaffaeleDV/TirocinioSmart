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
<section id="id-progetti-wrapper" class="wrapper">
  <h3 style="font-weight: 200; padding: 35px; font-size: 28px; color: black;" align="center">Progetti</h3>
  <!--
  <div id="scelta-progetto-wrapper" class="wrapper">
    <input id="search-input" type="text" placeholder="Inserire ID Del Progetto Formativo Scelto"/>
    <input id="button" type="button" onclick="vaiProgetto()" value="Vai Al Progetto"/>
  </div>
  -->
  <%
    TutorBean tutorBean = null;
    UfficioBean ufficioBean = null;
    AbstractBean userProgetti = (AbstractBean) session.getAttribute("SessionUser");
    List<AbstractBean> progetti = null;
    ProgettoFormativoModelDM progettoFormativoModelDM = (ProgettoFormativoModelDM) getServletContext().getAttribute("SessionProgettoFormativoModelDM");
    if (progettoFormativoModelDM == null) {
      progettoFormativoModelDM = new ProgettoFormativoModelDM();
      getServletContext().setAttribute("SessionProgettoFormativoModelDM", progettoFormativoModelDM);
    }
    
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
          progetti = (List<AbstractBean>) progettoFormativoModelDM.doRetrieveByTutor(tutorBean.getID());
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else if (ufficioBean != null) {
        try {
          progetti = (List<AbstractBean>) progettoFormativoModelDM.doRetrieveByUfficio(ufficioBean.getID());
        } catch (SQLException e) {
          Logger.getGlobal().log(Level.SEVERE, e.getMessage());
          //redirect to an [error] page
        }
      } else {
        Logger.getGlobal().log(Level.INFO, "Nessun login effettuato per accedere ai progetti");
        //redirect to an [error] page
      }
    }
    
    if (progetti != null) {
      for (AbstractBean product: progetti) {
        ProgettoFormativoBean progettoFormativoBean = null;
        if (product instanceof ProgettoFormativoBean) {
          progettoFormativoBean = (ProgettoFormativoBean) product;
        } else {
          Logger.getGlobal().log(Level.INFO, "Prodotto che non risulta un progetto");
          //redirect to an [error] page
        }
        
        if (progettoFormativoBean != null) {
  %>
          <div id="id-progetto-wrapper" class="wrapper">
            <div>
              <img id="progetto-icon" src="images/progetto-icon.png"/>
            </div>
            <div>
              <p> </p>
            </div>
            <div>
              <p id="id-progetto-info" class="info"> ID Progetto Formativo: <b id="id-prog"><%= progettoFormativoBean.getID() %></b></p>
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
    function vaiProgetto() {
      var idProg = $('#id-prog-input').val();
      
      $.ajax({
        type : "POST",
        url : "ProgettoFormativoServlet",
        contenteType : "application/x-www-form-urlencoded",
        datatype : "json",
        data: "id="+idProg,
        success: function(data) {
        
        },
        error: function(error) {
        
        }
      })
      
    }
  </script>
</section>
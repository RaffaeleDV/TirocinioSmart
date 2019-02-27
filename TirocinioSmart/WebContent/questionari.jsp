<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
    import="it.unisa.model.QuestionarioModelDM"
    import="it.unisa.model.QuestionarioBean"
    import="it.unisa.model.AbstractBean"
    import="java.util.Collection"
    import="it.unisa.model.CompilaBean"
    import="it.unisa.model.CompilaModelDM"
    import="it.unisa.model.StudenteBean"
    import="java.util.logging.Logger"
    import="java.util.logging.Level"%>
<section id="questionari-wrapper" class="wrapper">
  <h1><b id="quest-heading">Questionari</b></h1>
  <div id="questionari-wrapper" class="wrapper">
    <%
      AbstractBean userQuestionari = (AbstractBean) session.getAttribute("SessionUser");
      if (userQuestionari == null) {
        Logger.getGlobal().log(Level.SEVERE, "Nessun utente trovato nella sessione dopo un login");
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
      
      QuestionarioModelDM qmDM = (QuestionarioModelDM) getServletContext().getAttribute("SessionQuestionarioModelDM");
      if (qmDM == null) {
        qmDM = new QuestionarioModelDM();
        getServletContext().setAttribute("SessionQuestionarioModelDM", qmDM);
      }
      
      CompilaModelDM cmDM = (CompilaModelDM) getServletContext().getAttribute("SessionCompilaModelDM");
      if (cmDM == null) {
        cmDM = new CompilaModelDM();
        getServletContext().setAttribute("SessionCompilaModelDM", cmDM);
      }
      
      Collection<AbstractBean> questionari = qmDM.doRetrieveAll(null);
      
      if (userQuestionari instanceof StudenteBean) {
        StudenteBean studente = (StudenteBean) userQuestionari;
	    Collection<AbstractBean> comps = cmDM.doRetrieveByStudente(studente.getID());
	    
	    if (questionari == null) {
	      %>
            <h3>Nessun Questionario Trovato</h3>
          <%
	      Logger.getGlobal().log(Level.INFO, "Nessun questionario trovato");
	    } else {
	      if (questionari.size() == 0) {
	        %>
	          <h3>Nessun Questionario Trovato</h3>
	        <%
	      } else {
	        %>
	          <!--
	          <div id="sceltaquestionario-wrapper" class="wrapper">
                <input id="search-input" name="questionario" type="text" placeholder="inserire il nome del questionario" />
                <input id="button" name="quest-submit" type="button" value="Vai Al Questionario" onclick="vaiQuestionario()" />
              </div>
              -->
            <%
	      }
	    }
	    
	    for (AbstractBean a: questionari) {
	      QuestionarioBean questionario = (QuestionarioBean) a;
	      boolean questionarioCompilato = false;
	      if (questionario != null) {
	        for (AbstractBean product: comps) {
	          CompilaBean compilaBean = (CompilaBean) product;
	          if (compilaBean.getQuestionarioID() == questionario.getID()) {
	            questionarioCompilato = true;
	            break;
	          }
	        }
	        
	        if (!questionarioCompilato) {
	        %>
	          <div id="questionario-wrapper" class="wrapper">
	            <p id="quest-info">Nome: <b><%= questionario.getNome() %></b></p>
	            <p id="quest-info">Descrizione: <%= questionario.getDescription() %></p>
	            <p id="quest-info">Tematica: <%= questionario.getTematica() %></p>
	            <p id="quest-info">#Domande: <%= questionario.getQuestions() %></p>
	            <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNstudenti() %></p>
	          </div>
	        <%
	        } else {
	        %>
	          <div id="questionario-wrapper-compilato" class="wrapper">
	            <p id="quest-info">Nome: <b><%= questionario.getNome() %></b></p>
	            <p id="quest-info">Descrizione: <%= questionario.getDescription() %></p>
	            <p id="quest-info">Tematica: <%= questionario.getTematica() %></p>
	            <p id="quest-info">#Domande: <%= questionario.getQuestions() %></p>
	            <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNstudenti() %></p>
	          </div>
	        <%
	        }
	      }
	    }
      } else {
        for (AbstractBean a: questionari) {
          QuestionarioBean questionario = (QuestionarioBean) a;
          if (questionario != null) {
            %>
            <div id="questionario-wrapper" class="wrapper">
              <p id="quest-info">Nome: <b><%= questionario.getNome() %></b></p>
              <p id="quest-info">Descrizione: <%= questionario.getDescription() %></p>
              <p id="quest-info">Tematica: <%= questionario.getTematica() %></p>
              <p id="quest-info">#Domande: <%= questionario.getQuestions() %></p>
              <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNstudenti() %></p>
            </div>
            <%
          }
        }
      }
    %>
  </div>
  <script type="text/javascript">
    function vaiQuestionario() {
      var questionario = $('#search-input').val();
      console.log(questionario);
      $.ajax({
        type : "POST",
        url : "EffettuaQuestionarioStudenteServlet",
        contentType: "application/x-www-form-urlencoded",
        datatype : "json", 
        data: "questionario="+questionario,
        success: function(data){
          console.log("Nome Del Questionario Scelto: " + questionario);
          console.log("Richiesta effettuata da EffettuaQuestionarioStudenteServlet");
        },
        error: function(error){
          console.log("Errore:"+ error);
        }
      })
    }
  </script>
</section>
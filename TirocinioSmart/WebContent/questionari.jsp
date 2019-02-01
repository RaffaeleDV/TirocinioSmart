<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
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
<section class="wrapper">
  <h1><b id="quest-heading">Questionari</b></h1>
  <div id="questionari-wrapper" class="wrapper">
    <div id="sceltaquestionario-wrapper" class="wrapper">
      <input id="input-questionario" name="questionario" type="text" placeholder="inserire il nome del questionario" />
      <input id="button-questionario" name="quest-submit" type="button" value="Vai Al Questionario" onclick="vaiQuestionario()" />
    </div>
    <%
      Object userQuestionari = session.getAttribute("SessionUser");
      if (userQuestionari == null) {
        Logger.getGlobal().log(Level.SEVERE, "Nessun utente trovato nella sessione dopo un login");
      }
      
      QuestionarioModelDM qmDM = new QuestionarioModelDM();
      CompilaModelDM cmDM = new CompilaModelDM();
      Collection<AbstractBean> questionari = qmDM.doRetrieveAll(null);
      
      if (userQuestionari instanceof StudenteBean) {
        StudenteBean studente = (StudenteBean) userQuestionari;
        CompilaBean compila = new CompilaBean(studente.getMatricola(), -10, null, null);
	    Collection<CompilaBean> comps = cmDM.retreiveByUtente(compila);
	    
	    if (questionari == null) {
	      Logger.getGlobal().log(Level.INFO, "Nessun questionario trovato");
	    } else {
	      if (questionari.size() == 0) {
	        %>
	          <h3>Nessun Questionario Trovato</h3>
	        <%
	      }
	    }
	    
	    for (AbstractBean a: questionari) {
	      QuestionarioBean questionario = (QuestionarioBean) a;
	      boolean questionarioCompilato = false;
	      if (questionario != null) {
	        for (CompilaBean c: comps) {
	          if (c.getQuestionario() == questionario.getId()) {
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
	            <p id="quest-info">#Domande: <%= questionario.getQuests() %></p>
	            <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNutenti() %></p>
	          </div>
	        <%
	        } else {
	        %>
	          <div id="questionario-wrapper-compilato" class="wrapper">
	            <p id="quest-info">Nome: <b><%= questionario.getNome() %></b></p>
	            <p id="quest-info">Descrizione: <%= questionario.getDescription() %></p>
	            <p id="quest-info">Tematica: <%= questionario.getTematica() %></p>
	            <p id="quest-info">#Domande: <%= questionario.getQuests() %></p>
	            <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNutenti() %></p>
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
              <p id="quest-info">#Domande: <%= questionario.getQuests() %></p>
              <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNutenti() %></p>
            </div>
            <%
          }
        }
      }
    %>
  </div>
  <script type="text/javascript">
    function vaiQuestionario() {
      var questionario = $('#input-questionario').val();
      
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
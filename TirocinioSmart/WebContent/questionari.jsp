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
      <form method="post" action="QuestionarioServlet">
        <input name="questionario" type="text" placeholder="inserire il nome del questionario"/>
        <input name="quest-submit" type="button" value="Link"/>
      </form>
    </div>
    <%
      Object userQuestionari = (Object) session.getAttribute("SessionUser");
      if (userQuestionari == null) {
        Logger.getGlobal().log(Level.SEVERE, "Nessun utente nella sessione quando il login è stato fatto");
      }
      
      if (userQuestionari instanceof StudenteBean) {
        StudenteBean studente = (StudenteBean) userQuestionari;
        CompilaBean compila = new CompilaBean(studente.getMatricola(), -10, null, null);
	    CompilaModelDM cmDM = new CompilaModelDM();
	    QuestionarioModelDM qmDM = new QuestionarioModelDM();
	    Collection<AbstractBean> questionari = qmDM.doRetrieveAll(null);
	    Collection<CompilaBean> comps = cmDM.retreiveByUtente(compila);
	    
	    if (questionari == null) {
	      
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
	            <p id="quest-info">Nome: <a href=" ... "><%= questionario.getNome() %></a></p>
	            <p id="quest-info">Descrizione: <%= questionario.getDescription() %></p>
	            <p id="quest-info">Tematica: <%= questionario.getTematica() %></p>
	            <p id="quest-info">#Domande: <%= questionario.getQuests() %></p>
	            <p id="quest-info">#Utenti Che Hanno Compilato: <%= questionario.getNutenti() %></p>
	          </div>
	        <%
	        } else {
	        %>
	          <div id="questionario-wrapper-compilato" class="wrapper">
	            <p id="quest-info">Nome: <a href=" ... "><%= questionario.getNome() %></a></p>
	            <p id="quest-info">Descrizione: <%= questionario.getDescription() %></p>
	            <p id="quest-info">Tematica: <%= questionario.getTematica() %></p>
	            <p id="quest-info">Num. Di Domande: <%= questionario.getQuests() %></p>
	            <p id="quest-info">Num. Di Utenti: <%= questionario.getNutenti() %></p>
	          </div>
	        <%
	        }
	      }
	    }
      }
    %>
  </div>
</section>
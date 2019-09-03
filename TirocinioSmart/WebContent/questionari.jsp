<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List"
    import="it.unisa.model.QuestionarioModelDM"
    import="it.unisa.model.QuestionarioBean"
    import="it.unisa.model.AbstractBean"
    import="java.util.Collection"
    import="it.unisa.model.CompilaBean"
    import="it.unisa.model.CompilaModelDM"
    import="it.unisa.model.TutorBean"
    import="it.unisa.model.TutorModelDM"
    import="it.unisa.model.StudenteBean"
    import="it.unisa.model.StudenteModelDM"
    import="it.unisa.model.UfficioBean"
    import="it.unisa.model.UfficioModelDM"
    import="java.util.logging.Logger"
    import="java.util.logging.Level"%>
<section id="questionari-wrapper" class="wrapper">
  <div id="questionari-heading-wrapper" class="wrapper">
    <img id="questionari-heading-icon" class="icon" src="images/questionari-icon.png"/>
    <h1 id="questionari-heading" class="heading"></h1>
  </div>
  <div id="questionari-wrapper" class="wrapper">
    <%
      Collection<AbstractBean> questionari = null;
      Collection<AbstractBean> comps = null;
      AbstractBean userQuestionari = (AbstractBean) session.getAttribute("SessionUser");
      
      if (userQuestionari == null) {
        RequestDispatcher view = request.getRequestDispatcher("login-page.jsp");
        view.forward(request, response);
      }
      
      if (userQuestionari.getClass().getName().equals(StudenteBean.class.getName())) {
    	  StudenteBean studenteBean = (StudenteBean) userQuestionari;
    	  questionari = StudenteModelDM.INSTANCE.doRetrieveQuestionari(studenteBean);
      } else if (userQuestionari.getClass().getName().equals(TutorBean.class.getName())) {
    	  TutorBean tutorBean = (TutorBean) userQuestionari;
    	  questionari = TutorModelDM.INSTANCE.doRetrieveQuestionari(tutorBean);
      } else if (userQuestionari.getClass().getName().equals(UfficioBean.class.getName())) {
    	  UfficioBean ufficioBean = (UfficioBean) userQuestionari;
    	  questionari = UfficioModelDM.INSTANCE.doRetrieveQuestionari(ufficioBean);
      } else {
    	  RequestDispatcher view = request.getRequestDispatcher("500-page.jsp");
    	  view.forward(request, response);
      }
      
      if (questionari != null && questionari.size() > 0) {
    	  QuestionarioBean questionarioBean = null;
    	  for (AbstractBean productQuestionario: questionari) {
    		  boolean compilato = false;
    		  questionarioBean = (QuestionarioBean) productQuestionario;
    		  compilato = CompilaModelDM.INSTANCE.isQuestionarioCompilato(userQuestionari, questionarioBean);
    		  if (questionarioBean != null && compilato == false) {
    			  %>
    			    <div id=questionario-wrapper" class="wrapper">
    			      <div id="questionario-info-wrapper" class="wrapper">
    			        <p id="questionario-info-heading" class="info-heading">ID</p>
    			        <p id="questionario-info" class="info"><%= questionarioBean.getID() %></p>
    			      </div>
    			      <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Nome</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getNome() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Tematica</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getTematica() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Questions</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getQuestions() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Nstudenti</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getNstudenti() %></p>
                </div>
                <div id="questionario-input-wrapper" class="wrapper">
                  <input id="ts-button" class="button" type="button" value="Compila" style="margin: 25px; margin-left: 45%; background-color: #2fa493; !important"/>
                </div>
    			    </div>
    			  <%
    		  } else if (questionarioBean != null && compilato == true) {
    			  %>
    			    <div id="questionario-wrapper-compilato" class="wrapper">
    			      <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">ID</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getID() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Nome</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getNome() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Tematica</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getTematica() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Domande</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getQuestions() %></p>
                </div>
                <div id="questionario-info-wrapper" class="wrapper">
                  <p id="questionario-info-heading" class="info-heading">Numero Studenti</p>
                  <p id="questionario-info" class="info"><%= questionarioBean.getNstudenti() %></p>
                </div>
                <div id="questionario-input-wrapper" class="wrapper">
                  <input id="ts-button" class="button" type="button" value="Compila" style="margin: 25px; margin-left: 45%; background-color: #A0A0A0; !important"/>
                </div>
    			    </div>
    			  <%
          } else {
    			  //redirect to an [404error] page
    			  //save the message for redirecting
    			  RequestDispatcher view = request.getRequestDispatcher("404-error.jsp");
            view.forward(request, response);
          }
        }
      } else {
    	  %>
        <div id="warning-wrapper" class="wrapper">
          <h2>Nessun Questionario Da Visualizzare</h2>
        </div>
        <%
      }
    %>
  </div>
</section>
<script type="text/javascript">
  if (typeof String.trim == "undefined") {
    String.prototype.trim = function() {
      return this.replace(/(^\s*)|(\s*$)/g, "");
    }
  }
</script>
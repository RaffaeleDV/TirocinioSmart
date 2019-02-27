<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="it.unisa.model.QuestionarioBean"
    import="java.util.HashMap"
    import="it.unisa.model.QuestionBean"
    import="it.unisa.model.ChooseBean"
    import="java.util.List"
    import="java.util.Set"%>
<section class="wrapper">
  <%
    QuestionarioBean questionario = (QuestionarioBean) session.getAttribute("SessionQuestionario");
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
    } else {
      RequestDispatcher disp = request.getRequestDispatcher("login-page.jsp");
      disp.forward(request, response);
    }
  %>
  <div class="wrapper">
    <%
      HashMap<QuestionBean, List<ChooseBean>> questChoosesMap = 
          (HashMap<QuestionBean, List<ChooseBean>>)session.getAttribute("SessionQuestionChoosesMap");
      Set<QuestionBean> questions = questChoosesMap.keySet();
      for (QuestionBean question: questions) {
        List<ChooseBean> chooses = questChoosesMap.get(question);
    %>
        <div class="wrapper">
          <div class="info-wrapper">
            <p class="info">Question: <b><%= question.getQuestion() %></b></p>
            <p class="info">Descrizione: <b><%= question.getDescription() %></b></p>
            <p class="info">#Max Answers: <b><%= question.getMaxAnswers() %></b></p>
            <p class="info">#Max Chooses: <b><%= question.getMaxChooses() %></b></p>
          </div>
          <div class="wrapper">
            <select>
            <%
              if (chooses != null) {
                for (ChooseBean choose: chooses) {
                  %>
                    <div class="info-wrapper">
                      <%
                        if (choose.getTipo().equals("select")) {
                          %>
                            <option> <%= choose.getDescription() %> </option>
                          <%
                        }
                      %>
                    </div>
                  <%
                }
              }
            %>
            </select>
          </div>
        </div>
      <%
      }
    %>
  </div>
</section>